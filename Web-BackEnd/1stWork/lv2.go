package main

import "fmt"

func main() {
	var a int
	fmt.Println("输入第一个数字")
	_, err := fmt.Scanln(&a)
	if err != nil {
		return
	}
	for true {
		var b string
		fmt.Println("输入运算符号 (+ - * / %)")
		fmt.Println("输入 x 以退出程序")
		_, err := fmt.Scanln(&b)
		if err != nil {
			fmt.Println("非法符号, 程序退出")
			return
		}
		if b == "x" {
			break
		}
		var c int
		fmt.Println("输入运算数字")
		_, err = fmt.Scanln(&c)
		if err != nil {
			fmt.Println("输入内容无法解析为数字，程序退出")
			return
		}
		switch b {
		case "+":
			{
			a += c
			break
			}
		case "-":
			{
			a -= c
			break
			}
		case "*":
			{
			a *= c
			break
			}
		case "/":
			{
			a /= c
			break
			}
		case "%":
			{
			a %= c
			break
			}
		default:
			{
			fmt.Println("错误的运算符号，请重新输入")
			continue
			}
		}
		fmt.Printf("运算结果: %d\n", a)
	}
}
