package hk.asgard.rain.lesson4.lv4

import hk.asgard.rain.lesson4.utils.setProperty
import javassist.ClassPool
import javassist.util.HotSwapper

/**
 * hk.asgard.rain.lesson4.Lv4
 * 4thWork
 * 三种开挂解决方案
 * 反射 javassist生成新类 热替换
 *
 * @author 寒雨
 * @since 2021/11/18 10:58
 **/
fun main() {
    var hero: Entity = Hero()
    val boss = Boss()
//    bypass(hero)
//    hero = javassistByPass(hero)
    hotSwap()
    if (boss.speed > hero.speed) {
        // Boss 先手 秒杀
        println("英雄被Boss秒杀啦")
    } else {
        // hero 先手
        if (hero.damage >= boss.health) {
            println("英雄开挂秒杀了Boss")
        } else {
            println("英雄先手没能干掉Boss，被Boss反杀")
        }
    }
}

/**
 * 开挂(反射)
 */
fun bypass(hero: Entity) {
    hero.setProperty("damage", 999999)
    hero.setProperty("speed", 400)
}

// 用javassist生成新类并加载
fun javassistByPass(hero: Entity): Entity {
    // 获取ctClass
    val ctClass = ClassPool.getDefault().get("hk.asgard.rain.lesson4.lv4.Hero")
    // 改名，否则重复加载会抛异常
    ctClass.name = "hk.asgard.rain.lesson4.lv4.HeroEdited"
    // 从ctClass获取方法
    val ctMethodDamage = ctClass.getDeclaredMethod("getDamage")
    val ctMethodSpeed = ctClass.getDeclaredMethod("getSpeed")
    ctMethodDamage.setBody("""{
        return 999999L;
    }""")
    ctMethodSpeed.setBody("""{
        return 400;
    }""")
    return ctClass.toClass().newInstance() as Entity
}

// 热替换
// 需要jvm选项 -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000 并且implementation tools.jar
fun hotSwap() {
    // 获取ctClass
    val ctClass = ClassPool.getDefault().get("hk.asgard.rain.lesson4.lv4.Hero")
    // 从ctClass获取方法
    val ctMethodDamage = ctClass.getDeclaredMethod("getDamage")
    val ctMethodSpeed = ctClass.getDeclaredMethod("getSpeed")
    ctMethodDamage.setBody("""{
        return 999999L;
    }""")
    ctMethodSpeed.setBody("""{
        return 400;
    }""")
    val swap = HotSwapper(8000)
    swap.reload("hk.asgard.rain.lesson4.lv4.Hero", ctClass.toBytecode())
}