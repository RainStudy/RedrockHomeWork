package kim.bifrost.coldrain.work6th.lv3.frags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kim.bifrost.coldrain.work6th.databinding.FragmentLv3RvBinding
import kim.bifrost.coldrain.work6th.lv3.Lv3RVAdapter

/**
 * kim.bifrost.coldrain.work6th.lv3.frags.Lv3RVFragment
 * Work6th
 *
 * @author 寒雨
 * @since 2021/12/9 9:53
 **/
class Lv3RVFragment : Fragment() {

    lateinit var binding: FragmentLv3RvBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = Lv3RVAdapter(requireContext())
        binding.rv.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentLv3RvBinding.inflate(inflater, container, false).also { binding = it }.root
    }
}