import React, { useState } from 'react'

function Todo({todo, editTodo, updateStatus, deleteTodo}) {
  return (
    <>
        <li>
            <input type="checkbox" checked={todo.status} onChange={() => updateStatus(todo.id)}/>
            <span className={todo.status ? "active" : ""}>{todo.title}</span>
            <button onClick={() => editTodo(todo.id)}>Edit</button>
            <button onClick={() => deleteTodo(todo.id)}>Delete</button>
        </li> 
    </>
  )
}

export default Todo