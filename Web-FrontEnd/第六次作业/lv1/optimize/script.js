const circles = document.querySelectorAll('.circle')

async function sleep(ms) {
    return new Promise(resolve => {
        setTimeout(() => resolve(), ms)
    })
}

for (const circle of circles) {
    circle.classList.add("at-end")
    await sleep(1000)
}
