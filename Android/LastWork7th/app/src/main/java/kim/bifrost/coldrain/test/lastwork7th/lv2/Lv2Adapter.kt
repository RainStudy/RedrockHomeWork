package kim.bifrost.coldrain.test.lastwork7th.lv2

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kim.bifrost.coldrain.test.lastwork7th.databinding.DataBinding

class Lv2Adapter(private val list: List<Data>) : RecyclerView.Adapter<Lv2Adapter.Holder>() {

    private lateinit var binding: DataBinding
    private var itemClickListener: (Int, TextView) -> Unit = { _, _ -> }

    inner class Holder(binding: DataBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.link.setOnClickListener {
                itemClickListener(this.adapterPosition, binding.link)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        binding = DataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list[position]
        binding.link.text = data.link
        binding.shareUser.text = data.shareUser
        binding.title.text = data.title
    }

    override fun getItemCount(): Int = list.size

    /**
     * 回调传参
     */
    fun onItemClick(func: (Int, TextView) -> Unit) {
        itemClickListener = func
    }
}