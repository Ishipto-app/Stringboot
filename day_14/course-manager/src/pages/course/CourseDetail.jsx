import React, {useState, useEffect} from 'react'
import axios from 'axios';
import { Link, useParams, useNavigate } from "react-router-dom";
const API_URL = "http://localhost:8080/api/v1/courses"

function CourseDetail() {
    const {id} = useParams();
    const [course, setCourse] = useState({});
    
    useEffect(() => {
        const getCourseById = async (id) => {
          try {
              let rs = await axios.get(API_URL + "/" + id);
              console.log(rs.data)
              setCourse(rs.data);
          } catch (error) {
              console.error(error)
          }
        }
        getCourseById(id);
      }, []);
    
  return (
    <>
        <div className="course-container mt-5">
            <div className="container">
                <div className="mb-4">
                    <nav aria-label="breadcrumb">
                        <ol className="breadcrumb">
                            <li className="breadcrumb-item">
                                <Link to="/khoa-hoc">Khóa học</Link>
                            </li>
                            <li className="breadcrumb-item active" aria-current="page">
                                {course?.name}
                            </li>
                        </ol>
                    </nav>
                </div>
                <div className="row justify-content-center">
                    <div className="col-md-8">
                        <div className="main p-4 shadow-sm">
                            <h2 className="course-title fs-5">
                                {course?.name}
                            </h2>

                            <hr />

                            <div className="supporter d-flex align-items-center">
                                <div className="supporter-image">
                                    <img src={course?.user?.avatar}
                                        alt="tư vấn viên" className="user-avatar rounded-circle" />
                                </div>
                                <div className="supporter-info">
                                    <p>
                                        <b>Tư vấn viên :</b>
                                        {course?.user?.name}
                                    </p>
                                    <p>
                                        <b>Email :</b>
                                        {course?.user?.email}
                                    </p>
                                    <p>
                                        <b>Số điện thoại :</b>
                                        {course?.user?.phone}
                                    </p>
                                </div>
                            </div>

                            <hr />

                            <div className="course-description">
                                <p>
                                    {course?.description}
                                </p>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-4">
                        <div className="p-4 shadow-sm">
                            <div className="course-image mb-4">
                                <img src={course?.thumbnail} />
                            </div>
                            <p>
                                Học phí :
                                <span className="fw-bold course-price">
                                    {course?.price}
                                </span>
                            </p>
                            <p>
                                Hình thức học :
                                <span className="fw-bold course-type">{course?.type}</span>
                            </p>
                            <button className="btn btn-success">
                                Thêm vào giỏ hàng
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </>
  )
}

export default CourseDetail