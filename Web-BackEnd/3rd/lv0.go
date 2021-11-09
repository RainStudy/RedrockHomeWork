package main

var x int64
var end = make([]chan bool, 2)
var channel chan bool

// channel 先进先出
func main() {
	go add(0)
	go add(1)
	<- channel
	for _, c := range end {
		<- c
	}
}

func add(i int) {
	for i := 0; i < 50000; i++ {
		addSingle()
		<- channel
	}
	end[i] <- true
}

func addSingle()  {
	channel <- true
	x = x + 1
}
