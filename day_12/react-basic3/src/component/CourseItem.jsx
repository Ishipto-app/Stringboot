import React from 'react'

function CourseItem({data}) {
  return (
    <div className="col-md-4">
        <a href="#">
            <div className="course-item shadow-sm rounded mb-4">
                <div className="course-item-image">
                    <img src={data.src}
                        alt={data.alt} />
                </div>
                <div className="course-item-info p-3">
                    <h2 className="fs-5 mb-3 text-dark">{data.name}</h2>
                    <p className="type fw-light text-black-50">{data.type}</p>
                </div>
            </div>
        </a>
    </div>
  )
}

export default CourseItem