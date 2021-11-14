package kim.bifrost.coldrain.test.lastwork7th

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.google.gson.GsonBuilder
import kim.bifrost.coldrain.test.lastwork7th.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val handler = object : Handler(Looper.getMainLooper()) {
        @SuppressLint("SetTextI18n")
        override fun handleMessage(msg: Message) {
            // 在这里可以进行UI操作
            when (msg.what) {
                1 -> binding.count.text = (Integer.parseInt(binding.count.text.toString()) - 1).toString()
                2 -> {
                    binding.next.text = "Click to Lv2"
                    binding.next.setOnClickListener {
                        startActivity(Intent(this@MainActivity, Lv2Activity::class.java))
                    }
                    binding.toLogin.text = "Click to Lv4"
                    binding.toLogin.setOnClickListener {
                        startActivity(Intent(this@MainActivity, Lv4Activity::class.java))
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        countByThread()
        countByCoroutine()
    }

    /**
     * 使用多线程的方式进行
     * 没必要开线程池吧
     */
    private fun countByThread() {
        thread {
            while (binding.count.text.toString().toInt() > 0) {
                Thread.sleep(TimeUnit.SECONDS.toMillis(1))
                handler.sendMessage(Message().apply { what = 1 })
            }
            handler.sendMessage(Message().apply { what = 2 })
        }
    }

    /**
     * 使用协程 本质上是一样的
     */
    private fun countByCoroutine() {
        coroutineScope.launch {
            while (binding.count.text.toString().toInt() > 0) {
                delay(TimeUnit.SECONDS.toMillis(1))
                binding.count.text = (binding.count.text.toString().toInt() - 1).toString()
            }
            handler.sendMessage(Message().apply { what = 2 })
        }
    }

    companion object {

        lateinit var binding: ActivityMainBinding
            private set

        val coroutineScope = CoroutineScope(Dispatchers.Main)

        private val gson = GsonBuilder()
            .setPrettyPrinting()
            .create()
    }
}