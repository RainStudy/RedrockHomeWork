package team.redrock.coldrain.second.third


/**
 * team.redrock.coldrain.second.`3rd`.Lv3
 * 2ed
 *
 * @author 寒雨
 * @since 2021/11/2 1:12
 **/
abstract class King {
    val soldiers: MutableList<Soldier> = ArrayList()

    abstract fun review()

    /**
     * 使用lambda的方式添加Soldier
     *
     * @param func lambda
     * @receiver
     */
    fun addSoldier(func: () -> Unit) {
        soldiers.add(object : Soldier {
            override fun response() {
                func()
            }
        })
    }
    /**
     * Java 实现
     * public void addSoldier(String name, Function func) {
     *      soldiers.add(new Soldier() {
     *          @Override
     *          public void getName() {
     *              return name;
     *          }
     *
     *          @Override
     *          public void response() {
     *              func.execute();
     *          }
     *      });
     * }
     */
}

interface Soldier {
    fun response()
}

object KingImpl : King() {
    init {
        addSoldier {
            println("国王好！")
        }
        addSoldier {
            println("国外威武！")
        }
    }

    override fun review() {
        println("阅兵!")
        soldiers.forEach { it.response() }
    }
}

fun main() {
    KingImpl.review()
}