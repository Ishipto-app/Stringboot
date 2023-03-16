import React, {useState} from 'react'
import { Link } from "react-router-dom";

function CourseItem({course}) {

  return (
    <>
        <div className="col-md-4">
            <Link to={course.id}>
                <div className="course-item shadow-sm rounded mb-4">
                    <div className="course-item-image">
                        <img src="https://media.techmaster.vn/api/static/8028/bpfneoc51co8tcg6lek0"
                            alt="Marge Innastraightline" />
                    </div>
                    <div className="course-item-info p-3">
                        <h2 className="fs-5 mb-4 text-dark">
                            {course.name}
                        </h2>
                        <div
                            className="d-flex justify-content-between align-items-center fw-light text-black-50">
                            <p className="type">{course.type}</p>
                            <p className="rating">
                                <span>3</span>
                                <span className="text-warning"><i className="fa-solid fa-star"></i></span>
                            </p>
                        </div>
                        <p className="price text-danger fs-5">
                            3.000.000 VND
                        </p>
                    </div>
                </div>
            </Link>
        </div>
    </>
  )
}

export default CourseItem