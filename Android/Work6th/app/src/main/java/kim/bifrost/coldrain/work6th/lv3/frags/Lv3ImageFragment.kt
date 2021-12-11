package kim.bifrost.coldrain.work6th.lv3.frags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kim.bifrost.coldrain.work6th.databinding.FragmentLv3ImageBinding

/**
 * kim.bifrost.coldrain.work6th.lv3.frags.Lv3ImageFragment
 * Work6th
 *
 * @author 寒雨
 * @since 2021/12/9 10:49
 **/
class Lv3ImageFragment : Fragment() {
    lateinit var binding: FragmentLv3ImageBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 使用Glide加载网络图片
        Glide.with(view)
            .load("https://gitee.com/coldrain-moro/images_bed/raw/master/images/redrock.png")
            .into(binding.image)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentLv3ImageBinding.inflate(inflater, container, false).apply { binding = this }.root
    }
}