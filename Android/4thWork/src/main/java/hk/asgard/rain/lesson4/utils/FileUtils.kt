package hk.asgard.rain.lesson4.utils

import java.io.*
import java.util.*

/**
 * hk.asgard.rain.lesson4.utils.FileUtils
 * 4thWork
 *
 * @author 寒雨
 * @since 2021/11/18 14:13
 **/
fun readFile(path: String): String {
    val file = File(path)
    if (!file.exists()) error("the file is not exist!")
    if (!file.canRead()) error("the file unable to read!")
    return FileReader(file).run {
        readLines().joinToString("\n").also { close() }
    }
}

fun writeFile(path: String, content: String) {
    val file = File(path)
    if (!file.exists()) {
        file.createNewFile()
    }
    if (!file.canWrite()) error("the file unable to write!")
    FileWriter(file).use {
        it.write(content)
    }
}

// 序列化对象 Base64
fun serializeObject(any: Any): String {
    ByteArrayOutputStream().use { byteArrayOutputStream ->
        ObjectOutputStream(byteArrayOutputStream).use {
            it.writeObject(any)
            return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray())
        }
    }
}

// 反序列化对象 Base64
fun deserializeObject(str: String): Any {
    ByteArrayInputStream(Base64.getDecoder().decode(str)).use { byteArrayInputStream ->
        ObjectInputStream(byteArrayInputStream).use {
            return it.readObject()
        }
    }
}