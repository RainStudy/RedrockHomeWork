package team.redrock.coldrain.second.lv5.attr.item


/**
 * team.redrock.coldrain.second.lv5.attr.item.AttrItem
 * 2ed
 *
 * @author 寒雨
 * @since 2021/10/25 20:19
 **/
class AttrItem(val name: String, val data: Map<String, Array<Double>>) {
    companion object {
        // 武器
        // 冥杀炎魔刀 100~200点伤害 稳健的选择
        val SWORD = AttrItem("冥杀炎魔刀", mapOf("Damage" to arrayOf(100.0, 200.0, 0.0)))
        // 九天雷霆斧 25~400点伤害 赌徒的选择
        val AXE = AttrItem("九天雷霆斧", mapOf("Damage" to arrayOf(25.0, 400.0, 0.0)))
        // 重剑无锋 10% 暴击率 2000暴击伤害 赌狗的选择
        val NO_SHARP = AttrItem("重剑无锋", mapOf("CriticalChance" to arrayOf(10.0, 10.0, 0.0), "CriticalDamage" to arrayOf(2000.0, 2000.0, 0.0)))

        // 饰品
        // 虚音空石 +10% 闪避概率
        val VOID_STONE = AttrItem("虚音空石", mapOf("Dodge" to arrayOf(10.0, 10.0, 0.0)))
        // 钢铁之心 +10~20 护甲
        val IRON_HEART = AttrItem("钢铁之心", mapOf("Armor" to arrayOf(10.0, 20.0, 0.0)))
        // 直死之魔眼 +5~10% 暴击概率
        val MAGIC_EYE = AttrItem("直死之魔眼", mapOf("CriticalChance" to arrayOf(5.0, 10.0, 0.0)))
    }
}

