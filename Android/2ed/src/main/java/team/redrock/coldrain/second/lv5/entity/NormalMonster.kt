package team.redrock.coldrain.second.lv5.entity

import team.redrock.coldrain.second.lv5.fix
import kotlin.random.Random

/**
 * team.redrock.coldrain.second.lv5.entity.NormalMonster
 * 2ed
 *
 * @author 寒雨
 * @since 2021/10/27 0:06
 **/
class NormalMonster(name: String) : Entity(name, 250.0) {
    init {
        attributeData.apply {
            append("Damage", arrayOf(Random.nextDouble(10.0, 20.0).fix(), Random.nextDouble(20.0, 30.0).fix(), 0.0))
            append("Armor", arrayOf(Random.nextDouble(10.0, 20.0).fix(), Random.nextDouble(20.0, 30.0).fix(), 0.0))
            append("Dodge", arrayOf(Random.nextDouble(0.0, 5.0).fix(), Random.nextDouble(5.0, 10.0).fix(), 0.0))
        }
    }
}