package kim.bifrost.coldrain.work7th

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import kim.bifrost.coldrain.work7th.databinding.ActivityUserInfoBinding
import kim.bifrost.coldrain.work7th.fetch.DefFetchClient
import kim.bifrost.coldrain.work7th.fetch.fetch
import org.json.JSONObject
import kotlin.concurrent.thread

class UserInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserInfoBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityUserInfoBinding.inflate(layoutInflater).also { binding = it }.root)
        thread {
            // DefFetchClient只使用一次，直接就地实例化
            fetch("https://wanandroid.com//user/lg/userinfo/json", DefFetchClient(this)).onSuccess {
                JSONObject(it.string()).apply {
                    when (getInt("errorCode")) {
                        0 -> {
                            runOnUiThread { binding.tvUserinfo.text = "ID: ${getJSONObject("data").getJSONObject("userInfo").getLong("id")} \nName: ${getJSONObject("data").getJSONObject("userInfo").getString("nickname")}" }
                        }
                        else -> {
                            runOnUiThread { binding.tvUserinfo.text = getString("errorMsg") }
                        }
                    }
                }
            }.onFailure {
                runOnUiThread { binding.tvUserinfo.text = "网络请求失败: ${it.message}" }
            }
            // 对banner的实现可以看我的WanAndroid项目,这里偷个懒只加载一张图片
            fetch("https://www.wanandroid.com/banner/json").onSuccess {
                JSONObject(it.string()).apply {
                    when (getInt("errorCode")) {
                        0 -> {
                            runOnUiThread {
                                Glide.with(binding.root)
                                    .load(getJSONArray("data").getJSONObject(0).getString("imagePath"))
                                    .into(binding.banner)
                            }
                        }
                        -1 -> {
                            runOnUiThread {
                                Toast.makeText(this@UserInfoActivity, "请求错误: ${getString("errorMsg")}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }.onFailure {
                runOnUiThread { Toast.makeText(this, "网络请求失败: ${it.message}", Toast.LENGTH_SHORT).show() }
            }
            // 加载完毕
            runOnUiThread { binding.pb.visibility = View.INVISIBLE }
        }
    }
}