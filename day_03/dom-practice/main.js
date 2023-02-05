// Truy cập vào thẻ h1 có id=“heading” thay đổi màu chữ thành ‘red’, và in hoa nội dung của thẻ đó
const heading = document.getElementById("heading");
console.log(heading)
heading.style.color = "red";
heading.style["text-transform"] = "uppercase";
// Thay đổi màu chữ của tất cả thẻ có class “para” thành màu “blue” và có font-size = “20px”
const paras = document.querySelectorAll(".para");
paras.forEach(el => {
    el.style.color = "blue";
    el.style["font-size"] = "20px";
})
// Thêm thẻ a link đến trang ‘facebook’ ở đằng sau thẻ có class “para-3”
const para3 = document.querySelector(".para-3");

const a = document.createElement("a");
a.innerText = "facebook";
a.href = "https://www.facebook.com";
para3.insertAdjacentElement("afterend", a);
// Thay đổi nội dung của thẻ có id=“title” thành “Đây là thẻ tiêu đề”
const title = document.getElementById("title");
title.innerText = "Đây là thẻ tiêu đề";
// Thêm class “text-bold” vào thẻ có class=“description” (định nghĩa class “text-bold” có tác dụng in đậm chữ)
const mt = document.querySelector(".content .description");
mt.classList.add("text-bold");
// Thay thế thẻ có class=“para-3” thành thẻ button có nội dung là “Click me”
const btn = document.createElement("button")
btn.innerText = "Click me"
para3.parentNode.replaceChild(btn, para3)

// Copy thẻ có class=“para-2” và hiển thị ngay đằng sau thẻ đó
const para2 = document.querySelector(".para-2")
para2.insertAdjacentElement("afterend", para2.cloneNode(true))
// Xóa thẻ có class=“para-1”
// const para1 = document.querySelector(".para-1")
// para1.parentNode.removeChild(para1)
para2.parentNode.removeChild(para2.previousElementSibling)