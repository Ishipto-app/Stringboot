import React, {useState} from 'react'
import CourseItem from './CourseItem'

function Courses() {
    const courses = [
        {id: 1, src: "https://media.techmaster.vn/api/static/8028/bpfneoc51co8tcg6lek0", alt: "khoa hoc", name: "Spring Boot - Web Back End", type: "Trực tuyến" },
        {id: 2, src: "https://media.techmaster.vn/api/static/8028/bpfneoc51co8tcg6lek0", alt: "khoa hoc", name: "Spring Boot - Web Back End", type: "Phòng lab" },
        {id: 3, src: "https://media.techmaster.vn/api/static/8028/bpfneoc51co8tcg6lek0", alt: "khoa hoc", name: "Spring Boot - Web Back End", type: "Trực tuyến" },
        {id: 4, src: "https://media.techmaster.vn/api/static/8028/bpfneoc51co8tcg6lek0", alt: "khoa hoc", name: "Spring Boot - Web Back End", type: "Trực tuyến" },
        {id: 5, src: "https://media.techmaster.vn/api/static/8028/bpfneoc51co8tcg6lek0", alt: "khoa hoc", name: "Spring Boot - Web Back End", type: "Trực tuyến" },
        {id: 6, src: "https://media.techmaster.vn/api/static/8028/bpfneoc51co8tcg6lek0", alt: "khoa hoc", name: "Spring Boot - Web Back End", type: "Phòng lab" },
    ]
    const [itemArr, setItemArr] = useState([...courses])
    const [name, setName] = useState("");
    const filterCourse = (value) => {
        console.log(value)
        setName(value)
        if(value == "") {
            setItemArr([...courses])
        } else {
            let newArr = courses.filter(course => course.type.toLowerCase().includes(value.toLowerCase()))
            setItemArr(newArr)
        }
    }
  return (
    <>
        <div className="row">
            <div className="col-md-4">
                <div className="seach-htmlForm d-flex align-items-center rounded shadow-sm mb-4 pe-3">
                    <input type="text" placeholder="Tìm kiếm khóa học" className="htmlForm-control border-0 seach-htmlForm-input" 
                        value={name} 
                        onChange={e => filterCourse(e.target.value)}
                    />
                    <span className="text-black-50 seach-htmlForm-button"><i className="fa-solid fa-magnifying-glass"></i></span>
                </div>
            </div>
        </div>
        <div className="course-list row">
            {itemArr.map(item => (
                <CourseItem key={item.id} data={item}></CourseItem>
            ))}
        </div>
    </>
  )
}

export default Courses