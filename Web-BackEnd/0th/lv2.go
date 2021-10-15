package main

import "fmt"

var database = make(map[string]string)

func main() {
	var method int
	var id string
	var pass string
START:
	fmt.Println("用户数据库")
	fmt.Println("输入你想要执行的操作")
	fmt.Println("1.注册")
	fmt.Println("2.验证")
	fmt.Println("3.退出")
	_, err := fmt.Scanln(&method)
	if err != nil {
		fmt.Println("输入错误,程序退出")
		return
	}
	switch method {
	case 1:
		{
			fmt.Println("输入用户id")
			_, err := fmt.Scanln(&id)
			if err != nil {
				return
			}
			fmt.Println("输入用户密码")
			_, err = fmt.Scanln(&pass)
			if err != nil {
				return
			}
			register(id, pass)
			fmt.Println("注册成功")
			goto START
		}
	case 2:
		{
			fmt.Println("输入用户id")
			_, err := fmt.Scanln(&id)
			if err != nil {
				return
			}
			fmt.Println("输入用户密码")
			_, err = fmt.Scanln(&pass)
			if err != nil {
				return
			}
			if check(id, pass) {
				fmt.Println("id与密码相匹配")
			} else {
				fmt.Println("id与密码不匹配...")
			}
			goto START
		}
	case 3:
		{
			fmt.Println("程序退出...")
		}
	default:
		{
			fmt.Println("不存在的操作类型,程序退出...")
		}
	}
}

func register(id string, password string) {
	database[id] = password
}

func check(id string, password string) bool {
	pass, ok := database[id]
	if ok {
		return pass == password
	} else {
		return false
	}
}
