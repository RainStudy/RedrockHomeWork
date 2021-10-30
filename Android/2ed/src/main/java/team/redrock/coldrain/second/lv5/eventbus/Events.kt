package team.redrock.coldrain.second.lv5.eventbus

import java.util.*
import kotlin.collections.HashMap
import kotlin.reflect.KClass

/**
 * team.redrock.coldrain.second.lv5.eventbus.Events
 * 2ed
 * 简单的EventBus实现
 *
 * @author 寒雨
 * @since 2021/10/25 0:46
 **/
object Events {

    private val registry: HashMap<KClass<*>, LinkedList<SingleListener>> = HashMap()

    /**
     * 注册事件监听器
     *
     * @param T 事件类型
     * @param ignoreCancelled 是否忽略事件取消
     * @param property 优先级
     * @param func 事件处理
     * @receiver
     */
    inline fun <reified T : Event> listen(
        ignoreCancelled: Boolean = false,
        property: EventProperty = EventProperty.NORMAL,
        crossinline func: (T) -> Unit
    ): SingleListener {
        val listener = SingleListener(ignoreCancelled, property, type = T::class) {
            func.invoke(it as T)
        }
        return listener.also { listen(it) }
    }

    fun listen(listener: SingleListener) {
        if (registry.containsKey(listener.type)) {
            // 进行优先级排序
            registry[listener.type]!!.add(listener)
            registry[listener.type]!!.sortBy { it.property.property }
        } else registry[listener.type] = LinkedList<SingleListener>().apply { add(listener) }
    }

    fun <T : Event> callEvent(event: T): T {
        synchronized(Unit) {
            if (registry.containsKey(event::class)) {
                val cancelable = event is EventCancelable
                registry[event::class]!!.forEach {
                    if (cancelable && (event as EventCancelable).isCancelled && !it.ignoreCancelled) {
                        return@forEach
                    }
                    it.func.invoke(event)
                }
            }
            return event
        }
    }

    fun <T : Event> unregisterAll(eventClass: KClass<T>) {
        registry.remove(eventClass)
    }

    /**
     * 注销特定监听器
     *
     * @param singleListener 监听器
     */
    fun unregister(singleListener: SingleListener) {
        registry[singleListener.type]?.remove(singleListener)
    }
}

enum class EventProperty(val property: Int) {
    LOWEST(1), LOW(2), NORMAL(3), HIGH(4), HIGHEST(5), MONITOR(6)
}

class SingleListener(
    val ignoreCancelled: Boolean,
    val property: EventProperty,
    val type: KClass<*>,
    val func: (Any) -> Unit
)