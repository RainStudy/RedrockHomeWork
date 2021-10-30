package team.redrock.coldrain.second.lv5.entity

import team.redrock.coldrain.second.lv5.skill.ISkill
import java.util.concurrent.TimeUnit

/**
 * team.redrock.coldrain.second.lv5.entity.Boss
 * 2ed
 *
 * @author 寒雨
 * @since 2021/10/30 19:51
 **/
// 风之剑圣 亚里欧斯
class Arios() : Entity("风之剑圣·亚里欧斯", 5000.0), Boss, ISkill {
    init {
        attributeData.apply {
            append("Damage", arrayOf(150.0, 300.0, 0.0))
            append("Dodge", arrayOf(10.0, 10.0, 0.0))
        }
    }
    override val executeRate: Double = 0.3

    override var executing: Boolean = false

    override fun cast(caster: Entity, victim: Entity) {
        // 里疾风 二段杀伤
        println("秘技·里疾风")
        val damage = caster.attributeData.getFixed("Damage")
        victim.damage(caster, 0.0)
        Thread.sleep(TimeUnit.SECONDS.toMillis(1L))
        victim.damage(caster, damage / 3)
        println("你的伤害永久性下降10%")
        victim.attributeData.append("Damage", arrayOf(0.0, 0.0, -10.0))
    }

    override fun tell() {
        println("[???] 出于一己私欲，背离正义，舍弃道德，坚持一意孤行")
        Thread.sleep(TimeUnit.SECONDS.toMillis(1L))
        println("[???] 八叶一刀流·二之型·『疾风』免许皆传 亚里欧斯·马克莱因 参上!!!")
        Thread.sleep(TimeUnit.SECONDS.toMillis(1L))
    }
}

// 结社·噬身之蛇 执行者No.I 劫炎 马克邦
//class Mcburn()

interface Boss {
    // 战前问候
    fun tell()
}