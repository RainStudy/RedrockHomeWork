package team.redrock.coldrain.lesson1.lv5

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
    println(list.toContentString())
    list.remove("Hello")
    println(list.toContentString())
    list.add(0, "Hello")
    println(list.toContentString())
    list.remove(1)
    println(list.toContentString())
    println(list.get(0))
}