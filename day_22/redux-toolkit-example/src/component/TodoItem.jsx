import React from 'react'

//props la object chua cac gia tri ma component nhan dc
function TodoItem({todo, onDelete}) {
  const handleDelete = () => {
    onDelete(todo.id);
  }
  return (
    <div>
      {todo.title}
      <button onClick={handleDelete}>Delete</button>
    </div>
  )
}

export default TodoItem