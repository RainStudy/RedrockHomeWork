const btn = document.getElementById("btn")
const input = document.getElementById("input")
const div = document.getElementById("output")

btn.addEventListener("click", () => {
    sendRequest(input.value)
})

async function sendRequest(content) {
    const formData = new FormData();
    formData.append("message", content);
    const res = await fetch("http://anonym.ink:8000/homework/fufubot_test/", {
        method: "POST",
        body: formData
    })
    const data = await res.text()
    div.textContent = data
}