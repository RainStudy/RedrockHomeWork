package team.redrock.coldrain.second.lv5.entity

import team.redrock.coldrain.second.lv5.attr.EntityAttributeProfile
import team.redrock.coldrain.second.lv5.eventbus.impl.EntityDamageEvent
import team.redrock.coldrain.second.lv5.fix


/**
 * team.redrock.coldrain.second.lv5.entity.Entity
 * 2ed
 *
 * @author 寒雨
 * @since 2021/10/25 1:14
 **/
abstract class Entity(val name: String, val maxHealth: Double) {
    var isLiving = true
    var health = maxHealth
    val attributeData = EntityAttributeProfile()

    fun damage(source: Entity, amount: Double) =
        EntityDamageEvent(source, this, amount).call<EntityDamageEvent>()

    fun showStatus() {
        println("$name 当前剩余血量: ${health.fix()}/$maxHealth")
    }
}