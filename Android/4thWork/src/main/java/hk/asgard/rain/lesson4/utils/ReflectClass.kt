package hk.asgard.rain.lesson4.utils

import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.util.concurrent.ConcurrentHashMap

/**
 * hk.asgard.rain.lesson4.utils.ReflectClass
 * 4thWork
 * 用于反射的缓存类
 *
 * @author 寒雨
 * @since 2021/11/18 11:11
 **/
class ReflectClass(val clazz: Class<*>) {
    // 超类缓存
    private var superclass: ReflectClass? = null
    // 接口缓存
    private var interfaces = ArrayList<ReflectClass>()

    // 字段缓存
    private val savingField = ArrayList<Field>()
    // 方法缓存
    private val savingMethod = ArrayList<Method>()
    // 构造器缓存
    private val savingConstructor = ArrayList<Constructor<*>>()

    init {
        kotlin.runCatching {
            savingField.addAll(clazz.declaredFields.map {
                it.isAccessible = true
                it
            })
            savingMethod.addAll(clazz.declaredMethods.map {
                it.isAccessible = true
                it
            })
            savingConstructor.addAll(clazz.declaredConstructors.map {
                it.isAccessible = true
                it
            })
            if (clazz.superclass != null && clazz.superclass != Object::class.java) {
                superclass = ReflectClass(clazz.superclass)
            }
            clazz.interfaces.forEach {
                interfaces.add(ReflectClass(it))
            }
        }
    }

    // 获取字段
    fun findField(f: String): Field? {
        savingField.firstOrNull { it.name == f }?.run {
            return this
        }
        superclass?.findField(f)?.run {
            return this
        }
        interfaces.forEach {
            it.findField(f)?.run {
                return this
            }
        }
        return null
    }

    // 获取方法
    fun findMethod(m: String, vararg parameter: Any?): Method? {
        // 优先从当前类中获取
        savingMethod.firstOrNull { it.name == m && compare(it.parameterTypes, parameter.map { p -> p?.javaClass }.toTypedArray())}?.run {
            return this
        }
        // 从超类中获取
        superclass?.findMethod(m, *parameter)?.run {
            return this
        }
        // 从接口中获取
        interfaces.forEach {
            it.findMethod(m, *parameter)?.run {
                return this
            }
        }
        return null
    }

    private fun compare(primary: Array<Class<*>>, secondary: Array<Class<*>?>): Boolean {
        if (primary.size != secondary.size) {
            return false
        }
        for (index in primary.indices) {
            val primaryClass = primary[index].nonPrimitive()
            val secondaryClass = secondary[index]?.nonPrimitive() ?: continue
            if (primaryClass == secondaryClass || primaryClass.isAssignableFrom(secondaryClass)) {
                continue
            }
            return false
        }
        return true
    }

    // 获取构造器
    fun findConstructor(vararg parameter: Any?): Constructor<*>? {
        savingConstructor.firstOrNull {
            if (it.parameterCount == parameter.size) {
                var checked = true
                it.parameterTypes.forEachIndexed { index, p ->
                    if (parameter[index] != null && !p.nonPrimitive().isInstance(parameter[index])) {
                        checked = false
                    }
                }
                return@firstOrNull checked
            }
            return@firstOrNull false
        }?.run {
            return this
        }
        return null
    }

    // 取得装箱之后的类型
    private fun Class<*>.nonPrimitive(): Class<*> {
        return when {
            this == Integer.TYPE -> Integer::class.java
            this == Character.TYPE -> Character::class.java
            this == java.lang.Byte.TYPE -> java.lang.Byte::class.java
            this == java.lang.Long.TYPE -> java.lang.Long::class.java
            this == java.lang.Double.TYPE -> java.lang.Double::class.java
            this == java.lang.Float.TYPE -> java.lang.Float::class.java
            this == java.lang.Short.TYPE -> java.lang.Short::class.java
            this == java.lang.Boolean.TYPE -> java.lang.Boolean::class.java
            else -> this
        }
    }
    companion object {
        val savingClass = ConcurrentHashMap<String, ReflectClass>()

        fun find(clazz: Class<*>) = savingClass.computeIfAbsent(clazz.name) { ReflectClass(clazz) }
    }
}