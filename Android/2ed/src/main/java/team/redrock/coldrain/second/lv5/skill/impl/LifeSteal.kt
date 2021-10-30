package team.redrock.coldrain.second.lv5.skill.impl

import team.redrock.coldrain.second.lv5.entity.Entity
import team.redrock.coldrain.second.lv5.eventbus.impl.EntityDamageEvent
import team.redrock.coldrain.second.lv5.skill.ISkill

/**
 * team.redrock.coldrain.second.lv5.skill.impl.LifeSteal
 * 2ed
 *
 * @author 寒雨
 * @since 2021/10/27 0:37
 **/
object LifeSteal : ISkill {


    override var executing: Boolean = false

    override val executeRate: Double = 0.3

    override fun cast(caster: Entity, victim: Entity) {
        executing = true
        println("${caster.name} 发动了生命偷取技能")
        val v = victim.damage(caster, 0.0).call<EntityDamageEvent>().damage
        caster.health = (caster.health + v).coerceAtMost(caster.maxHealth)
        println("${caster.name} 回复了 $v 点生命值")
        executing = false
    }
}