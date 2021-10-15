package main

import (
	"fmt"
	"math/rand"
	"time"
)

func main() {
	var slice1 []int
	rand.Seed(time.Now().Unix())
	for i := 0; i < 99; i++ {
		slice1 = append(slice1, rand.Intn(100))
	}
	slice1 = sort(slice1)
	for _, i2 := range slice1 {
		fmt.Println(i2)
	}
}

// 冒泡排序
func sort(origin []int) []int {
	var edited = origin
	for i := 0; i < len(edited) - 1; i++ {
		for j := 0; j < len(edited) - 1 - i; j++ {
			if edited[j] > edited[j + 1] {
			var temp = edited[j + 1]
			edited[j + 1] = edited[j]
			edited[j] = temp
			}
		}
	}
	return edited
}
