package team.redrock.coldrain.second.third

import java.util.*

/**
 * team.redrock.coldrain.second.`3rd`.Lv4
 * 2ed
 * 王子的特殊闪避系统
 *
 * @author 寒雨
 * @since 2021/11/2 0:36
 **/
abstract class KingDodge(var next: KingDodge?) {
    abstract fun dodge(enemy: Enemy)
    // builder类
    class Builder {
        private val list = LinkedList<KingDodge>()

        /**
         * 稍作修改，可以用lambda表达式了
         *
         * @param dodge 闪避逻辑
         * @receiver
         */
        fun add(dodge: (Enemy, KingDodge?) -> Unit): Builder {
            val inst = object : KingDodge(null) {
                override fun dodge(enemy: Enemy) {
                    dodge(enemy, next)
                }
            }
            list.lastOrNull()?.next = inst
            list.add(inst)
            return this
        }

        fun build(): KingDodge {
            return list.firstOrNull() ?: error("No dodge instance added")
        }
    }

    companion object {
        // 王子本身的防御值
        const val DEFENSE = 10
    }
}

class Enemy(val atk : Int) {
    fun attack(dodge: KingDodge) {
        dodge.dodge(this)
    }

    fun attacked() {
        println("反击成功")
    }
}

fun main() {
    val dodge = KingDodge.Builder()
        .add { e, next ->
            if (e.atk > KingDodge.DEFENSE * 3)
                println("王子逃跑")
            else next?.dodge(e)
        }
        .add { e, next ->
            if (e.atk > KingDodge.DEFENSE * 2)
                println("王子挡下一击后逃跑")
            else next?.dodge(e)
        }
        .add { e, next ->
            if (e.atk > KingDodge.DEFENSE * 1)
                println("王子躲避该次攻击并反击").let { e.attacked() }
            else next?.dodge(e)
        }
        .add { e, _ ->
            println("王子直接反击")
            e.attacked()
        }
        .build()
    println("为王子的敌人赋予ATK值:")
    // 获取值后自动关闭
    val atk = Scanner(System.`in`).run {
        nextInt().also { close() }
    }
    Enemy(atk).attack(dodge)
}