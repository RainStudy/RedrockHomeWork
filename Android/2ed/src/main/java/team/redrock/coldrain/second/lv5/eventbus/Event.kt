package team.redrock.coldrain.second.lv5.eventbus

/**
 * team.redrock.coldrain.second.lv5.eventbus.Event
 * 2ed
 *
 * @author 寒雨
 * @since 2021/10/25 0:47
 **/
abstract class Event {
    @Suppress("UNCHECKED_CAST")
    fun <T : Event> call(): T = Events.callEvent(this as T)
}