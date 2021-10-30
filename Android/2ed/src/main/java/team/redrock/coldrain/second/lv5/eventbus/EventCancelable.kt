package team.redrock.coldrain.second.lv5.eventbus

/**
 * team.redrock.coldrain.second.lv5.eventbus.EventCancelable
 * 2ed
 *
 * @author 寒雨
 * @since 2021/10/25 12:55
 **/
abstract class EventCancelable : Event() {
    var isCancelled = false
}