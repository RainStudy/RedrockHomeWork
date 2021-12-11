// lv1 自己实现Call与Apply
// 有点类似于Java反射的Method#invoke吧，不过没有反射这么大的性能开销
// 踩了个坑，lambda里面不能用this
Function.prototype.myCall = function() {
    // call接受vararg，故从方法内获取参数
    const inst = arguments[0]
    const args = []
    // 注意i从1开始
    for (let i = 1, len = arguments.length; i < len; i++) {
        args.push(arguments[i])
    }
    // 方法
    inst["_fn"] = this
    // 执行
    const result = inst["_fn"](...args)
    // 执行完后删除
    delete inst["_fn"]
    // 返回结果
    return result
}

Function.prototype.myApply = function (inst, args) {
    // 方法
    inst["_fn"] = this
    // 执行
    const result = inst["_fn"](...args)
    // 执行完后删除
    delete inst["_fn"]
    // 返回结果
    return result
}

function call(arg1, arg2) {
    console.log(this.message + arg1 + arg2)
}

const js = {
    message: "JavaScript",
}

// call.myCall(js, "真", "好使")
// call.myApply(js, ["真", "好使"])

// lv2 数组去重
function decoupling(array) {
    const decouplied = []
    for (let i of array) {
        if (!decouplied.includes(i)) {
            decouplied.push(i)
        }
    }
    return decouplied
}

const array = ["JavaScript", "JavaScript", "TypeScript", "TypeScript", "Kotlin"]

console.log(decoupling(array))
