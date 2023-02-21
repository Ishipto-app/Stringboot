const generateId = arr => {
    if(arr.length === 0) return 1;
    return arr[arr.length - 1].id + 1;
}
let todos = [];

const listEl = document.getElementById("todo-list");
const inputEl = document.getElementById("todo-input");
const btnAdd = document.getElementById("btn-add");

const API_URL = "http://localhost:8081/api/todos";

const getTodos = async () => {
    try {
        let rs = await axios.get(API_URL);
        todos = rs.data;
        renderTodos(todos);
    } catch (error) {
        console.log(error);
    }
}
getTodos();
btnAdd.addEventListener("click", async () => {
    const title = inputEl.value;
    if(title.trim() === ""){
        alert("Tieu de khong duoc de trong");
        return;
    }
    const newTodo = {
        title: title,
    }
    try {
        let rs = await axios.post(API_URL, newTodo);
        todos.push(rs.data)
        renderTodos(todos);
        inputEl.value = "";
    } catch (error) {
        console.log(error);
    }

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

const editTodo = async id => {
    const todo = todos.find(el => el.id === id);
    let title = window.prompt("Please enter title", `${todo.title}`);
    if(title.trim() === ""){
        alert("noi dung khong duoc de trong, title khong thay doi");
        return;
    }
    try {
        let newData = {title: title, status: todo.status};
        let rs = await axios.put(API_URL + "/" + id, newData);
        todo.title = title
        renderTodos(todos)
    } catch (error) {
        console.log(error);
    }
}
const deleteTodo = async id => {
    try {
        let rs = await axios.delete(API_URL + "/" + id);
        console.log(rs)
        let index = todos.indexOf(todos.find(el => el.id == id));
        todos.splice(index, 1)
        renderTodos(todos)
    } catch (error) {
        console.log(error);
    }
}
const chanceStatus = async id => {
    const todo = todos.find(el => el.id === id);
    
    try {
        let status = !todo.status
        let newData = {title: todo.title, status: status};
        let rs = await axios.put(API_URL + "/" + id, newData);
        console.log(rs)
        todo.status = status
        renderTodos(todos)
    } catch (error) {
        console.log(error);
    }
}
