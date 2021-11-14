package kim.bifrost.coldrain.test.lastwork7th

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kim.bifrost.coldrain.test.lastwork7th.databinding.ActivityLv4Binding
import kim.bifrost.coldrain.test.lastwork7th.databinding.ActivityRegisterBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityRegisterBinding.inflate(layoutInflater).run {
            binding = this
            root
        })
        binding.register.setOnClickListener {
            register(binding.accountInput.text.toString(), binding.passwordInput.text.toString(), binding.confirmInput.text.toString())
        }
        binding.backToLogin.setOnClickListener {
            startActivity(Intent(this, ActivityLv4Binding::class.java))
        }
    }

    private fun register(account: String, password: String, confirm: String) {
        MainActivity.coroutineScope.launch(Dispatchers.IO) {
            val requestBody = FormBody.Builder()
                .add("username", account)
                .add("password", password)
                .add("repassword", confirm)
                .build()
            val request = Request.Builder()
                .url("https://www.wanandroid.com/user/register")
                .post(requestBody)
                .build()
            val client = OkHttpClient()
            val response = client.newCall(request).execute().body?.string()
            if (response != null) {
                val obj = JSONObject(response)
                when (obj.getInt("errorCode")) {
                    0 -> {
                        // 注册成功
                        withContext(Dispatchers.Main) {
                            AlertDialog.Builder(this@RegisterActivity)
                                .setTitle("Register successfully")
                                .setMessage("You can use this account to login now")
                                .setCancelable(false)
                                .setPositiveButton("OK") { _, _ ->
                                    // 跳转登录界面
                                    startActivity(Intent(this@RegisterActivity, Lv4Activity::class.java))
                                }
                                .show()
                        }
                    }
                    -1 -> {
                        // 两次密码不一致
                        withContext(Dispatchers.Main) {
                            AlertDialog.Builder(this@RegisterActivity)
                                .setTitle("Password not equals Confirm")
                                .setMessage("Check it")
                                .setCancelable(false)
                                .setPositiveButton("OK") { _, _ -> }
                                .show()
                        }
                    }
                    else -> {
                        // 未知错误
                        withContext(Dispatchers.Main) {
                            AlertDialog.Builder(this@RegisterActivity)
                                .setTitle("Unexpected error")
                                .setMessage("A unexpected error occurred")
                                .setCancelable(false)
                                .setPositiveButton("OK") { _, _ -> }
                                .show()
                        }
                    }
                }
            }
        }
    }
}