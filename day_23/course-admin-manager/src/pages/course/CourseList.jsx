import React, { useState } from 'react'
import { Link } from "react-router-dom";
import { useDeleteCourseMutation, useGetAllCourseQuery } from '../../app/serivce/courseService';

function CourseList() {
    // data - The latest returned result regardless of hook arg, if present.
    // currentData - The latest returned result for the current hook arg, if present.
    // error - The error result if present.
    // isUninitialized - When true, indicates that the query has not started yet.
    // isLoading - When true, indicates that the query is currently loading for the first time, and has no data yet. This will be true for the first request fired off, but not for subsequent requests.
    // isFetching - When true, indicates that the query is currently fetching, but might have data from an earlier request. This will be true for both the first request fired off, as well as subsequent requests.
    // isSuccess - When true, indicates that the query has data from a successful request.
    // isError - When true, indicates that the query is in an error state.
    // refetch - A function to force refetch the query
    const {data, isLoading, isError, error} = useGetAllCourseQuery();

    const [deleteCourse] = useDeleteCourseMutation()

    const handDelete = (id) => {
        deleteCourse(id)
            .unwrap()
            .then(() => {
                alert("xoa thanh cong");
            })
            .catch(err => alert(err));

    }
    if(isLoading) {
        return <h2>Loading...</h2>
    }
    if(isError){
        return <h2>Error: {error}</h2>
    }
  return (
    <>
        <div className="course-list mt-4 mb-4">
            <div className="container">
                <div className="mb-4">
                    <Link to="/admin/khoa-hoc/tao-khoa-hoc" className="btn-custom btn-create-course">
                        <span><i className="fa-solid fa-plus"></i></span>
                        Tạo khóa học
                    </Link>
                    <Link to="/admin/khoa-hoc" className="btn-custom btn-refresh">
                        <span><i className="fa-solid fa-arrow-rotate-right"></i></span>
                        Refresh
                    </Link>
                </div>

                <div className="course-list-inner p-2">
                    <table className="table course-table">
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Tên khóa học</th>
                                <th>Hình thức</th>
                                <th>Chủ đề</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {data && data.data.length > 0 && data.data.map((course, index) => (
                            <tr key={course.id}>
                                <td>{index + 1}</td>
                                <td>
                                    <Link to={"/admin/khoa-hoc/" + course.id}>{course.name}</Link>
                                </td>
                                <td className="text-info">{course.type}</td>
                                <td>{course.categories.map(category => category.name).join(", ")}</td>
                                <td>
                                    <button className='btn btn-danger' onClick={() => handDelete(course.id)}>Delete</button>
                                </td>
                            </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </>
  )
}

export default CourseList