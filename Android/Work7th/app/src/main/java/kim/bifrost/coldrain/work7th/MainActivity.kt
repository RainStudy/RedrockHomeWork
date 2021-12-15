package kim.bifrost.coldrain.work7th

import android.annotation.SuppressLint
import android.content.Intent
import android.os.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import kim.bifrost.coldrain.work7th.adapter.Lv2RVAdapter
import kim.bifrost.coldrain.work7th.databinding.ActivityMainBinding
import kim.bifrost.coldrain.work7th.fetch.fetch
import org.json.JSONObject
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val handler = object : Handler(Looper.getMainLooper()) {
        @SuppressLint("NotifyDataSetChanged")
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                1 -> {
                    binding.rvLv2.adapter?.notifyDataSetChanged()
                    Toast.makeText(this@MainActivity, "刷新完成", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)
        binding.btnLv2.setOnClickListener {
            (binding.rvLv2.adapter as Lv2RVAdapter).refresh()
        }
        binding.btnNext.setOnClickListener {
            startActivity(Intent(this, Lv3Activity::class.java))
        }
        binding.rvLv2.layoutManager = LinearLayoutManager(this)
        binding.rvLv2.adapter = Lv2RVAdapter {
            thread {
                fetch("https://www.wanandroid.com//hotkey/json").onSuccess {
                    val array = JSONObject(it.string())
                        .getJSONArray("data")
                    for (i in (0 until array.length())) {
                        add(array.getJSONObject(i).getString("name"))
                    }
                    handler.sendMessage(Message().apply { what = 1 })
                }.onFailure {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "网络请求失败: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}