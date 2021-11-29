const step = (size, time, ele) => {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            ele.style.marginLeft = `${size}px`
            resolve(size)
        }, time)
    })
}


// promise 实现
function promiseAnimation(id) {
    const ele = document.getElementById(id)
    let promise
    for (let i = 1; i <= 200; i++) {
        promise = step(i + 100, 10 * i, ele)
    }
    return promise
}

// promiseAnimation("one").then((v) => {
//     promiseAnimation("two").then((v) => {
//         promiseAnimation("three")
//     })
// })

// async/await 实现
async function asyncAnimation(id) {
    const ele = document.getElementById(id)
    for (let i = 1; i <= 200; i++) {
        await step(i + 100, 10, ele)
    }
}

// 顺序执行任务队列
async function awaitAll() {
    await asyncAnimation("one")
    await asyncAnimation("two")
    await asyncAnimation("three")
}

// Note: async/await实现运动得相对较慢 
// 因为await让异步任务同步，这个过程是线性的，而不是并行的
awaitAll()

