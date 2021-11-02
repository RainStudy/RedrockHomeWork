// lv0
const myInstance = {
    name: 'myInstance'
}

class Player {
    constructor() {
        this.name = 'myInstance'
    }
}

const player = new Player()

// lv1
const array = []
// 对象
const instance = {
    name: 'Billy',
    age: 21,
    gender: 'male'
}

// 遍历内部属性，构造一个所有属性相同的新实例
function copy_I(insertObject) {
    const obj = {}
    for(let i in insertObject) {
        obj[i] = insertObject[i]
    }
    return obj
}

// 转为json字符串，再转回来
function copy_II(insertObject) {
    return JSON.parse(JSON.stringify(insertObject))
}

// 运算拓展符实现深拷贝
function copy_III(insertObject) {
    var { ...obj } = insertObject
    return obj
}

array.push(instance, copy_I(instance), copy_II(instance), copy_III(instance))
console.table(array)

// lv2
const arr = [[1, [2, 3], 4], [5, [6, 7], 8, 9]];
// method
function fn(array) {
    return array.reduce((newArr, present) => {
        return newArr.concat(Array.isArray(present) ? fn(present) : present)
    }, []);
}
// 输出结果
console.log(fn(arr))