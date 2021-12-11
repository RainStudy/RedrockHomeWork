package kim.bifrost.coldrain.work6th.lv5

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import kim.bifrost.coldrain.work6th.BaseActivity
import kim.bifrost.coldrain.work6th.databinding.ActivityLv5Binding

class Lv5Activity : BaseActivity<ActivityLv5Binding>() {

    // 点击同意用户协议时的动画的时间
    private val lottieProgress = 0.39f

    // 用户同意协议
    private var userAgreementIsCheck: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 点击选中
        binding.lavLoging.setOnClickListener {
            binding.lavLoging.playAnimation()
            userAgreementIsCheck = !userAgreementIsCheck
        }
        // 动画更新监听
        binding.lavLoging.addAnimatorUpdateListener {
            if (it.animatedFraction == 1f && userAgreementIsCheck) {
                // 动画已经执行完毕 & 同意协议
                // 暂停动画
                binding.lavLoging.pauseAnimation()
            } else if (it.animatedFraction >= lottieProgress && it.animatedFraction != 1f && !userAgreementIsCheck) {
                // 动画执行时间达到指定时长 & 不同意协议
                binding.lavLoging.pauseAnimation()
            }
        }
        // 初始化图标
        binding.lavLoging.playAnimation()
        // 协议文字不同颜色
        val builder = SpannableStringBuilder("同意 《用户协议》 和 《隐私权政策》").apply {
            setSpan(ForegroundColorSpan(Color.parseColor("#95eeff")), 3, 9, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            setSpan(ForegroundColorSpan(Color.parseColor("#95eeff")), 12, 19, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        }
        binding.agreement.text = builder
        binding.touristMode.text = buildSpannableText {
            append("没有学号吗? ")
            append("游客模式吧", "#506484")
        }

    }

    override fun getViewBinding(): ActivityLv5Binding = ActivityLv5Binding.inflate(layoutInflater)

    companion object {
        // DSL风格的SpannableStringBuilder封装
        fun buildSpannableText(func: SpannableData.() -> Unit): SpannableStringBuilder {
            return SpannableData().apply { func(this) }.contents
        }
    }

    class SpannableData {

        val contents = SpannableStringBuilder()

        fun append(content: String, color: String? = null) {
            contents.append(content)
            if (color != null) {
                contents.setSpan(
                    ForegroundColorSpan(Color.parseColor(color)),
                    contents.length - content.length,
                    contents.length,
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                )
            }
        }
    }
}