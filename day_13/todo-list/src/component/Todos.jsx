import React, { useState, useEffect } from 'react'
import Todo from './Todo'

const API_URL = "http://localhost:8080/api/todos";

function Todos() {
  const [todos, setTodos] = useState([]);
  const [title, setTitle] = useState("");

  useEffect(() => {
    const getAllTodos = async () => {
      try {
          let rs = await fetch(API_URL);
          let data = await rs.json();
          console.log(data)
          setTodos(data);
      } catch (error) {
          console.error(error)
      }
    }
    getAllTodos();
  }, []);

  const addTodo = async () => {
    if(title.trim() === ""){
        alert("Tieu de khong duoc de trong");
        return;
    }
    const newTodo = {
        id: Math.max(...todos.map(todo => todo.id)) + 1,
        title: title,
        status: false,
    }
    try {
      let rs = await fetch(API_URL, {
        headers: {
        'Content-type': 'application/json; charset=UTF-8'
        },
        method: 'POST',
        body: JSON.stringify(newTodo)
      });
      if(rs.status === 201){
        let newTodos = [...todos, newTodo];
        setTodos(newTodos);
        setTitle("");
      } else {
        console.error(rs);
        alert('error')
      }
    } catch (error) {
        console.error(error);
    }
  }

  const editTodo = id => {
    const todo = todos.find(todo => todo.id === id);
    let title = window.prompt("Please enter title", `${todo.title}`);
    if(title.trim() === ""){
        alert("noi dung khong duoc de trong, title khong thay doi");
        return;
    }
    let newTodo = {title: title, status: todo.status};
    updateTodoServer(id, newTodo);
  }

  const updateTodoServer = async (id, data) => {
    try {
      let rs = await fetch(API_URL + "/" + id, {
          headers: {
          'Content-type': 'application/json; charset=UTF-8'
          },
          method: 'PUT',
          body: JSON.stringify(data)
      });
      console.log(rs)
      if(rs.status === 200){
        const newTodos = todos.map(item => {
          if(item.id === id){
            return{...item, title: data.title, status: data.status};
          }
          return item;
        })
        setTodos(newTodos);
      } else {
        console.error(rs);
        alert("error");
      }
    } catch (error) {
        console.error(error);
    }
  }

  const updateStatus = async id => {
    const todo = todos.find(todo => todo.id === id);
    let newTodo = {title: todo.title, status: !todo.status};
    updateTodoServer(id, newTodo);
  }

  const deleteTodo = async (id) => {
    try {
      let rs = await fetch(API_URL + "/" + id, {
          method: 'DELETE'
      });
      if(rs.status === 204){
        let newTodos = todos.filter(todo => todo.id !== id);
        setTodos(newTodos);
      } else {
        console.error(rs);
        alert("error");
      }
    } catch (error) {
        console.error(error)
    }
  }

  return (
    <>
      <h1>TodoList App</h1>

      <input id="todo-input" type="text" placeholder="Enter todo title ..." value={title} onChange={e => setTitle(e.target.value)}/>
      <button id="btn-add" onClick={addTodo}>Add</button>

      <ul id="todo-list">
        {todos.length > 0 && todos.map(todo => (
          <Todo key={todo.id} todo={todo} editTodo={editTodo} deleteTodo={deleteTodo} updateStatus={updateStatus}/>
        ))}
        {todos.length == 0 && <li>Danh sách công việc trống</li>}
      </ul>

    </>
  )
}

export default Todos