// 评论类
function MyComment(author, date, icon, content, email) {
    this.author = author
    this.email = email
    this.date = date
    this.icon = icon
    this.content = content
    // shit代码
    this.ele = document.createElement('div')
    this.ele.setAttribute("class","commentBox card")
    commentArea.appendChild(this.ele)
    let ele2 = document.createElement('div')
    ele2.setAttribute("class","infoBox")
    this.ele.appendChild(ele2)
    let ele3 = document.createElement('div')
    ele3.setAttribute("class","icon")
    if (icon != "") {
      ele3.style.backgroundImage = "url(" + icon + ")"  
    }
    ele2.appendChild(ele3)
    let ele4 = document.createElement('div')
    ele4.setAttribute("class","info")
    ele2.appendChild(ele4)
    let nameDiv = document.createElement('div')
    nameDiv.setAttribute("class","name")
    nameDiv.textContent = author
    ele4.appendChild(nameDiv)
    let emailDiv = document.createElement('div')
    emailDiv.setAttribute("class","email")
    emailDiv.textContent = email
    ele4.appendChild(emailDiv)
    let timeDiv = document.createElement('div')
    timeDiv.setAttribute("class","time")
    timeDiv.textContent = date
    ele4.appendChild(timeDiv)
    let contentBox = document.createElement('div')
    contentBox.setAttribute("class","contentBox")
    contentBox.textContent = content
    this.ele.appendChild(contentBox)
    // 删除按钮
    let removeButton = document.createElement('span')
    removeButton.setAttribute("class", "delete card")
    removeButton.textContent = '删除'
    // 点击删除元素
    removeButton.addEventListener('click', () => {
        commentList.splice(commentList.indexOf(this), 1)
        this.ele.remove()
        flushTitle()
    })
    this.ele.appendChild(removeButton)
}

const button = document.getElementById('commentButton')
const commentArea = document.getElementById('commentArea')
let commentList = new Array()

function comment() {
    const author = document.getElementById('inputName').value == "" ? "Unnamed" : document.getElementById('inputName').value
    const date = new Date().toLocaleString()
    const icon = document.getElementById('inputAddress').value
    const email = document.getElementById('inputEmail').value == "" ? "Unknown" : document.getElementById('inputEmail').value
    const content = document.getElementById('inputContent').value
    if (content.replace(/\s*/g,"") == "") {
        // 内容为空，无法评论
        return
    }
    // 添加到registry
    commentList.push(new MyComment(author, date, icon, content, email))
} 

function flushTitle() {
    document.getElementById('commentTitle').textContent = '%amount% 条评论'.replace('%amount%', commentList.length + 1)
}

// 给按钮加上监听器
button.addEventListener('click', () => {
    comment()
    flushTitle()
})

