package main

import "fmt"

type Student struct {
	Name string
	Class int
	age int
}

func main() {
	stu := Student {
		Name: "寒雨",
		Class: 10,
		age: 18,
	}
	fmt.Println("结构体: ", stu)
	fmt.Println("名称: ", stu.Name)
	fmt.Println("班级: ", stu.Class)
	fmt.Println("年纪: ", stu.age)
}
