package kim.bifrost.coldrain.work6th.lv4

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import kim.bifrost.coldrain.work6th.databinding.Lv4RvHeaderBinding
import kim.bifrost.coldrain.work6th.databinding.Lv4RvItemBinding
import kim.bifrost.coldrain.work6th.lv5.Lv5Activity

/**
 * kim.bifrost.coldrain.work6th.lv4.Lv4Adapter
 * Work6th
 *
 * @author 寒雨
 * @since 2021/12/9 11:20
 **/
class Lv4Adapter(val context: Context) : RecyclerView.Adapter<Lv4Adapter.Holder>(){

    class Data(val title: String, val description: String, val image: String)

    private val list = arrayListOf(
        Data(
            "Flandre Scarlet",
            "紅い血液の悪魔 (鲜红血液的恶魔)",
            "https://avatars.githubusercontent.com/u/69996135?v=4"
        ),
        Data(
            "Remilia Scarlet",
            "永遠に紅い幼き月 (永远鲜红的幼月)",
            "https://gitee.com/coldrain-moro/images_bed/raw/master/images/remilia.png"
        )
    )

    inner class Holder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            if (binding is Lv4RvItemBinding) {
                binding.root.setOnClickListener {
                    // 进入下一个Activity
                    context.startActivity(Intent(context, Lv5Activity::class.java))
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) return 0
        return 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        if (viewType == 0) return Holder(Lv4RvHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        return Holder(Lv4RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val binding = holder.binding
        if (binding is Lv4RvItemBinding) {
            val data = list[position - 1]
            Glide.with(binding.root)
                .load(data.image)
                .into(binding.imageView)
            binding.textView4.text = data.title
            binding.textView5.text = data.description
        }
    }

    override fun getItemCount(): Int = list.size + 1

}