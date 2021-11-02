// 图片
const images = new Array

init()
select()

function init() {
    images[0] = "http://www.dmoe.cc/random.php"
}

function select() {
    const index = Math.floor(Math.random() * images.length)
    const url = images[index]
    document.getElementById("body").style = "background-image: url(" + url + ");"
}