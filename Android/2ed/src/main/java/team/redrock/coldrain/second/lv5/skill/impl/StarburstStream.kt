package team.redrock.coldrain.second.lv5.skill.impl

import team.redrock.coldrain.second.lv5.entity.Entity
import team.redrock.coldrain.second.lv5.eventbus.EventProperty
import team.redrock.coldrain.second.lv5.eventbus.Events
import team.redrock.coldrain.second.lv5.eventbus.impl.EntityDamageEvent
import team.redrock.coldrain.second.lv5.skill.ISkill
import java.util.concurrent.TimeUnit

/**
 * team.redrock.coldrain.second.lv5.skill.impl.StarburstStream
 * 2ed
 * 星爆气流斩 16连击
 * 伤害逐渐增加
 *
 * @author 寒雨
 * @since 2021/10/27 0:16
 **/
object StarburstStream : ISkill {
    override var executing: Boolean = false

    override val executeRate: Double = 0.2

    override fun cast(caster: Entity, victim: Entity) {
        executing = true
        println("${caster.name} 发动了 StarburstStream , 对 ${victim.name} 造成16次连续伤害!")
        repeat(16) { i ->
            Thread.sleep(TimeUnit.SECONDS.toMillis(1) / 4)
            // 注册伤害修正器
            val singleListener = Events.listen<EntityDamageEvent>(property = EventProperty.MONITOR) {
                if (it.entity == victim && it.attacker == caster) {
                    it.damage *= i.toDouble() / 16
                }
            }
            // 伤害
            victim.damage(caster, 0.0)
            // 注销
            Events.unregister(singleListener)
            if (!victim.isLiving) {
                executing = false
                return
            }
        }
        executing = false
    }
}