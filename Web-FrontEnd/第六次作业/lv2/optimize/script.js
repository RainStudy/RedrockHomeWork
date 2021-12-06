// 在引用时加一串type="module" 
// 可以导入JS模块（下学期内容），还可以使用一些其他JS的新特性，比如 top await，这时 await 就不需要放在 async 函数里才能执行了
// 路姐的优雅代码 彳亍
const p = document.querySelector('p')
const content = `元丰六年十月十二日夜，解衣欲睡，月色入户
，欣然起行。念无与为乐者，遂至承天寺寻张怀民。怀民亦未寝，相与步于中庭。
庭下如积水空明，水中藻、荇交横，盖竹柏影也。何夜无月？何处无竹柏？但少闲人如吾两人者耳。
`

// 这个函数可以达到类似Java在子线程中调用Thread.sleep的效果
async function sleep(ms) {
    return new Promise(resolve => {
        setTimeout(() => resolve(), ms)
    })
}

for (let i = 0; i < content.length; i++) {
    p.textContent = content.substring(0, i)
    await sleep(200)
}