package team.redrock.coldrain.lesson1.lv4

import java.util.*
import java.util.concurrent.TimeUnit

/**
 * team.redrock.coldrain.lesson1.lv4.DiningHall
 * Lesson1
 *
 * @author 寒雨
 * @since 2021/10/18 16:44
 **/
abstract class Restaurant(val name: String) {

    /**
     * 欢迎
     *
     */
    abstract fun welcome()

    abstract fun pay(way: PayWay)

    // 支付渠道
    enum class PayWay {
        WECHAT, ALIPAY, SCAN_FACE
    }

    companion object {
        val INSTANCE: Restaurant = object : Restaurant("红岩餐厅") {
            override fun welcome() {
                val scan = Scanner(System.`in`)
                val orders = arrayListOf<Dish>()
                println("Hello!Welcome to RedRock Restaurant!")
                println("今日菜单:")
                do {
                    Dish.values().forEach {
                        println("${it.id}.${it.display_name} ${it.price}元")
                    }
                    println("请输入要点的菜的序号，输入0结算:")
                    // 堵塞
                    val id = scan.nextInt()
                    if (id == 0) break
                    val find = Dish.values().firstOrNull { it.id == id } ?: error("不存在的菜品")
                    if (orders.contains(find)) {
                        println("你已经点过这道菜啦")
                        continue
                    }
                    orders.add(find)
                    println("你一共点了:")
                    var sum = 0.0
                    orders.forEachIndexed { index, dish ->
                        sum += dish.price
                        println("${index + 1}.${dish.display_name} ${dish.price}元")
                    }
                    println("共计: ${sum}元")
                } while (true)
                println("选择支付方式:")
                println("1. 支付宝")
                println("2. 微信")
                println("3. 人脸识别")
                val way = when (scan.nextInt()) {
                    1 -> PayWay.ALIPAY
                    2 -> PayWay.WECHAT
                    3 -> PayWay.SCAN_FACE
                    else -> error("不存在的支付方式")
                }
                pay(way)
            }

            override fun pay(way: PayWay) {
                when (way) {
                    PayWay.ALIPAY -> println("出示支付宝付款码")
                    PayWay.WECHAT -> println("出示微信付款码")
                    PayWay.SCAN_FACE -> println("请露出面部")
                }
                Thread.sleep(TimeUnit.SECONDS.toMillis(3L))
                println("支付成功")
            }

        }
    }
}