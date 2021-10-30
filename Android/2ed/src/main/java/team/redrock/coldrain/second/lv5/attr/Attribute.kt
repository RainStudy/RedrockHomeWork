package team.redrock.coldrain.second.lv5.attr

import team.redrock.coldrain.second.lv5.eventbus.Event
import team.redrock.coldrain.second.lv5.eventbus.EventProperty
import team.redrock.coldrain.second.lv5.eventbus.Events
import team.redrock.coldrain.second.lv5.eventbus.SingleListener

/**
 * team.redrock.coldrain.second.lv5.attr.Attribute
 * 2ed
 *
 * @author 寒雨
 * @since 2021/10/25 18:27
 **/
class Attribute {
    // 该属性注册的监听器
    val eventHandlers = mutableListOf<SingleListener>()

    var name: String = "null"

    inline fun <reified T: Event> addExecutor(
        ignoreCancelled: Boolean = false,
        property: EventProperty = EventProperty.HIGH,
        crossinline func: (T) -> Unit
    ): Attribute {
        val listener = SingleListener(ignoreCancelled, property, T::class) {
            func.invoke(it as T)
        }
        eventHandlers.add(listener)
        return this
    }

    fun register() {
        registry[name] = this
        eventHandlers.forEach { Events.listen(it) }
        println("属性 $name 注册完毕")
    }

    fun unregister() {
        registry.remove(name)
        eventHandlers.forEach { Events.unregister(it) }
        println("属性 $name 注销完毕")
    }

    companion object {
        val registry = HashMap<String, Attribute>()

        fun builder(name: String): Attribute {
            return Attribute().apply { this.name = name }
        }
    }
}