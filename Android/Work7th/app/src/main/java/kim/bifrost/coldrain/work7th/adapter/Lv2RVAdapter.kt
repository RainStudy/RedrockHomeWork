package kim.bifrost.coldrain.work7th.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kim.bifrost.coldrain.work7th.databinding.ItemLv2Binding

/**
 * kim.bifrost.coldrain.work7th.adapter.Lv2RVAdapter
 * Work7th
 *
 * @author 寒雨
 * @since 2021/12/13 19:36
 **/
class Lv2RVAdapter(private val callback: ArrayList<String>.(Lv2RVAdapter) -> Unit) : RecyclerView.Adapter<Lv2RVAdapter.Holder>() {

    inner class Holder(val binding: ItemLv2Binding) : RecyclerView.ViewHolder(binding.root)

    private val elements = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemLv2Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.itemLv2.text = elements[position]
    }

    override fun getItemCount(): Int = elements.size

    @SuppressLint("NotifyDataSetChanged")
    fun refresh() {
        elements.clear()
        callback(elements, this)
    }
}