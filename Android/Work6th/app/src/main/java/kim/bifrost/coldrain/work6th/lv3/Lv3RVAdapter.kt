package kim.bifrost.coldrain.work6th.lv3

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kim.bifrost.coldrain.work6th.databinding.Lv3RvItemBinding
import kim.bifrost.coldrain.work6th.lv4.Lv4Activity
import kotlin.random.Random

/**
 * kim.bifrost.coldrain.work6th.lv3.Lv3RVAdapter
 * Work6th
 *
 * @author 寒雨
 * @since 2021/12/9 9:11
 **/
class Lv3RVAdapter(val context: Context) : RecyclerView.Adapter<Lv3RVAdapter.Holder>(){

    private val elements = arrayListOf("廖老师", "祥瑞giegie", "中泰giegie", "王兮姐姐")

    inner class Holder(val binding: Lv3RvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                context.startActivity(Intent(context, Lv4Activity::class.java))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(Lv3RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val array = elements.clone() as ArrayList<String>
        holder.binding.textView.text = getRandom(array) + "是大卷怪"
        holder.binding.textView2.text = getRandom(array) + "也是大卷怪"
        holder.binding.textView3.text = "红岩最卷的是" + getRandom(array)
    }

    override fun getItemCount(): Int = 20

    private fun getRandom(array: ArrayList<String>): String =
        array[Random.nextInt(array.size)].apply { array.remove(this) }

}