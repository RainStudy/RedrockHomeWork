package kim.bifrost.coldrain.work7th

import android.annotation.SuppressLint
import android.content.Intent
import android.os.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kim.bifrost.coldrain.work7th.databinding.ActivityLv3Binding
import kim.bifrost.coldrain.work7th.fetch.DefFetchClient
import kim.bifrost.coldrain.work7th.fetch.HttpMethod
import kim.bifrost.coldrain.work7th.fetch.fetch
import org.json.JSONObject
import kotlin.concurrent.thread

class Lv3Activity : AppCompatActivity() {

    private lateinit var binding: ActivityLv3Binding

    private val defFetchClient by lazy { DefFetchClient(this) }

    private val handler = object : Handler(Looper.getMainLooper()) {
        @SuppressLint("NotifyDataSetChanged")
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                0 -> {
                    runOnUiThread {
                        AlertDialog.Builder(this@Lv3Activity)
                            .setTitle("Login successfully")
                            .setMessage("Welcome")
                            .setCancelable(false)
                            .setPositiveButton("OK") { _, _ -> }
                            .show()
                    }
                }
                -1 -> {
                    Toast.makeText(this@Lv3Activity, "账号密码不匹配", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityLv3Binding.inflate(layoutInflater).also { binding = it }.root)
        binding.btnLogin.setOnClickListener {
            thread {
                val username = binding.etLoginUsername.text.toString()
                val password = binding.etLoginPassword.text.toString()
                fetch("https://www.wanandroid.com/user/login", defFetchClient) {
                    method = HttpMethod.POST
                    formBody {
                        put("username", username)
                        put("password", password)
                    }
                }.onSuccess {
                    JSONObject(it.string()).apply {
                        when (getInt("errorCode")) {
                            0 -> {
                                // 登录成功
                                handler.sendMessage(Message().apply { what = 0 })
                            }
                            -1 -> {
                                // 登录失败
                                handler.sendMessage(Message().apply { what = 1 })
                            }
                        }
                    }
                }.onFailure {
                    runOnUiThread {
                        Toast.makeText(this,
                            "网络请求失败: ${it.message}",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, Lv3RegisterActivity::class.java))
        }
        binding.btnNext.setOnClickListener {
            startActivity(Intent(this, UserInfoActivity::class.java))
        }
    }
}