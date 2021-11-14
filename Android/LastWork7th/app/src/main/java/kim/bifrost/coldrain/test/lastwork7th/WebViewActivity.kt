package kim.bifrost.coldrain.test.lastwork7th

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kim.bifrost.coldrain.test.lastwork7th.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    lateinit var binding: ActivityWebViewBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityWebViewBinding.inflate(layoutInflater).run {
            binding = this
            root
        })
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl(intent.getStringExtra("url")!!)
    }

    companion object {
        fun startActivity(context: Context, url: String) {
            context.startActivity(Intent(context, WebViewActivity::class.java).apply {
                putExtra("url", url)
            })
        }
    }
}