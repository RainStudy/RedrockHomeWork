package team.redrock.coldrain.lesson1

import kotlinx.coroutines.*
import java.util.*
import kotlin.random.Random

/**
 * team.redrock.coldrain.asmtest.Lv2
 * ASMTest
 *
 * @author coldrain
 * @since 2021/10/18
 **/
fun main() {
    // 生成一组随机数
    val array = arrayOfNulls<Int>(7)
    val random = Random(System.currentTimeMillis())
    for (i in array.indices) {
        array[i] = random.nextInt(100)
    }
    println("排序结果:")
    println(sleepSort(array).toTypedArray().contentToString())
    println("请输入要插入的数字")
    val scan = Scanner(System.`in`)
    // 阻塞
    val v = scan.nextInt()
    println("插入结果:")
    println(insert(selectSort(array), v).contentToString())
}

// 选择排序
fun selectSort(array: Array<Int?>): Array<Int?> {
    for (i in array.indices) {
        var minIndex = i
        for (j in (i + 1 until array.size)) {
            if (array[j]!! < array[minIndex]!!) {
                minIndex = j
            }
            if (minIndex != i) {
                val temp = array[minIndex]
                array[minIndex] = array[i]
                array[i] = temp
            }
        }
    }
    return array
}

// 冒泡排序
fun bubbleSort(array: Array<Int?>): Array<Int?> {
    for (i in array.indices) {
        for (j in (0 until array.size - i - 1)) {
            if (array[j]!! > array[j + 1]!!) {
                val temp = array[j + 1]
                array[j + 1] = array[j]
                array[j] = temp
            }
        }
    }
    return array
}

// 插入
fun insert(array: Array<Int?>, value: Int): Array<Int?> {
    for (i in array.indices) {
        if (array[i]!! >= value) {
            return array.copyOf(array.size + 1).apply {
                for (j in array.indices) {
                    if (j >= i) {
                        this[j + 1] = array[j]
                    }
                }
                this[i] = value
            }
        }
    }
    return array.copyOf(array.size + 1).apply {
        this[size - 1] = value
    }
}

// 睡眠排序 kotlin实现
// 需要用到协程库
// kt用多线程写多没意思
fun sleepSort(array: Array<Int?>): List<Int> {
    val result = arrayListOf<Int>()
    val list = arrayListOf<Deferred<Unit>>()
    for (i in array) {
        list.add(GlobalScope.async {
            delay(i!! * 10L)
            result.add(i)
            Unit
        })
    }
    // 堵塞至操作完成
    runBlocking {
        list.forEach {
            it.await()
        }
    }
    return result
}