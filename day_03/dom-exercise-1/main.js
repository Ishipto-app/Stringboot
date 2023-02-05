// Câu 1. Tạo 1 thẻ p có id=“text”, có nội dung bất kỳ (có thể tạo bằng HTML hay Javascript - tùy chọn). Yêu cầu

// Đặt màu văn bản thành #777
// Đặt kích thước phông chữ thành 2rem
// Đặt nội dung HTML thành Tôi có thể làm <em> bất cứ điều gì </em> tôi muốn với JavaScript.
const para = document.getElementById("text");
para.style.color = "#777";
para.style.fontSize = "2rem";
para.innerHTML = `Tôi có thể làm <em> bất cứ điều gì </em> tôi muốn`;

// Câu 2. Sử dụng vòng lặp để đặt màu chữ cho tất cả thẻ li thành màu blue (tạo thẻ ul-li bằng html)

// <ul>
//     <li>Item 1</li>
//     <li>Item 2</li>
//     <li>Item 3</li>
// </ul>

const ul = document.querySelectorAll('ul li');
ul.forEach(item => {
    item.style.color = "blue";
})
// Câu 3. Cho mã HTML có nội dung như sau (tạo thẻ ul-li bằng html):

// <ul id="list">
//    <li>Item 1</li>
//    <li>Item 2</li>
//    <li>Item 3</li>
//    <li>Item 4</li>
//    <li>Item 5</li>
//    <li>Item 6</li>
//    <li>Item 7</li>
// </ul>

// Sử dụng javascript để thực hiện những công việc sau
// Thêm 3 thẻ <li> có nội dung Item 8, Item 9, Item 10 vào cuối danh sách
// Sửa nội dung cho thẻ <li> 1 thành màu đỏ (color)
// Sửa background cho thẻ <li> 3 thành màu xanh (background-color)
// Remove thẻ <li> 4
// Thêm thẻ <li> mới thay thế cho thẻ <li> 4 bị xóa ở bước trước, thẻ <li> mới có nội dung bất kỳ

const list = document.getElementById("list")
for (let i = 8; i <= 10; i++) {
    const newLi = document.createElement("li");
    newLi.innerText = `Item ${i}`;
    list.appendChild(newLi);
}

const listItems = list.getElementsByTagName('li');
listItems[0].style.color = "red";

listItems[2].style.backgroundColor = "blue";

listItems[3].parentNode.removeChild(listItems[3]);

const newLi2 = document.createElement("li");
newLi2.innerText = `nội dung bất kỳ`;
listItems[2].insertAdjacentElement("afterend", newLi2);