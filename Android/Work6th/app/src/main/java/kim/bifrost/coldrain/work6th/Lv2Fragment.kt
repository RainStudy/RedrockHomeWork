package kim.bifrost.coldrain.work6th

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kim.bifrost.coldrain.work6th.databinding.FragmentLv2Binding
import kim.bifrost.coldrain.work6th.lv3.Lv3Activity
import kotlin.properties.Delegates

class Lv2Fragment : Fragment() {

    lateinit var binding: FragmentLv2Binding
    var page by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = requireArguments().getInt("page")
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.text.text = "第${page}页"
        binding.nextBtn.setOnClickListener {
            startActivity(Intent(requireContext(), Lv3Activity::class.java))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentLv2Binding.inflate(inflater, container, false).apply { binding = this }.root
    }

    companion object {
        fun newInstance(page: Int): Lv2Fragment {
            val args = Bundle()
            args.putInt("page", page)
            val fragment = Lv2Fragment()
            fragment.arguments = args
            return fragment
        }
    }
}