package kim.bifrost.coldrain.work7th.fetch

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * kim.bifrost.coldrain.work7th.fetch.Fetch
 * Work7th
 * 类似JavaScript Fetch的网络请求工具
 * 叔叔我啊，最喜欢封装工具了
 * 只封装了非常有限的功能，毕竟只是一次作业
 * 感觉比Okhttp好用 (doge)
 *
 * @author 寒雨
 * @since 2021/12/13 10:06
 **/
/**
 * Fetch DSL封装
 *
 * @param urlStr url
 * @param client client,如果不需要阻断则可以填null
 * @param func DSL
 * @return Kotlin Result
 */
fun fetch(urlStr: String, client: FetchClient? = null, func: FetchOptions.() -> Unit = {}): Result<FetchResponse> {
    val options = FetchOptions().also(func)
    client?.sendInterceptors?.forEach { it(options) }
    var connection: HttpURLConnection?
    return runCatching {
        val response = StringBuilder()
        // 添加FormBody
        val url = URL(urlStr)
        connection = url.openConnection() as HttpURLConnection
        // 请求方式
        connection?.requestMethod = options.method.name
        // 添加 headers
        options.headers.forEach { (k, v) -> connection?.setRequestProperty(k, v) }
        connection?.connectTimeout = options.connectTimeout
        connection?.readTimeout = options.readTimeout
        if (options.method == HttpMethod.POST) {
            // 设置允许写出 (这里踩了坑)
            connection?.doOutput = true
            // 添加 jsonBody
            if (options.body is JsonBody) {
                connection!!.outputStream.write((options.body as JsonBody).content.toByteArray())
            } else if (options.body is FormBody) {
                // 写入 formBody
                DataOutputStream(connection!!.outputStream).apply {
                    val iterator = (options.body as FormBody).pairs.iterator()
                    while (iterator.hasNext()) {
                        iterator.next().let { writeBytes("${it.key}=${it.value}") }
                        if (iterator.hasNext()) writeBytes("&")
                    }
                }
            }
        }
        val input = connection!!.inputStream
        val reader = BufferedReader(InputStreamReader(input))
        reader.use {
            reader.forEachLine {
                response.append(it)
            }
        }
        FetchResponse(response.toString(), connection!!.headerFields)
            .also { client?.receiveInterceptor?.forEach { i -> i(it) } }
            .also { connection?.disconnect() }
    }
}

open class FetchClient {
    val sendInterceptors = ArrayList<FetchOptions.() -> Unit>()
    val receiveInterceptor = ArrayList<FetchResponse.() -> Unit>()
}

class FetchResponse(private val content: String, val headers: Map<String, List<String>>) {

    // 不准用Gson就给注释了, 实际上用了Gson会更方便

//    private val gson = Gson()

    fun string(): String = content

//    fun <T> json(): T = gson.fromJson(string(), object: TypeToken<T>() {}.type)
}

interface RequestBody

class JsonBody(val content: String) : RequestBody

class FormBody(init: HashMap<String, String>.() -> Unit) : RequestBody {
    val pairs = HashMap<String, String>()

    init {
        init(pairs)
    }
}

class EmptyBody : RequestBody

class FetchOptions {
    // 请求头
    val headers = HashMap<String, String>()
    // 请求方法
    var method = HttpMethod.GET
    // 连接超时时间
    var connectTimeout = 8000
    // 读取超时时间
    var readTimeout = 8000
    // 请求体 只在POST请求时有效
    var body: RequestBody = EmptyBody()

    fun formBody(func: HashMap<String, String>.() -> Unit) {
        body = FormBody(func)
    }

    fun jsonBody(content: String) {
        headers["content-type"] = "application/json"
        body = JsonBody(content)
    }

    fun headers(func: HashMap<String, String>.() -> Unit) {
        func(headers)
    }
}

enum class HttpMethod {
    // 目前只用得上这俩，就只封装这俩
    GET, POST
}