package team.redrock.coldrain.lesson1.lv5

import com.google.gson.GsonBuilder

/**
 * team.redrock.coldrain.lesson1.lv5.Test
 * Lesson1
 *
 * @author 寒雨
 * @since 2021/10/18 19:26
 **/
fun main() {
    val list = ArrayListImpl<String>()
    list.add("Hello")
    list.add("World")
    println(list.toString())
    list.add(0, "Hello")
    println(list.toString())
    list.remove(2)
    println(list.toString())
    list.add("World")
    list.remove("Hello")
    println(list.toString())
}