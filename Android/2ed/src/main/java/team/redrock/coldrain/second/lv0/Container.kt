package team.redrock.coldrain.second.lv0

/**
 * team.redrock.coldrain.`2ed`.lv0.Inventory
 * 2ed
 *
 * @author 寒雨
 * @since 2021/10/24 17:50
 **/
open class Container(val name: String)

class Oven : Container("洗衣机") {
    /**
     * 烤
     *
     * @param animal 动物
     */
    fun toast(animal: Animal) {
        println("烤箱烤 ${animal.name}")
    }
}


class Washer : Container("洗衣机") {
    /**
     * 洗
     *
     * @param animal 动物
     */
    fun wash(animal: Animal) {
        println("洗衣机洗 ${animal.name}")
    }
}