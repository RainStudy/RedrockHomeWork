package kim.bifrost.coldrain.work6th

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * kim.bifrost.coldrain.work6th.VP2Adapter
 * Work6th
 *
 * @author 寒雨
 * @since 2021/12/8 16:18
 **/
class VP2Adapter(fragmentActivity: FragmentActivity, private val fragments: List<Fragment>) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}