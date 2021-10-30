package team.redrock.coldrain.second.lv5.attr

import kotlin.random.Random

/**
 * team.redrock.coldrain.`2ed`.lv5.EntityAttributeProfile
 * 2ed
 * 实体属性容器
 *
 * @author 寒雨
 * @since 2021/10/25 0:43
 **/
class EntityAttributeProfile {
    // min max percent
    private val data = HashMap<String, Array<Double>>()

    /**
     * 获取计算后的属性值
     *
     * @param name 属性
     * @return
     */
    fun getFixed(name: String): Double {
        if (!data.containsKey(name)) {
            return 0.0
        }
        return (data[name]!![0] + (data[name]!![1] - data[name]!![0]) * Random.nextDouble()) * (1 + (data[name]!![2] / 100))
    }

    /**
     * 获取原始属性数据
     *
     * @param name 属性
     * @return
     */
    fun getOrigin(name: String): Array<Double> {
        if (!data.containsKey(name)) {
            return arrayOf(0.0, 0.0, 0.0)
        }
        return data[name]!!
    }

    /**
     * 增加属性值
     *
     * @param name 属性
     * @param array 值
     */
    fun append(name: String, array: Array<Double>) {
        data[name] = (data[name] ?: arrayOf(0.0,0.0,0.0)).apply {
            this[0] += array[0]
            this[1] += array[1]
            this[2] += array[2]
        }
    }

    /**
     * 设置属性值
     *
     * @param name 属性
     * @param array 值
     */
    fun set(name: String, array: Array<Double>) {
        data[name] = array
    }
}