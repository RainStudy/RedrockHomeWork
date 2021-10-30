package main

import (
	"fmt"
	"sync"
)

var x int64
var wg sync.WaitGroup
var mu sync.Mutex

func add() {
	for i := 0; i < 50000; i++ {
		mu.Lock()
		x = x + 1
		mu.Unlock()
	}
	wg.Done()
}

func main() {
	wg.Add(2)
	go add()
	go add()
	wg.Wait()
	fmt.Println(x)
}