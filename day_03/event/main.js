function sayHello() {
    console.log('Xin chào các bạn');
}

let btn2 = document.getElementById('btn-2');

// Sử dụng DOM property
// btn2.onclick = function() {
//     console.log('Xin chào các bạn 2');
// }
const sayHello2 = () => {
    console.log('Xin chào các bạn 2');
}
btn2.onclick = sayHello2 // ko sayHello2()
let btn3 = document.getElementById('btn-3');
// Sử dụng addEventListener
btn3.addEventListener('click', function() {
    console.log('Xin chào các bạn 3');
})

// document.addEventListener('click', function(e) {
//     console.log(e);
//    })
   
//    document.addEventListener('mousedown', function() {
//     console.log('mousedown');
//    })
   
//    document.addEventListener('mousemove', function() {
//     console.log('mousemove');
//    })
   
//    document.addEventListener('mouseup', function() {
//     console.log('mouseup');
//    })

//tao hinh tron
const box = document.createElement("div")
box.style.width = "100px";
box.style.height = "100px";
box.style.border = "1px solid"
box.style.background = "red";
box.style["border-radius"] = "50%";
box.style.position = "absolute";

// document.addEventListener("click", event => {

// //set vi tri
//     box.style.left = `${event.offsetX - 50}px`;
//     box.style.top = `${event.offsetY - 50}px`;
    
// //insert vao dom
//     document.body.prepend(box)
// })

const inputEl = document.querySelector("input")
const resultEl = document.querySelector("span")
inputEl.addEventListener("keydown", (e) => {
    if(e.key == "Enter"){
        let text = inputEl.value
        if(text.trim() == ""){
            alert("noi dung ko dc de trong")
            return;
        }
        resultEl.innerText = text;
        inputEl.value = ""
    }
});