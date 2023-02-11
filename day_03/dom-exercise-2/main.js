let colors = [
    '#3498db',
    '#9b59b6',
    '#e74c3c',
    '#2c3e50',
    '#d35400',
]

// Render số box = số lượng màu ra ngoài màn hình bằng javascript (5 box)
// Box được tạo bởi thẻ div, có class=“box”, background tương ứng với từng mã màu
// Cập nhật số lượng total box trong thẻ <span> có class “points”
// Khi bấm vào box bất kỳ thì box đó biến mất, đồng thời số lượng total box giảm đi 1
// Khi click vào nút “more box” thì số lượng box tăng lên 5 (tương ứng với 5 màu trong mảng colors), đồng thời số lượng total box cũng tăng lên 5

const boxes = document.querySelector(".boxes");
// xoa noi dung ben trong
boxes.innerHTML = "";

const span = document.querySelector(".points");

// Cập nhật số lượng total box trong thẻ <span>
const countBoxes = () => {
    const listBox = document.querySelectorAll(".box");
    span.textContent = listBox.length;
}

//Render số box
const addBox = () => {
    colors.map(color => {
        const newBox = document.createElement("div");
        newBox.classList.add("box");
        newBox.style.backgroundColor = color;

        //Khi bấm vào box bất kỳ thì box đó biến mất, đồng thời số lượng total box giảm đi 1
        newBox.addEventListener('click', function(e) {
            e.target.parentNode.removeChild(e.target)
            countBoxes();
        })
        
        boxes.insertAdjacentElement("beforeend", newBox);
    })
    countBoxes();
}
addBox();

// Khi click vào nút “more box” thì số lượng box tăng lên 5 (tương ứng với 5 màu trong mảng colors), đồng thời số lượng total box cũng tăng lên 5
const btn = document.getElementById("btn");
btn.addEventListener('click', addBox);