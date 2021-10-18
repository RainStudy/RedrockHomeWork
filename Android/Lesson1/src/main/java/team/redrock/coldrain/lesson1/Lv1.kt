package team.redrock.coldrain.lesson1

/**
 * team.redrock.coldrain.asmtest.Lv1
 * ASMTest
 *
 * @author coldrain
 * @since 2021/10/18
 **/
fun main() {
    // 乘法表
    table()
    // 五角星
    star()
}

fun table() {
    for(i in 1..9) {
        for (j in 1..i) {
            print("$j * $i = ${j * i} ")
        }
        println()
    }
}

// 网上的思路，自己实在想不出来了
fun star() {
    val radius = 50
    val topHeight = 6
    val bottomHeight = 25
    for (i in 1..(topHeight + bottomHeight)) {
        for (j in 1..radius) {
            if (i <= topHeight) {
                print(if (j >= (radius/2 + 1) + 1 -i && j<=radius/2 + 1 - 1 +i) "*" else "-")
            }
            if (i in (topHeight + 1..bottomHeight)) {
                val str = if (j in (radius/2 + 1 + 2 - i..radius - 3 * (i - topHeight)) || j in (3 * (i - topHeight)..radius/2 + 1 - 1 + i)) "*" else "-"
                print(str)
            }
        }
        println()
    }
}