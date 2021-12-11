package kim.bifrost.coldrain.work6th

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * kim.bifrost.coldrain.work6th.BaseActivity
 * Work6th
 *
 * @author 寒雨
 * @since 2021/12/8 14:06
 **/
abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewBinding().apply {
            setContentView(root)
            binding = this
        }
    }

    abstract fun getViewBinding(): T
}