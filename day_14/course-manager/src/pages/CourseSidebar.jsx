import React, { useState } from 'react'
let topics = [
    {id:1, name: "Lập trình Frontend", value: "frontend"}, 
    {id:2, name: "Lập trình Backend", value: "backend"}, 
    {id:3, name: "Lập trình Database", value: "database"},
    {id:4, name: "Lập trình Devops", value: "devops"},
    {id:5, name: "Lập trình cơ bản", value: "basic"}, 
    {id:6, name: "Lập trình di động", value: "mobile"}
]

function CourseSidebar({topic, onTopicChange}) {
    const [selectedValue, setSelectedValue] = useState(topic);
    
    const handleChange = (e) => {
        setSelectedValue(e.target.value);
        onTopicChange(e.target.value);
    }

  return (
    <>
        <div className="col-md-3">
            <h2 className="fs-5 mb-4">Chủ đề</h2>
            {topics.map(topicItem => (
                <div className="topic-item input-group d-flex align-items-center mb-1" key={topicItem.id}>
                    <input type="radio" name="topic" id={topicItem.value} 
                        value={topicItem.value}
                        checked={selectedValue === topicItem.value}
                        onChange={handleChange}/>
                    <label htmlFor={topicItem.value} className="ms-2 fs-5">{topicItem.name}</label>
                </div>
            ))}
        </div>
    </>
  )
}

export default CourseSidebar