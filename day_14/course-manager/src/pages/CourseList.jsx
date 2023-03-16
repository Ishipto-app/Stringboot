import axios from 'axios';
import React, { useState, useEffect } from 'react'
import CourseHeader from './CourseHeader';
import CourseItem from './CourseItem';
import CourseSearchText from './CourseSearchText';
import CourseSidebar from './CourseSidebar';
const API_URL = "http://localhost:8080/api/v1/courses"

function CourseList() {
    const [courses, setCourses] = useState([]);
    const [topic, setTopic] = useState("");
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

    const filterByTopic = async value => {
        setTopic(value);
        filterCourses(type, name, value);
    }
    const filterByName = value => {
        setName(value);
        filterCourses(type, value, topic);
    }
    const filterCourses = async (type, name, topic) => {
        let str = API_URL
        if(type || name || topic){
            str += "?" + (type ? "type=" + type : "") + (name ? ((type ? "&" : "") + "name=" + name) : "") + (topic ? (((type || name) ? "&" : "") + "topic=" + topic) : "")
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
        <CourseHeader />

        <div className="course-container mt-5">
            <div className="container">
                <div className="row">
                    <CourseSidebar topic={topic} onTopicChange={filterByTopic} />

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