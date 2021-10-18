package team.redrock.coldrain.lesson1.lv5

/**
 * team.redrock.coldrain.lesson1.lv5.ArrayListImpl
 * Lesson1
 * ArrayList简单功能实现
 *
 * @author 寒雨
 * @since 2021/10/18 18:14
 **/
class ArrayListImpl<T> {
    private var elementData: Array<Any?> = arrayOf()

    val size: Int
        get() = elementData.size

    /**
     * 添加元素
     *
     * @param element 元素
     */
    fun add(element: T) {
        val newData = arrayOfNulls<Any>(size + 1)
        elementData.forEachIndexed { i, any ->
            newData[i] = any
        }
        newData[size] = element
        elementData = newData
    }

    /**
     * 向指定位置插入元素
     *
     * @param index 索引
     * @param element 元素
     */
    fun add(index: Int, element: T) {
        val newData = arrayOfNulls<Any>(size + 1)
        var meet = false
        elementData.forEachIndexed { i, any ->
            if (index == i && !meet) {
                newData[i] = element
                meet = true
            }
            newData[if (meet) i+1 else i] = any
        }
        elementData = newData
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
        return elementData[index] as T
    }

    /**
     * 根据索引删除元素
     *
     * @param index 索引
     */
    fun remove(index: Int) {
        if (index >= size) error("数组越界")
        val newData = arrayOfNulls<Any>(elementData.size - 1)
        elementData.forEachIndexed { i, any ->
            var meet = false
            if (i < newData.size) {
                if (i == index) {
                    meet = true
                    return@forEachIndexed
                }
                newData[if (meet) i - 1 else i] = any
            }
        }
        elementData = newData
    }

    /**
     * 删除数组中的指定元素
     *
     * @param element 元素
     */
    fun remove(element: T) {
        // 取得数组中相同元素数量
        var elementNum = 0
        elementData.forEach {
            if (it == element) {
                elementNum++
            }
        }
        val newData = arrayOfNulls<Any>(elementData.size - elementNum)
        var finds = 0
        elementData.forEachIndexed { i, any ->
            if (any == element) {
                finds++
                return@forEachIndexed
            }
            newData[i - finds] = any
        }
        elementData = newData
    }

    fun toContentString(): String {
        return elementData.contentDeepToString()
    }
}