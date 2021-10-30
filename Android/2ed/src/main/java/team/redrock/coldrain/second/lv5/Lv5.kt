package team.redrock.coldrain.second.lv5

import team.redrock.coldrain.second.lv5.attr.Attributes
import team.redrock.coldrain.second.lv5.attr.item.AttrItem
import team.redrock.coldrain.second.lv5.entity.Arios
import team.redrock.coldrain.second.lv5.entity.Entity
import team.redrock.coldrain.second.lv5.entity.NormalMonster
import team.redrock.coldrain.second.lv5.entity.Player
import team.redrock.coldrain.second.lv5.eventbus.listener.EventListener
import team.redrock.coldrain.second.lv5.skill.impl.EmptySkill
import team.redrock.coldrain.second.lv5.skill.impl.LifeSteal
import team.redrock.coldrain.second.lv5.skill.impl.StarburstStream
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.random.Random

/**
 * team.redrock.coldrain.`2ed`.lv5.Lv5
 * 2ed
 *
 * @author 寒雨
 * @since 2021/10/25 0:39
 **/
fun main() {
    // 部署属性/监听器
    Attributes.init()
    EventListener.init()
    val scan = Scanner(System.`in`)
    // 输入玩家名称
    println("请输入玩家名称:")
    val name = scan.next()
    println("输入初始血量: ")
    val health = scan.nextInt()
    // 选择武器装备
    val equipments = arrayListOf<AttrItem>()
    println("选择你的武器:")
    println("1. 冥杀炎魔刀 100~200点伤害 稳健的选择")
    println("2. 九天雷霆斧 25~400点伤害 赌徒的选择")
    println("3. 重剑无锋 10% 暴击率 2000暴击伤害 赌狗的选择")
    // 添加武器
    equipments.add(
        when (scan.nextInt()) {
            1 -> AttrItem.SWORD
            2 -> AttrItem.AXE
            3 -> AttrItem.NO_SHARP
            else -> error("不存在的武器")
        }
    )
    // 选择饰品
    println("选择你的饰品:")
    println("1. 虚音空石 +10% 闪避概率")
    println("2. 钢铁之心 +10~20 护甲")
    println("3. 直死之魔眼 +5~10% 暴击概率")
    println("others. 我很强,不需要饰品")
    when (scan.nextInt()) {
        1 -> AttrItem.VOID_STONE
        2 -> AttrItem.IRON_HEART
        3 -> AttrItem.MAGIC_EYE
        else -> null
    }?.let {
        equipments.add(it)
    }
    println("选择你的技能:")
    println("1. 生命偷取 每次攻击 30% 概率偷取敌方生命值")
    println("2. 星爆气流斩 每次攻击 10% 发动, 对敌方造成16次连续斩击，造成大量伤害")
    println("others. 我很强,不需要技能")
    val skill = when (scan.nextInt()) {
        1 -> LifeSteal
        2 -> StarburstStream
        else -> EmptySkill
    }
    // 实例化玩家对象
    val player = Player(name, health.toDouble(), equipments, skill)
    // 第一轮战斗
    println("进入第一轮战斗")
    val enemies = arrayListOf<Entity>()
    val amount = Random.nextInt(3, 6)
    println("你遭遇了 $amount 只小兵...")
    repeat(amount) {
        enemies.add(NormalMonster("小兵 ${it + 1}号"))
    }
    val flag = Random.nextInt(100)
    if (flag >= 50) {
        println("玩家处于优势地位,优先攻击")
        enemies[Random.nextInt(enemies.size)].damage(player, 0.0)
    } else println("怪物处于优势地位,优先攻击")
    while (enemies.isNotEmpty()) {
        enemies.toTypedArray().forEach {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1L))
            player.damage(it, 0.0)
            if (!player.isLiving) {
                println("你死了...游戏结束")
                return
            }
        }
        Thread.sleep(TimeUnit.SECONDS.toMillis(1L))
        val entity = enemies[Random.nextInt(enemies.size)]
        entity.damage(player, 0.0)
        if (!entity.isLiving) {
            enemies.remove(entity)
            println("剩余怪物 ${enemies.size} 只")
        }
    }
    val boss = Arios()
    // BOSS强制先手
    // 开打之前先犯二
    boss.tell()
    while (boss.isLiving) {
        Thread.sleep(TimeUnit.SECONDS.toMillis(1L))
        player.damage(boss, 0.0)
        if (!player.isLiving) {
            println("你死了...游戏结束")
            return
        }
        Thread.sleep(TimeUnit.SECONDS.toMillis(1L))
        boss.damage(player, 0.0)
    }
    println("战斗胜利!")
}