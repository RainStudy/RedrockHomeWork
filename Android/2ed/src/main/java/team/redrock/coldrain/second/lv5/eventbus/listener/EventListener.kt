package team.redrock.coldrain.second.lv5.eventbus.listener

import team.redrock.coldrain.second.lv5.eventbus.EventProperty
import team.redrock.coldrain.second.lv5.eventbus.Events
import team.redrock.coldrain.second.lv5.eventbus.impl.EntityDamageEvent
import team.redrock.coldrain.second.lv5.eventbus.impl.EntityDeathEvent
import team.redrock.coldrain.second.lv5.fix
import team.redrock.coldrain.second.lv5.skill.ISkill
import kotlin.random.Random

/**
 * team.redrock.coldrain.second.lv5.eventbus.listener.EventListener
 * 2ed
 *
 * @author 寒雨
 * @since 2021/10/25 13:09
 **/
object EventListener {
    fun init() {
        // 死亡信息广播
        Events.listen<EntityDeathEvent> {
            println("${it.entity.name} 死亡!")
        }
        // 伤害逻辑处理
        Events.listen<EntityDamageEvent>(property = EventProperty.MONITOR) {
            it.damage = it.damage.fix()
            if (it.damage <= 0) {
                println("来自 ${it.attacker.name} 的攻击被 ${it.entity.name} 完全抵挡")
                return@listen
            }
            println("${it.attacker.name} 的攻击对 ${it.entity.name} 造成了 ${it.damage} 点伤害")
            val hp = (it.entity.health - it.damage).coerceAtLeast(0.0)
            it.entity.health = hp
            if (it.entity.health == 0.0) {
                it.entity.isLiving = EntityDeathEvent(it.entity).call<EntityDeathEvent>().isCancelled
                if (it.entity.isLiving) {
                    // 起死回生
                    it.entity.health = 1.0
                }
            }
            // 如果生物存活，展示血量状态
            if (it.entity.isLiving) it.entity.showStatus()
        }
        // 伤害闪避反击机制
        Events.listen<EntityDamageEvent>(ignoreCancelled = true, property = EventProperty.MONITOR) {
            if (it.isCancelled) {
                println("来自 ${it.attacker.name} 的攻击被 ${it.entity.name} 闪躲")
                // 反击
                println("${it.entity.name} 反击了 ${it.attacker.name} !")
                it.attacker.damage(it.entity, 10.0)
            }
        }
        // 技能触发机制
        Events.listen<EntityDamageEvent>(property = EventProperty.MONITOR) {
            val entity = it.attacker
            if (entity is ISkill && !entity.executing && entity.executeRate >= Random.nextDouble()) {
                it.isCancelled = true
                entity.cast(entity, it.entity)
            }
        }
    }
}