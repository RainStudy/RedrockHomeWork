package team.redrock.coldrain.lesson1

import kotlin.random.Random

/**
 * team.redrock.coldrain.lesson1.Lv3
 * Lesson1
 *
 * @author coldrain
 * @since 2021/10/18
 **/
val random = Random(System.currentTimeMillis())

fun main() {
    val v1 = generate(4)
    v1.print()
    println()
    val v2 = generate(4)
    v2.print()
    println()
    val result = v1.multiply(v2)
    result.print()
    var sum = 0
    for (i in result.indices) {
        sum += result.getDeep(i, i)!!
    }
    println("对称线之和: $sum")
}

fun Array<Array<Int?>?>.multiply(append: Array<Array<Int?>?>): Array<Array<Int?>?> {
    val array = this
    val emptyArray = zeroArray(this.size)
    for (i in array.indices) {
        for (j in array.indices) {
            for (k in array.indices) {
                emptyArray[i]!![j] = (array.getDeep(i, k)!! * append.getDeep(k, j)!!) + emptyArray.getDeep(i, j)!!
            }
        }
    }
    return emptyArray
}

// 生成一个全为0的二维数组
fun zeroArray(size: Int): Array<Array<Int?>?> {
    val array = arrayOfNulls<Array<Int?>>(size)
    for (i in array.indices) {
        array[i] = arrayOfNulls(size)
        for (j in array.indices) {
            array[i]!![j] = 0
        }
    }
    return array
}

/**
 * 生成一个二维数组
 * 他们构成了一个对称矩阵
 */
fun generate(width: Int): Array<Array<Int?>?> {
    val array = arrayOfNulls<Array<Int?>>(width)
    for (i in 0 until width) {
        // 顺便设置对称线上的点
        array[i] = arrayOfNulls<Int>(width).apply { set(i, random.nextInt(10)) }
    }
    for (a in array.indices) {
        for (i in 0 until a) {
            val r = random.nextInt(10)
            array.putDeep(a, i, r)
            array.putDeep(i, a, r)
        }
    }
    return array
}

fun Array<Array<Int?>?>.putDeep(x: Int, y: Int, value: Int) {
    set(x, get(x)!!.apply { set(y, value) })
}

fun Array<Array<Int?>?>.getDeep(x: Int, y: Int): Int? {
    return get(x)!![y]
}

fun Array<Array<Int?>?>.print() {
    forEach {
        it!!.forEach {
            print("$it\t\t")
        }
        println()
    }
}
