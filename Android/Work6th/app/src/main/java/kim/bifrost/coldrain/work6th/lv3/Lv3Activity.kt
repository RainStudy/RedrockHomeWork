package kim.bifrost.coldrain.work6th.lv3

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kim.bifrost.coldrain.work6th.BaseActivity
import kim.bifrost.coldrain.work6th.VP2Adapter
import kim.bifrost.coldrain.work6th.databinding.ActivityLv3Binding
import kim.bifrost.coldrain.work6th.lv3.frags.Lv3ImageFragment
import kim.bifrost.coldrain.work6th.lv3.frags.Lv3RVFragment

class Lv3Activity : BaseActivity<ActivityLv3Binding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vp2.adapter = VP2Adapter(this, listOf(Lv3RVFragment(), Lv3ImageFragment()))
    }

    override fun getViewBinding(): ActivityLv3Binding = ActivityLv3Binding.inflate(layoutInflater)
}