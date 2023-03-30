import axios from 'axios';
import React, { useState, useEffect } from 'react'
import CourseItem from './CourseItem';
import CourseSearchText from './CourseSearchText';
import CourseSidebar from './CourseSidebar';
const API_URL = "http://localhost:8080/api/v1/courses"

function CourseList() {
    const [courses, setCourses] = useState([]);
    const [category, setCategory] = useState("");
    const [name, setName] = useState("");
    const [type, setType] = useState("");

    useEffect(() => {
        const getAllCourses = async () => {
          try {
              let rs = await axios.get(API_URL);
              console.log(rs.data)
              setCourses(rs.data);
          } catch (error) {
              console.error(error)
          }
        }
        getAllCourses();
      }, []);

    const filterByCategory = async value => {
        setCategory(value);
        filterCourses(type, name, value);
    }
    const filterByName = value => {
        setName(value);
        filterCourses(type, value, category);
    }
    const filterCourses = async (type, name, category) => {
        let str = API_URL
        if(type || name || category){
            str += "?" + (type ? "type=" + type : "") + (name ? ((type ? "&" : "") + "name=" + name) : "") + (category ? (((type || name) ? "&" : "") + "category=" + category) : "")
        }
        try {
            let rs = await axios.get(str);
            console.log(rs.data)
            setCourses(rs.data);
        } catch (error) {
            console.error(error)
        }
    }
  
  return (
    <>

        <div className="course-container mt-5">
            <div className="container">
                <div className="row">
                    <CourseSidebar category={category} onCategoryChange={filterByCategory} />

                    <div className="col-md-9">
                        <CourseSearchText name={name} onTextFilterChange={filterByName}/>
                        <div className="course-list row">
                            {courses.map((course, index) => (
                                <CourseItem key={course.id} course={course} />
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </>
  )
}

export default CourseList