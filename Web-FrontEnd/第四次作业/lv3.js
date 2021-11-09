// deep copy
function deepCopy(inst) {
    // 乞丐版 使用JSON进行序列化/反序列化操作实现
    // 这种做法效率应该相当低 （反正在Java/Kotlin里面挺低的）
    JSON.parse(JSON.stringify(inst))
    // 使用递归的方式copy到最深层的基础类型属性
    const copy = {}
    for (key in inst) {
        let temp = inst[key]
        if (typeof temp == 'object') {
            // 递归
            temp = deepCopy(temp)
        }
        copy[key] = temp
    }
    return copy
}

const person = {
    name: '露露姐姐',
    age: 1000000,
    address: {
      city: 'ChongQing',
      area: 'NanShan'
    },
    title: ['student',{year:2021, title:'GoodStudent'}]
  }

  const obj = deepCopy(person)
  // 修改person第一层属性address
  person.address.area = 'CQUPT';
  console.log(person); // area: CQUPT
  console.log(obj); // area: NanShan
  // Perfect!