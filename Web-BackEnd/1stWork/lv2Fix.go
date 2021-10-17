package main

import "fmt"

var numbers []int

func main() {
	for true {
		fmt.Println("输入运算式")
		var a, b int
		var cmd string
		_, err := fmt.Scanln(&a, &cmd, &b)
		if err != nil {
			return
		}
		var result int
		switch cmd {
		case "+":
			{
				result = a + b
				break
			}
		case "-":
			{
				result = a - b
				break
			}
		case "*":
			{
				result = a * b
				break
			}
		case "/":
			{
				result = a / b
				break
			}
		case "%":
			{
				result = a % b
				break
			}
		default:
			{
				fmt.Println("错误的运算符号，请重新输入")
				continue
			}
		}
		numbers = append(numbers, result)
		fmt.Println("结果:")
		fmt.Println(result)
		for _, number := range numbers {
			fmt.Print(number)
			fmt.Print(" ")
		}
		fmt.Println()
	}
}
