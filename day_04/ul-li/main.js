const listEl = document.getElementById("list")
// Thêm 1 nút “add” + 1 ô input để nhập text. Mỗi khi bấm nút thêm 1 thẻ <li> có nội dung là nội dung trong ô input vào cuối danh sách
// Thêm 1 nút “remove”. Chức năng để xóa thẻ <li> cuối cùng của danh sách
// Thêm 1 nút “Hide” trên đầu của danh sách <ul>.
// Khi bấm vào “Hide” thì <ul> sẽ ẩn. Đồng thời label của nút “Hide” => “Show”
// Và ngược lại Khi bấm vào “Show” thì <ul> sẽ hiện. Đồng thời label của nút “Show” => “Hide”

//tao o input
const inputEl = document.createElement("input");
inputEl.type = "text";
inputEl.placeholder = "Enter text...";
document.body.appendChild(inputEl);

//tao btn
const createBtn = name => {
    const btn = document.createElement("button");
    btn.innerText = name;
    return btn
}
const btnAdd = createBtn("Add");
document.body.appendChild(btnAdd);

const addLi = () => {
    const value = inputEl.value;
    if(value.trim() === "") {
        alert("Noi dung khong duoc de trong");
        return;
    }
    let newLi = document.createElement("li");
    newLi.innerText = value;
    listEl.appendChild(newLi);
    inputEl.value = "";
}

//btnAdd.addEventListener("click", addLi)
btnAdd.onclick = addLi;

//add = enter
inputEl.addEventListener("keydown", e => {
    if(e.key === "Enter") addLi();
})

//them remove
const btnRemove = createBtn("Remove");

document.body.appendChild(btnRemove);
btnRemove.addEventListener("click", e => {
    const lastLi = document.querySelector("#list li:last-child");
    if(lastLi) {
        listEl.removeChild(lastLi)
    } else {
        alert("All Items are remove!");
    }
})

//them hide
const btnShowHide = createBtn("Hide");
document.body.appendChild(btnShowHide);
btnShowHide.addEventListener("click", e => {
    listEl.classList.toggle("hide");
    if(btnShowHide.innerText === "Hide") {
        btnShowHide.innerText = "Show";
    } else {
        btnShowHide.innerText = "Hide";
    }
})