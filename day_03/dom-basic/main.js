const heading = document.getElementById("heading");
console.log(heading);

const headingSelector = document.querySelector("#heading");
console.log(headingSelector);

const para2 = document.querySelector(".para-2");
console.log(para2);

const paras = document.querySelectorAll("p");
console.log(paras)

const para3 = document.querySelector("ul li:nth-child(2");
console.log(para3);

const para1 = para3.firstChild;
console.log(para1)

paras.forEach(ele => {
    ele.style.color = "red"
    console.log(ele)
})
Array.from(paras).map(ele => {
    console.log(ele)
})
const ul = document.querySelector("ul")
console.log(ul.innerHTML)
console.log(ul.innerText) //chi lay noi dung text view
console.log(ul.textContent) // lay ca cac khoang trang

heading.innerHTML = "<span>Hi</span>"
heading.innerText = "<span>Hi</span>"
heading.textContent = "<span>Hi</span>"

//them phan tu

//tao ra phan tu
const btn = document.createElement("button")
btn.innerText = "Click me"
console.log(btn)

// document.body.appendChild(btn)
// document.body.prepend(btn)
// replaceChild thay the
// xac dinh vi tri trong cha => body / truoc => para2
// document.body.insertBefore(btn, para2)

// Một số phương thức khác để thêm nội dung, phần tử DOM

// targetElement.insertAdjacentHTML(position, text);
// targetElement.insertAdjacentElement(position, element);
// targetElement.insertAdjacentText(position, text);
// Trong đó:

// position: Vị trí của phần tử cần thêm, bao gồm 4 vị trí sau:

// beforebegin : Vị trí trước targetElement.
// afterbegin : Bên trong targetElement, trước first child.
// beforeend : Bên trong targetElement, sau last child.
// afterend : Vị trí sau targetElement.

//para2.insertAdjacentElement("beforebegin", btn)
para2.insertAdjacentHTML("beforebegin", "<button>Click me</button>")

//xoa
//document.body.removeChild(heading)
//heading.parentNode.removeChild(heading)
heading.classList.add("text-red", "text-big", "text-center")
heading.classList.remove("text-red", "text-center")
console.log(heading.classList.contains("text-big"))
console.log(heading.classList.contains("text"))

// setInterval(() => {
//     console.log(heading.classList.toggle("text-big"))
// }, 1000)

const timeEl = document.getElementById("time")
const spanEl = document.querySelector("#time span")
console.log(spanEl)
console.log(spanEl.innerText)
let time = 10
const itv1 = setInterval(() => {
    time--;
    spanEl.innerText = `${time}s`
    if(time == 0) {
        clearInterval(itv1)
        timeEl.innerText = "Ket thuc"
    }
}, 1000)