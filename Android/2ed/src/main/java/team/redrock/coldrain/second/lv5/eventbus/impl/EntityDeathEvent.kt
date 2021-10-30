package team.redrock.coldrain.second.lv5.eventbus.impl

import team.redrock.coldrain.second.lv5.entity.Entity
import team.redrock.coldrain.second.lv5.eventbus.EventCancelable

/**
 * team.redrock.coldrain.second.lv5.eventbus.impl.EntityDeathEvent
 * 2ed
 *
 * @author 寒雨
 * @since 2021/10/25 12:54
 **/
class EntityDeathEvent(val entity: Entity) : EventCancelable()