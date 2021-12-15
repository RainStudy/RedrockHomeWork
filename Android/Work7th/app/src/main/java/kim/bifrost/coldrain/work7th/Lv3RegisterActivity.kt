package kim.bifrost.coldrain.work7th

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.annotation.RequiresApi
import kim.bifrost.coldrain.work7th.databinding.ActivityLv3RegisterBinding
import kim.bifrost.coldrain.work7th.fetch.DefFetchClient
import kim.bifrost.coldrain.work7th.fetch.HttpMethod
import kim.bifrost.coldrain.work7th.fetch.fetch
import org.json.JSONObject
import kotlin.concurrent.thread

class Lv3RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLv3RegisterBinding

    private val defFetchClient by lazy { DefFetchClient(this) }

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityLv3RegisterBinding.inflate(layoutInflater).also { binding = it }.root)
        binding.btnRegister.setOnClickListener {
            val username = binding.etRegisterUsername.text.toString()
            val password = binding.etRegisterPassword.text.toString()
            val repass = binding.etRegisterRepassword.text.toString()
            thread {
                fetch("https://www.wanandroid.com/user/register", defFetchClient) {
                    method = HttpMethod.POST
                    formBody {
                        put("username", username)
                        put("password", password)
                        put("repassword", repass)
                    }
                }.onSuccess {
                    JSONObject(it.string()).apply {
                        when (getInt("errorCode")) {
                            0 -> {
                                // 注册成功
                                runOnUiThread { Toast.makeText(this@Lv3RegisterActivity, "注册成功", Toast.LENGTH_SHORT).show() }
                            }
                            -1 -> {
                                // 注册失败
                                runOnUiThread { Toast.makeText(this@Lv3RegisterActivity, "注册失败: ${getString("errorMsg")}", Toast.LENGTH_SHORT).show() }
                            }
                        }
                    }
                }.onFailure {
                    // 主线程运行
                    runOnUiThread { Toast.makeText(this, "网络请求失败: ${it.message}", Toast.LENGTH_SHORT).show() }
                }
            }
        }
    }
}