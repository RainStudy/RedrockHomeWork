const person = {
    name: '露露姐姐',
    age: 1000000,
    address: {
      city: 'ChongQing',
      area: 'NanShan'
    },
    title: ['student',{year:2021, title:'GoodStudent'}]
  }

// 解构赋值
const {name: username} = person
const {age} = person
// 嵌套解构
const {address: {city}} = person
const {address: {area}} = person
const {title: [title1, {year, title: title2}]} = person

// output
console.log(username);
console.log(age);
console.log(city);
console.log(area);
console.log(title1);
console.log(year);
console.log(title2);