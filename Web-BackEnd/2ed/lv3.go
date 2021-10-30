package main

import "fmt"

func main() {
	Receiver("你好呀")
	Receiver(5)
	Receiver(false)
}

func Receiver(v interface{}) {
	switch v.(type) {
	case string:
		{
		fmt.Println("这个是string")
		}
	case int:
		{
		fmt.Println("这个是int")
		}
	case bool:
		{
		fmt.Println("这个是bool")
		}
	}
}