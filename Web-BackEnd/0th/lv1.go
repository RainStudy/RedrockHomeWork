package main

import "fmt"

func main() {
	var num1 int
	var method string
	var num2 int
	fmt.Println("输入第一个数字")
	_, err := fmt.Scanln(&num1)
	if err != nil {
		fmt.Println("输入错误，程序退出")
		return
	}
	fmt.Println("输入运算符号")
	_, err = fmt.Scanln(&method)
	if err != nil {
		fmt.Println("输入错误，程序退出")
		return
	}
	fmt.Println("输入第二个数字")
	_, err = fmt.Scanln(&num2)
	if err != nil {
		fmt.Println("输入错误，程序退出")
		return
	}
	fmt.Println("打印结果:")
	switch method {
	case "+":
		{
			fmt.Println(plus(num1, num2))
		}
	case "-":
		{
			fmt.Println(minus(num1, num2))
		}
	case "*":
		{
			fmt.Println(multiply(num1, num2))
		}
	case "/":
		{
			fmt.Println(divide(num1, num2))
		}
	default:
		{
			fmt.Println("运行异常")
		}
	}
}

// 加
func plus(num1 int, num2 int) (result int) {
	return num1 + num2
}

// 减
func minus(num1 int, num2 int) (result int) {
	return num1 - num2
}

// 乘
func multiply(num1 int, num2 int) (result int) {
	return num1 * num2
}

func divide(num1 int, num2 int) (result int) {
	return num1 / num2
}
