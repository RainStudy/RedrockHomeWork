package main

import "fmt"

func main() {
	var str string
	result := ""
	fmt.Println("输入字符:")
	_, err := fmt.Scanln(&str)
	if err != nil {
		return 
	}
	for _, char := range str {
		result = string(char) + result
	}
	fmt.Printf("结果: %s", result)
}
