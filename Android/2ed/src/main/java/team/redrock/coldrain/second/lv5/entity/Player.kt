package team.redrock.coldrain.second.lv5.entity

import team.redrock.coldrain.second.lv5.attr.item.AttrItem
import team.redrock.coldrain.second.lv5.skill.ISkill

/**
 * team.redrock.coldrain.second.lv5.entity.Player
 * 2ed
 *
 * @author 寒雨
 * @since 2021/10/25 20:17
 **/
class Player(
    name: String,
    maxHealth: Double,
    equipments: List<AttrItem>,
    skill: ISkill
    ) : Entity(name, maxHealth), ISkill by skill {

    init {
        // 从玩家武器装备中读取属性
        equipments.forEach {
            it.data.forEach { (k, v) ->
                attributeData.append(k, v)
            }
        }
    }

}