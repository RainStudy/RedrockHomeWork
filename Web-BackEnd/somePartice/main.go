package main

import "fmt"

func main() {
	fmt.Println("HelloWorld")
	fmt.Println(f1(1, 2, "sb"))
	sum := 0
	for i := 1; i <= 100; i++ {
		sum += i
	}
	fmt.Println(sum)
}

func f1(a, b int, c string) (sum int, cc string) {
	sum = a + b
	cc = c
	return
}
