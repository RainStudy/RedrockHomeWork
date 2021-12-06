const response = await fetch("http://anonym.ink:8000/homework/get_homework", {
    method: "POST"
})
const data = await response.text()
console.log(data)