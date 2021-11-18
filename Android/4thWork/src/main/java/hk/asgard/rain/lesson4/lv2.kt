package hk.asgard.rain.lesson4

import hk.asgard.rain.lesson4.utils.deserializeObject
import hk.asgard.rain.lesson4.utils.readFile
import hk.asgard.rain.lesson4.utils.serializeObject
import hk.asgard.rain.lesson4.utils.writeFile
import java.io.Serializable

/**
 * hk.asgard.rain.lesson4.lv2
 * 4thWork
 *
 * @author 寒雨
 * @since 2021/11/18 14:32
 **/
fun main() {
    writeFile("E:\\text.txt", serializeObject(SerializeObject()))
    println(serializeObject(SerializeObject()))
    println(readFile("E:\\text.txt"))
    val obj = deserializeObject(readFile("E:\\text.txt")) as SerializeObject
    println(obj.number)
}

class SerializeObject : Serializable {
    val number = 1
}