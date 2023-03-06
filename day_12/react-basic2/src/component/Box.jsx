import React from 'react'

function Box({color, index, onDelete}) {
    const handleDelete = () => {
      onDelete(index);
    }
  return (
    <div className='box' style={{backgroundColor: color}} onClick={handleDelete}></div>
  )
}

export default Box