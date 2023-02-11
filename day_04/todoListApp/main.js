const generateId = arr => {
    if(arr.length === 0) return 1;
    return arr[arr.length - 1].id + 1;
}
const todos = [
    {id: 1, title: "Di choi", status: false},
    {id: 2, title: "Hoc bai", status: true},
    {id: 3, title: "Da bong", status: false},
];

const listEl = document.getElementById("todo-list");
const inputEl = document.getElementById("todo-input");
const btnAdd = document.getElementById("btn-add");

btnAdd.addEventListener("click", () => {
    const title = inputEl.value;
    if(title.trim() === ""){
        alert("Tieu de khong duoc de trong");
        return;
    }
    const newTodo = {
        id: generateId(todos),
        title: title,
        status: false,
    }
    todos.push(newTodo);
    renderTodos(todos);
    inputEl.value = "";
})

const renderTodos = arr => {
    listEl.innerHTML = "";

    if(arr.length === 0){
        listEl.innerHTML = "<li>Danh sách công việc trống</li>";
        return;
    }

    let html = ""
    todos.map(todo => {
        html += `
            <li>
                <input type="checkbox" onclick="chanceStatus(${todo.id})" ${todo.status ? "checked" : ""}>
                <span class=${todo.status ? "active" : ""}>${todo.title}</span>
                <button onclick="editTodo(${todo.id})">Edit</button>
                <button onclick="deleteTodo(${todo.id})">Delete</button>
            </li>
        `
    })
    listEl.innerHTML = html;
}
renderTodos(todos);

const editTodo = id => {
    const todo = todos.find(el => el.id === id);
    let title = window.prompt("Please enter title", `${todo.title}`);
    if(title.trim() === ""){
        alert("noi dung khong duoc de trong, title khong thay doi");
        return;
    }
    todo.title = title
    renderTodos(todos)
}
const deleteTodo = id => {
    let index = todos.indexOf(todos.find(el => el.id == id));
    todos.splice(index, 1)
    renderTodos(todos)
}
const chanceStatus = id => {
    const todo = todos.find(el => el.id === id);
    todo.status = !todo.status
    renderTodos(todos)
}
