package kim.bifrost.coldrain.work7th.fetch

import android.content.Context
import androidx.core.content.edit

/**
 * kim.bifrost.coldrain.work7th.Utils
 * Work7th
 *
 * @author 寒雨
 * @since 2021/12/14 23:54
 **/
class DefFetchClient(context: Context) : FetchClient() {
    init {
        sendInterceptors.add {
            context.getSharedPreferences("cookie", Context.MODE_PRIVATE)
                .getString("cookie", null)?.let {
                    headers["cookie"] = it
                }
        }
        receiveInterceptor.add {
            val builder = StringBuffer()
            (headers.getOrDefault("set-cookie", null) ?: return@add)
                .map { it.split(";")[0] }
                .forEach {
                    builder.append(it).append(";")
                }
            context.getSharedPreferences("cookie", Context.MODE_PRIVATE)
                .edit {
                    putString("cookie", builder.toString())
                }
        }
    }
}