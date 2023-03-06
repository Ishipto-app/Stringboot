import React from 'react'

function SidebarItem({data, handleTopic}) {
    const handleValue = () => {
        handleTopic(data.value)
    }
  return (
    <div className="topic-item input-group d-flex align-items-center mb-1">
        <input type="radio" value={data.value} id={data.id} name={data.name} onChange={handleValue} />
        <label htmlFor={data.id} className="ms-2 fs-5">{data.label}</label>
    </div>
  )
}

export default SidebarItem