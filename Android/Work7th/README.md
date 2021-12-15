# Work7th

## Lv0

> https://www.wanandroid.com/tree/json
>
> 方法：GET
>
> 参数：无

![uTools_1639358234393](https://gitee.com/coldrain-moro/images_bed/raw/master/images/uTools_1639358234393.png)

![uTools_1639358168383](https://gitee.com/coldrain-moro/images_bed/raw/master/images/uTools_1639358168383.png)

![uTools_1639358601597](https://gitee.com/coldrain-moro/images_bed/raw/master/images/uTools_1639358601597.png)

> https://wanandroid.com/article/list/0/json
>
> 方法:GET
>
> 参数： author：作者昵称
>
> （这里统一将参数author的值设置为扔物线进行网络请求）

![uTools_1639359399717](https://gitee.com/coldrain-moro/images_bed/raw/master/images/uTools_1639359399717.png)

![uTools_1639359450751](https://gitee.com/coldrain-moro/images_bed/raw/master/images/uTools_1639359450751.png)

## Lv1

> git init 初始化仓库
>
> git add . 添加仓库内所有文件到Git
>
> git remote add origin https://github.com/ColdRain-Moro/RedrockHomeWork.git 添加远程仓库
>
> git commit -m *"commit内容" 将commit文件更改缓存到缓存区
>
> git push origin master 推送到远程仓库

## Lv4

> 封装了一套Kotlin DSL风格的网络请求工具 Fetch
> 设计向JavaScript的fetch方法靠拢
> 同时使用了Kotlin的Result API, 使对网络请求成功与否的回调判断更加优雅
> 目前只支持了GET/POST两种方法

***示例用法***
~~~kotlin
// GET
fetch("https://www.wanandroid.com//hotkey/json")
    .onSuccess {
        val array = JSONObject(it.string())
            .getJSONArray("data")
        for (i in (0 until array.length())) {
            add(array.getJSONObject(i).getString("name"))
        }
        handler.sendMessage(Message().apply { what = 1 })
    }.onFailure {
        runOnUiThread {
            Toast.makeText(this@MainActivity, "网络请求失败: ${it.message}", Toast.LENGTH_SHORT).show()
        }
    }

// POST
val username = binding.etLoginUsername.text.toString()
val password = binding.etLoginPassword.text.toString()
fetch("https://www.wanandroid.com/user/login") {
    method = HttpMethod.POST
    formBody {
        put("username", username)
        put("password", password)
    }
}
.onSuccess {
    JSONObject(it.string()).apply {
        when (getInt("errorCode")) {
            0 -> {
                // 登录成功
                handler.sendMessage(Message().apply { what = 0 })
            }
            -1 -> {
                // 登录失败
                handler.sendMessage(Message().apply { what = 1 })
            }
        }
    }
}
.onFailure {
    runOnUiThread { Toast.makeText(this, "网络请求失败: ${it.message}", Toast.LENGTH_SHORT).show() }
}
~~~

## Lv6
> 为Fetch工具实现了类似OkHttp的拦截器
> 只不过非常的简陋
> 用来自动存/取Cookie肯定是够用了
