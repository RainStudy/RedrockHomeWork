package kim.bifrost.coldrain.work6th.lv4

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kim.bifrost.coldrain.work6th.BaseActivity
import kim.bifrost.coldrain.work6th.databinding.ActivityLv4Binding

class Lv4Activity : BaseActivity<ActivityLv4Binding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rv.apply {
            layoutManager = LinearLayoutManager(this@Lv4Activity)
            adapter = Lv4Adapter(this@Lv4Activity)
            addItemDecoration(DividerItemDecoration(this@Lv4Activity, DividerItemDecoration.VERTICAL))
        }
    }

    override fun getViewBinding(): ActivityLv4Binding = ActivityLv4Binding.inflate(layoutInflater)
}