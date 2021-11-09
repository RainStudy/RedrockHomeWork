// 浅拷贝
// 使用for循环遍历实现
const person = {
    name: '露露姐姐',
    age: 1000000,
    address: {
      city: 'ChongQing',
      area: 'NanShan'
    },
    title: ['student',{year:2021, title:'GoodStudent'}]
  }

function copy(inst) {
    const copy = {}
    for (key in inst) {
        copy[key] = inst[key]
    }
    return copy
}

console.log(person)
// 复制一个露露姐姐 嘿嘿~
console.log(copy(person))