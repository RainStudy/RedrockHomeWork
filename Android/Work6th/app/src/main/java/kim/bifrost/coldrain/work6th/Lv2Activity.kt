package kim.bifrost.coldrain.work6th

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import kim.bifrost.coldrain.work6th.databinding.ActivityLv2Binding

class Lv2Activity : BaseActivity<ActivityLv2Binding>() {

    private val data = arrayOf("第一页", "第二页", "第三页")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vp2.adapter = VP2Adapter(this, ArrayList<Fragment>().apply {
            for (i in 1..3) {
                add(Lv2Fragment.newInstance(i))
            }
        })
        TabLayoutMediator(binding.tl, binding.vp2) { t, p ->
            t.text = data[p]
        }.attach()
    }

    override fun getViewBinding(): ActivityLv2Binding = ActivityLv2Binding.inflate(layoutInflater)

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, Lv2Activity::class.java))
        }
    }
}