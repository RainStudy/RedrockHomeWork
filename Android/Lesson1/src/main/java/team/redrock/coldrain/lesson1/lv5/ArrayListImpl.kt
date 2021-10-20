package team.redrock.coldrain.lesson1.lv5

import com.google.gson.Gson

/**
 * team.redrock.coldrain.lesson1.lv5.ArrayListImpl
 * Lesson1
 * ArrayList简单功能实现
 *
 * @author 寒雨
 * @since 2021/10/18 18:14
 **/
class ArrayListImpl<T> {
    private val gson = Gson()
    private var elementData: Array<Any?> = arrayOfNulls(10)

    var size: Int = 0
        private set

    /**
     * 添加元素
     *
     * @param element 元素
     */
    fun add(element: T) {
        expandIfOutOfBounds()
        elementData[size - 1] = element
    }

    /**
     * 向指定位置插入元素
     *
     * @param index 索引
     * @param element 元素
     */
    fun add(index: Int, element: T) {
        if (index >= size) error("数组越界")
        expandIfOutOfBounds()
        var temp: Any? = elementData[index]
        elementData[index] = element
        for (i in (index + 1) until elementData.size) {
            val temp1 = elementData[i]
            elementData[i] = temp
            temp = temp1
        }
    }

    /**
     * 通过索引获取元素
     * 若索引大于自身大小则触发ArrayOutOfBoundsException
     *
     * @param index 索引
     * @return 元素
     */
    @Suppress("UNCHECKED_CAST")
    fun get(index: Int): T {
        if (index >= size) error("数组越界")
        return elementData[index] as T
    }

    /**
     * 根据索引删除元素
     *
     * @param index 索引
     */
    fun remove(index: Int) {
        if (index >= size) error("数组越界")
        elementData[index] = null
        for (i in (index + 1)..size) {
            elementData[i] = elementData[i + 1]
        }
        size--
    }

    /**
     * 删除数组中的指定元素
     *
     * @param element 元素
     */
    fun remove(element: T) {
        var tempSize = size
        for (i in (0 until size)) {
            if (element == elementData[i]) {
                elementData[i] = null
            }
        }
        for (i in (0 until size)) {
            if (elementData[i] == null) {
                for (j in (i + 1..tempSize)) {
                    elementData[j - 1] = elementData[j]
                }
                tempSize--
            }
        }
        size = tempSize
    }

    override fun toString(): String {
        return gson.toJson(elementData.filterNotNull())
    }

    private fun expandIfOutOfBounds() {
        // 自动扩容1.5倍
        size++
        if (size > elementData.size) {
            val newSize = if (elementData.size * 3 / 2 > elementData.size + 1) elementData.size * 3 / 2 else elementData.size + 1
            val newData = arrayOfNulls<Any>(newSize)
            elementData.forEachIndexed { index, any ->
                newData[index] = any
            }
            elementData = newData
        }
    }
}