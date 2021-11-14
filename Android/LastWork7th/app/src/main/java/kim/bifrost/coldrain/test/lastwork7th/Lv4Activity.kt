package kim.bifrost.coldrain.test.lastwork7th

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kim.bifrost.coldrain.test.lastwork7th.databinding.ActivityLv4Binding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class Lv4Activity : AppCompatActivity() {

    lateinit var binding: ActivityLv4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityLv4Binding.inflate(layoutInflater).run {
            binding = this
            root
        })
        binding.login.setOnClickListener {
            login(binding.accountInput.text.toString(), binding.passwordInput.text.toString())
        }
        binding.register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun login(account: String, password: String) {
        MainActivity.coroutineScope.launch(Dispatchers.IO) {
            val requestBody = FormBody.Builder()
                .add("username", account)
                .add("password", password)
                .build()
            val request = Request.Builder()
                .url("https://www.wanandroid.com/user/login")
                .post(requestBody)
                .build()
            val client = OkHttpClient()
            val response = client.newCall(request).execute().body?.string()
            if (response != null) {
                val obj = JSONObject(response)
                when (obj.getInt("errorCode")) {
                    0 -> {
                        // 登录成功
                        withContext(Dispatchers.Main) {
                            AlertDialog.Builder(this@Lv4Activity)
                                .setTitle("Login successfully")
                                .setMessage("Welcome, $account")
                                .setCancelable(false)
                                .setPositiveButton("OK") { _, _ -> }
                                .show()
                        }
                    }
                    -1 -> {
                        // 账号或密码错误
                        withContext(Dispatchers.Main) {
                            AlertDialog.Builder(this@Lv4Activity)
                                .setTitle("Incorrect account & password")
                                .setMessage("Check it")
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