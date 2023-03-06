import React, {useState} from 'react'
import SidebarItem from './SidebarItem'

function Sidebar() {
    const items = [
        {id: "backend", value: "Backend", name: "topic", label: "Lập trình Backend"},
        {id: "frontend", value: "Frontend", name: "topic", label: "Lập trình Frontend"},
        {id: "mobile", value: "Di động", name: "topic", label: "Lập trình di động"},
        {id: "database", value: "Database", name: "topic", label: "Cơ sở dữ liệu"},
    ]
    const [itemArr, setItemArr] = useState([...items])
    const handleValue = (value) => {
        console.log(value)
        //filter
    }
  return (
    <>
        <h2 className="fs-5 mb-4">Chủ đề</h2>
        {itemArr.map(item => (
            <SidebarItem key={item.id} data={item} 
            handleTopic = {handleValue}></SidebarItem>

        ))}
    </>
  )
}

export default Sidebar