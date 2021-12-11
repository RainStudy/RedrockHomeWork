package kim.bifrost.coldrain.work6th

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import androidx.core.view.ViewCompat
import kim.bifrost.coldrain.work6th.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btn.setOnClickListener {
            Lv2Activity.start(this)
        }
        // 因为不是button，所以必须设置点击回调才能变色
        binding.linearLayout.setOnClickListener {  }
        binding.linearLayout.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                // 缩小
                ViewCompat.animate(view)
                    .setDuration(200)
                    .scaleX(0.8f)
                    .scaleY(0.8f)
                    .start();
            } else if (motionEvent.action == MotionEvent.ACTION_UP) {
                // 复原
                ViewCompat.animate(view)
                    .setDuration(200)
                    .scaleX(1f)
                    .scaleY(1f)
                    .start();
            }
            false
        }
    }

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}