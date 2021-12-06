const login = document.getElementById("login")
const pass = document.getElementById("password")
const user = document.getElementById("username")
const div = document.getElementById("output")
const btnGet = document.getElementById("get")

function getToken() {
    return localStorage.getItem("token")
}

async function logIn(username, password) {
    const res = await fetch("http://anonym.ink:8000/homework/auth/login", {
        method: "POST",
        body: JSON.stringify(
            {
                username: username,
                password: password,
            }
        ),
        headers: new Headers({
            "content-type": "application/json"
        })
    })
    const token = await res.json()
    localStorage.setItem("token", token.data)
    div.textContent = token.data
}

async function get(token) {
    const res = await fetch("http://anonym.ink:8000/homework/auth/next_level", {
        method: "GET",
        headers: new Headers({
            "Authorization": `BEARER ${token}`
        })
    })
    const msg = await res.text()
    console.log(msg)
    div.textContent = msg
}

login.addEventListener("click", () => {
    logIn(user.value, pass.value)
})

btnGet.addEventListener("click", () => {
    get(getToken())
})