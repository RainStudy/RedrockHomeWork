package team.redrock.coldrain.second.lv5

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * team.redrock.coldrain.second.lv5.Util
 * 2ed
 *
 * @author 寒雨
 * @since 2021/10/25 21:07
 **/
fun Double.fix(): Double {
    return BigDecimal(this).setScale(1, RoundingMode.HALF_UP).toDouble()
}