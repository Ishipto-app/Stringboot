import React from 'react'
import Item from './item'

function List({list}) {
  return (
    <>
      <ul id="list">
        {list.map((name, index) => (
            <Item 
                key={index} 
                name={name} 
                index={index}
            ></Item>
        ))}
      </ul>
    </>
    
  )
}

export default List