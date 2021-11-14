package kim.bifrost.coldrain.test.lastwork7th

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kim.bifrost.coldrain.test.lastwork7th.databinding.ActivityLv2Binding
import kim.bifrost.coldrain.test.lastwork7th.lv2.Data
import kim.bifrost.coldrain.test.lastwork7th.lv2.Lv2Adapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class Lv2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityLv2Binding

    private val handler = object : Handler(Looper.getMainLooper()) {
        @SuppressLint("SetTextI18n")
        override fun handleMessage(msg: Message) {
            // 在这里可以进行UI操作
            when (msg.what) {
                1 -> initRecyclerView()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLv2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        initWithLibs()
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this).apply { orientation = LinearLayoutManager.VERTICAL }
        binding.recyclerView.adapter = Lv2Adapter(dataRegistry).apply {
            onItemClick { _, v ->
                WebViewActivity.startActivity(this@Lv2Activity, v.text.toString())
            }
        }
    }

    // 不使用外部库
    private fun init() {
        thread {
            // http请求部分
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                connection = URL("https://www.wanandroid.com/article/list/0/json").openConnection() as HttpURLConnection
                connection.readTimeout = 8000
                connection.connectTimeout = 8000
                BufferedReader(InputStreamReader(connection.inputStream)).use {
                    it.forEachLine {
                        response.append(it)
                    }
                }
                val json = response.toString()
                val array = JSONObject(json).getJSONObject("data").getJSONArray("datas")
                for (i in 0 until array.length()) {
                    val obj = array.getJSONObject(i)
                    dataRegistry.add(Data(obj.getString("title"), obj.getString("shareUser"), obj.getString("link")))
                }
                // 初始化RecyclerView
                handler.sendMessage(Message().apply { what = 1 })
            } catch (t: Throwable) {
                t.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }
    }

    // 使用外部库
    // 协程/OkHttp
    @SuppressLint("NotifyDataSetChanged")
    private fun initWithLibs() {
        MainActivity.coroutineScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://www.wanandroid.com/article/list/0/json")
                .build()
            val response = client.newCall(request).execute().body?.string()
            if (response != null) {
                val array = JSONObject(response).getJSONObject("data").getJSONArray("datas")
                for (i in 0 until array.length()) {
                    val obj = array.getJSONObject(i)
                    dataRegistry.add(Data(obj.getString("title"), obj.getString("shareUser"), obj.getString("link")))
                }
            }
            // 初始化RecyclerView
            withContext(Dispatchers.Main) {
                initRecyclerView()
            }
        }
    }

    companion object {
        val dataRegistry = ArrayList<Data>()
    }
}