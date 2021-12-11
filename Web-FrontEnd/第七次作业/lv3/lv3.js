// 连接WebSocket
const ws = new WebSocket(`wss://anonym.ink:8000/homework/chatroom?username=${localStorage.getItem("username")}&avatar=${localStorage.getItem("avatar")}`)
const input = document.querySelector("#inputText")
const contentBox = document.querySelector("#content")
const placeholder = document.querySelector("#placeholderBlock")
// 接受信息时
ws.onmessage = (event) => {
    // debug
    console.log(event.data)
    onMessage(JSON.parse(event.data))
    contentBox.scrollTo({
        top: contentBox.scrollHeight
    })
}

// 回车监听
document.onkeyup = (e) => {
    const event = e || window.event
    const key = event.which || event.keyCode || event.charCode
    if (key === 13) {
        send()
    }
}

function onMessage(data) {
    if (data.type == "MESSAGE") {
        const ele = document.createElement("div")
        ele.className = "chat"
        ele.innerHTML = `
    <div class="chatContainer">
        <div class="avatar" style="background-image: url(${data.avatar});"></div>
        <div style="display: inline-block;position: relative;bottom: 20px;left: 10px;">
            <span class="username">${data.username}</span>
            <span class="time">${new Date().toLocaleString()}</span>
        </div>
        <div class= "chatContent">${parse(data.data)}</div>
    </div>
    `
        // 在占位块之前插入元素
        contentBox.insertBefore(ele, placeholder)
    } else {
        generateBubble(data.username, data.type == "OPEN")
    }
}

// 解析图片
function parse(content) {
    const regex = /image:(\S+)/
    const translated = content.replace("/", "\/")
    return translated.replace(regex, `<div class="image" style="background-image: url($1);"></div>`)
    .replace("<script>", "")
    .replace("</script>", "")
}

function parseSend(content) {
    return content.replace(/image:(\S+)/, `<img src="$1" alt="图片" width="400px" />`)
}

function send() {
    ws.send(parseSend(input.value))
    input.value = ""
}

function generateBubble(name, join) {
    const key = join ? "进入" : "离开"
    const msg = `${name} ${key}了聊天室`
    const ele = document.createElement("div")
    ele.className = "card bubble"
    ele.textContent = msg
    contentBox.insertBefore(ele, placeholder)
}
