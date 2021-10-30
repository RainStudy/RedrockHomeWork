package team.redrock.coldrain.second.lv5.attr

import team.redrock.coldrain.second.lv5.eventbus.impl.EntityDamageEvent
import team.redrock.coldrain.second.lv5.eventbus.impl.EntityDeathEvent
import kotlin.random.Random

/**
 * team.redrock.coldrain.second.lv5.attr.Attributes
 * 2ed
 *
 * @author 寒雨
 * @since 2021/10/25 20:20
 **/
object Attributes {
    fun init() {
        Attribute.builder("Damage")
            .addExecutor<EntityDamageEvent> {
                val damage = it.attacker.attributeData.getFixed("Damage")
                it.damage += damage
            }.register()
        Attribute.builder("Armor")
            .addExecutor<EntityDamageEvent> {
                it.damage -= it.entity.attributeData.getFixed("Armor")
            }.register()
        Attribute.builder("Dodge")
            .addExecutor<EntityDamageEvent> {
                if (Random.nextDouble() <= it.entity.attributeData.getFixed("Dodge") / 100) {
                    it.isCancelled = true
                }
            }.register()
        Attribute.builder("Nirvana")
            .addExecutor<EntityDeathEvent> {
                if (Random.nextDouble() <= it.entity.attributeData.getFixed("Nirvana") / 100) {
                    it.isCancelled = true
                }
            }.register()
        Attribute.builder("CriticalChance")
            .addExecutor<EntityDamageEvent> {
                if (Random.nextDouble() <= it.attacker.attributeData.getFixed("CriticalChance") / 100) {
                    val value = it.attacker.attributeData.getFixed("CriticalDamage")
                    it.damage += value
                    println("暴击! 额外造成 $value 点伤害!")
                }
            }.register()
        Attribute.builder("CriticalDamage")
            .register()
        }
}