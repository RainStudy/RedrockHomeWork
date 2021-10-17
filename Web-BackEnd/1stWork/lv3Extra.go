package main

import (
	"fmt"
	"math/rand"
	"sync"
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
	for i := 0; i < len(edited)-1; i++ {
		for j := 0; j < len(edited)-1-i; j++ {
			if edited[j] > edited[j+1] {
				var temp = edited[j+1]
				edited[j+1] = edited[j]
				edited[j] = temp
			}
		}
	}
	return edited
}

// 从真正的大佬那里抄来的代码
// 睡排，开睡！
// 虽然确实是一种非常棒的思路，但由于数字差距较小时较容易产生误差，所以在这里并不太实用
// 或者...我们可以牺牲更多的排序时间来换取它的精确性,即把元睡眠时间增大
func sortSleep(origin []int) []int {
	// 不初始化也可以append
	var edited []int
	var wg sync.WaitGroup
	wg.Add(len(origin))
	for _, i := range origin {
		go func(num int) {
			time.Sleep(time.Duration(num) * time.Millisecond * 10) // 这里如果改为一百，结果就非常准确了，但如果是10，还是非常容易产生误差
			edited = append(edited, num)
			wg.Done()
		}(i)
	}
	wg.Wait()
	return edited
}
