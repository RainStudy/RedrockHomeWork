package team.redrock.coldrain.lesson1.lv4

/**
 * team.redrock.coldrain.lesson1.lv4.Dish
 * Lesson1
 *
 * @author 寒雨
 * @since 2021/10/18 16:45
 **/
enum class Dish(val id: Int, val display_name: String, val price: Double) {
    SPICY_CHICKEN(1, "辣子鸡丁", 38.0),
    BOIL_MEET(2, "水煮肉片", 22.0),
    TENDERLOIN(3, "糖醋里脊", 18.0),
    POT_BEEF(4, "干锅牛肉", 38.0),
    POT_RIBS(5, "干锅排骨", 29.0)
}