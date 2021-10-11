package main

import "fmt"

func main() {
	fmt.Println("请输入四个数字，以空格分开")
	var input1 int
	var input2 int
	var input3 int
	var input4 int
	_, err := fmt.Scanf("%d %d %d %d", &input1, &input2, &input3, &input4)
	if err != nil {
		fmt.Println("格式错误,程序退出")
		return
	}
	values := []int{input1, input2, input3, input4}
	for i := 0; i < len(values)-1; i++ {
		for j := i + 1; j < len(values); j++ {
			if values[i] > values[j] {
				values[i], values[j] = values[j], values[i]
			}
		}
	}
	fmt.Println("排序结果: ")
	fmt.Println(values)
}
