// for
function factorial(n) {
    let sum = 1;
    for (let i = 1; i <= n; i ++) {
        sum *= i
    }
    return sum
}

// 递归
const factorialFunc = (n, temp = 1, sum = 1) => {
    if(temp > n) {
        return sum
    }
    sum *= temp
    temp++
    return factorialFunc(n, temp, sum)
}

console.log(factorial(10))
console.log(factorialFunc(10))