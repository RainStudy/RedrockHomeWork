package hk.asgard.rain.lesson4.lv4

/**
 * hk.asgard.rain.lesson4.lv4.Boss
 * 4thWork
 *
 * @author 寒雨
 * @since 2021/11/18 12:17
 **/
interface Entity {
    val health: Long
    val damage: Long
    val speed: Int
}

class Boss : Entity {
    override val health: Long = 2000

    override val damage: Long = 99999

    override val speed: Int = 250
}

class Hero : Entity {
    override val health: Long = 200

    override val damage: Long = 1

    override val speed: Int = 1
}