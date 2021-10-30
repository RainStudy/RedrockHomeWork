package team.redrock.coldrain.second.lv5.skill.impl

import team.redrock.coldrain.second.lv5.entity.Entity
import team.redrock.coldrain.second.lv5.skill.ISkill

/**
 * team.redrock.coldrain.second.lv5.skill.impl.EmptySkill
 * 2ed
 *
 * @author 寒雨
 * @since 2021/10/29 15:35
 **/
object EmptySkill : ISkill {

    override var executing: Boolean = false

    override val executeRate: Double
        get() = 0.0

    override fun cast(caster: Entity, victim: Entity) {

    }
}