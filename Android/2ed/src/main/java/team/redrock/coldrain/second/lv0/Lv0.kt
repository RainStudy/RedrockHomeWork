package team.redrock.coldrain.second.lv0

/**
 * team.redrock.coldrain.`2ed`.lv0
 * 2ed
 *
 * @author 寒雨
 * @since 2021/10/24 17:54
 **/
fun main() {
    val refrigerator = Container("冰箱")
    val washer = Washer()
    val oven = Oven()
    val closet = Container("衣柜")

    val elephant = Elephant()
    val tiger = Tiger()
    val monkey = Monkey()

    elephant.enter(refrigerator)
    elephant.enter(washer)
    elephant.enter(oven)
    elephant.enter(closet)

    tiger.enter(refrigerator)
    tiger.enter(closet)
    monkey.enter(oven)

    oven.toast(elephant)
    washer.wash(tiger)
}

