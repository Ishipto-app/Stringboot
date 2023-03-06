import React from 'react'

function Item({name, index, onDelete}) {
  return (
    <li>{name}</li>
  )
}

export default Item