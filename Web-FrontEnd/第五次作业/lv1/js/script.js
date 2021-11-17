const button = document.getElementById("button")
button.addEventListener('click', () => {
    const elements = document.getElementsByTagName('span')
    let color = elements[0].style.backgroundColor == 'black' ? 'white' : 'black'
        for (let element of elements) {
            element.style.backgroundColor = color
        }
})