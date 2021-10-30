package team.redrock.coldrain.second.lv5.skill

import team.redrock.coldrain.second.lv5.entity.Entity

/**
 * team.redrock.coldrain.second.lv5.skill.ExecutableSkill
 * 2ed
 *
 * @author 寒雨
 * @since 2021/10/27 0:07
 **/
interface ISkill {
    /**
     * 是否正在执行
     */
    var executing: Boolean

    /**
     * 执行概率
     */
    val executeRate: Double

    /**
     * 发动技能
     *
     * @param caster 发动技能者
     * @param victim 敌方生物
     */
    fun cast(caster: Entity, victim: Entity)
}