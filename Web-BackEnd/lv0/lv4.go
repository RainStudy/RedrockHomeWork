package main

import "fmt"

var (
	users = []string{
		"Matthew", "Sarah", "Augustus", "Heidi", "Emilie", "Peter", "Giana", "Adriano", "Aaron", "Elizabeth",
	}
	distribution = make(map[string]int, len(users))
)

func main() {
	left := dispatchCoin()
	fmt.Println("共发了：", left)
	fmt.Println(distribution)
}

func dispatchCoin() int {
	amount := 0
	for _, user := range users {
		_, ok := distribution[user]
		if !ok {
			distribution[user] = 0
		}
		for _, char := range user {
			switch char {
			case 'e', 'E':
				{
					amount += 1
					distribution[user] += 1
				}
			case 'i', 'I':
				{
					amount += 2
					distribution[user] += 2
				}
			case 'o', 'O':
				{
					amount += 3
					distribution[user] += 3
				}
			case 'u', 'U':
				{
					amount += 4
					distribution[user] += 4
				}
			}
		}
	}
	return amount
}
