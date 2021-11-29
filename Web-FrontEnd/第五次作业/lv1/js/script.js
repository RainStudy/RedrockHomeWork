const button = document.getElementById("button")
button.addEventListener('click', () => {
    const elements = document.getElementsByTagName('span')
    const color = elements[0].style.backgroundColor == 'black' ? 'white' : 'black'
        for (const element of elements) {
            element.style.backgroundColor = color
        }
})