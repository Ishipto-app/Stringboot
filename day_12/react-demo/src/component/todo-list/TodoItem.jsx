import React from 'react'

//props la object chua cac gia tri ma component nhan dc
function TodoItem({title, index, onDelete}) {
  const handleDelete = () => {
    onDelete(index);
  }
  return (
    <div>
      {title}
      <button onClick={handleDelete}>Delete</button>
    </div>
  )
}

export default TodoItem