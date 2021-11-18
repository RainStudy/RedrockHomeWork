package hk.asgard.rain.lesson4.utils

/**
 * hk.asgard.rain.lesson4.utils.Reflection
 * 4thWork
 * 简单好用的反射轮子
 * 通过缓存来优化性能
 * 借鉴开源项目TabooLib部分代码
 *
 * @author 寒雨
 * @since 2021/11/18 10:59
 **/

/**
 * Get property
 * 获取字段
 *
 * @param path 路径 (使用 / 分隔以递归获取)
 */
fun <T> Any.getProperty(path: String): T? {
    val reflectClass = ReflectClass.find(this::class.java)
    val deep = path.indexOf('/')
    if (deep == -1) {
        return reflectClass.findField(path)?.get(this) as T?
    }
    // 递归获取
    return reflectClass.findField(path.substring(0..deep))?.get(this)?.getProperty(path.substring(deep))
}

/**
 * Set property
 * 覆写字段
 *
 * @param path 路径 (使用 / 分隔以递归获取)
 * @param any 值
 */
fun Any.setProperty(path: String, any: Any?) {
    val reflectClass = ReflectClass.find(this::class.java)
    val deep = path.indexOf('/')
    if (deep == -1) {
        reflectClass.findField(path)?.set(this, any)
        return
    }
    // 递归覆写
    reflectClass.findField(path.substring(0..deep))?.get(this)?.setProperty(path.substring(deep), any)
}

/**
 * Invoke method
 * 执行实例方法
 *
 * @param path 方法名称
 * @param args 参数
 * @return 返回值
 */
fun <T> Any.invokeMethod(path: String, vararg args: Any?): T {
    val reflectClass = ReflectClass.find(this::class.java)
    return (reflectClass.findMethod(path, *args) ?: error("Method not found")).invoke(this, *args) as T
}

/**
 * Construct
 * 使用构造器实例化对象
 *
 * @param T 类型
 * @param args 参数
 * @return 实例
 */
fun <T> Class<T>.construct(vararg args: Any?): T {
    return (ReflectClass.find(this::class.java).findConstructor(*args)?.newInstance(args) ?: error("No constructor find")) as T
}

/**
 * Invoke static
 * 执行静态方法
 *
 * @param T 返回值类型
 * @param path 方法名
 * @param args 参数
 * @return 返回值
 */
fun <T> Class<*>.invokeStatic(path: String, vararg args: Any?): T {
    return invokeMethod(path, args)
}