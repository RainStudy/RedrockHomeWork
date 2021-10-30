package team.redrock.coldrain.second.lv5.eventbus.impl

import team.redrock.coldrain.second.lv5.entity.Entity
import team.redrock.coldrain.second.lv5.eventbus.Event
import team.redrock.coldrain.second.lv5.eventbus.EventCancelable

/**
 * team.redrock.coldrain.second.lv5.eventbus.impl.EntityDamageEvent
 * 2ed
 *
 * @author 寒雨
 * @since 2021/10/25 1:18
 **/
class EntityDamageEvent(val attacker: Entity, val entity: Entity, var damage: Double) : EventCancelable()