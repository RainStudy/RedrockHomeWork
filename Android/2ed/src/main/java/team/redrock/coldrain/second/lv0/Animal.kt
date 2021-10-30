package team.redrock.coldrain.second.lv0

/**
 * team.redrock.coldrain.`2ed`.lv0.Animal
 * 2ed
 *
 * @author 寒雨
 * @since 2021/10/25 0:22
 **/
open class Animal(val name: String) {
    fun enter(container: Container) {
        println("$name 被装进了 ${container.name}")
    }
}

class Elephant : Animal("大象")
class Tiger : Animal("老虎")
class Monkey : Animal("猴子")
