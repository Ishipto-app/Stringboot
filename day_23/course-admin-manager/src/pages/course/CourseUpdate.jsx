import React, {useEffect, useState} from 'react'
import { Link, useParams, useNavigate } from "react-router-dom";
import { useGetCourseByIdQuery, useDeleteCourseMutation, useUpdateCourseMutation } from '../../app/serivce/courseService';

import {
    getCategoryOptions,
    getTypeOptions,
    getUserOptions,
} from "./options/options";

import { Controller } from "react-hook-form";
import Select from 'react-select';

import useFetchQuery from './hooks/useFetchQuery';
import useCreate from './hooks/useCreate';

function CourseUpdate() {
    const navigate = useNavigate();
    const {id} = useParams();
    const { categories, users, isLoading: loadItem } = useFetchQuery();
    const {data: courseData, isLoading, isError, error} = useGetCourseByIdQuery(id);

    const [updateData, { isLoading: isUpdating, isError: isUpdateError, error: updateError }] = useUpdateCourseMutation();

    const { control, register, handleSubmit, errors, setValue } = useCreate();

    const categoryOptions = getCategoryOptions(categories);
    const userOptions = getUserOptions(users);
    const typeOptions = getTypeOptions();

    console.log(categoryOptions)
    
    const [deleteCourse] = useDeleteCourseMutation()

    const handDelete = (id) => {
        deleteCourse(id)
            .unwrap()
            .then(() => {
                alert("xoa thanh cong");
                //chuyen huong
                navigate("/admin/khoa-hoc/")
            })
            .catch(err => alert("xoa error"));
    }
      
    useEffect(() => {
        if(courseData){
            setValue('name', courseData.name);
            setValue('description', courseData.description);
            setValue('type', courseData.type);
            setValue('categoryIds', courseData.categories.map(a => a.id));
            setValue('thumbnail', courseData.thumbnail);
            setValue('price', courseData.price);
            setValue('rating', courseData.rating);
            setValue('userId', courseData.user.id);
        }
    }, [courseData, setValue]);

    const onSubmitCourse = data => {
        let newData = {...data}
        newData.categories = categories.filter(a => data.categoryIds.includes(a.id));
        newData.user = users.find(user => user.id == newData.userId);
        newData.id = id;
        console.log(newData)
        updateData(newData)
            .unwrap()
            .then((res) => {
                alert("update success")
            })
            .catch(err => {
                console.error(err)
                alert('create error')
            });
    }

    if(isLoading) {
        return <h2>Loading...</h2>
    }
    if(isError){
        console.error(error)
        return <h2>Error</h2>
    }
    if(isUpdateError){
        console.error(updateError)
        return <h2>Error</h2>
    }
  return (
    <>
        <div className="course-list mt-4 mb-4">
            <div className="container">
                <form onSubmit={handleSubmit(onSubmitCourse)}>
                    <div className="mb-4 d-flex justify-content-between">
                        <div>
                            <button className="btn-custom btn-update-course" type="submit">
                                <span><i className="fa-solid fa-plus"></i></span>
                                Cập nhật
                            </button>
                            <Link to="/admin/khoa-hoc" className="btn-custom btn-refresh">
                                <span><i className="fa-solid fa-angle-left"></i></span>
                                Quay lại
                            </Link>
                        </div>
                        <div>
                            <button className="btn-custom btn-delete-course bg-danger" type="button" onClick={() => handDelete(id)}>
                                <span><i className="fa-solid fa-trash-can"></i></span>
                                Xóa
                            </button>
                        </div>
                    </div>

                    <div className="course-list-inner p-2">
                        <div className="row">
                            <div className="col-md-8">
                                <div className="mb-3">
                                    <label htmlFor="course-name" className="form-label fw-bold">Tên khóa học</label>
                                    <input type="text" className="form-control" id="course-name" {...register("name")} />
                                    <p className='text-danger'>{errors.name?.message}</p>
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="course-description" className="form-label fw-bold">Mô tả</label>
                                    <textarea className="form-control" id="course-description" rows="10"  {...register("description")}></textarea>
                                    <p className='text-danger'>{errors.description?.message}</p>
                                </div>
                            </div>
                            <div className="col-md-4">
                                <div className="mb-3">
                                    <label htmlFor="course-type" className="form-label fw-bold">Hình thức học</label>
                                    <Controller
                                        name="type"
                                        control={control}
                                        defaultValue={courseData?.type}
                                        render={({ field }) => (
                                            <Select
                                                {...field}
                                                placeholder="-- Chọn hình thức học --"
                                                options={typeOptions}
                                                value={typeOptions.find(
                                                    (c) =>
                                                        c.value === field.value
                                                )}
                                                onChange={(val) =>
                                                    field.onChange(val.value)
                                                }
                                            />
                                        )}
                                    />
                                    <p className='text-danger'>{errors.type?.message}</p>
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="course-topic" className="form-label fw-bold">Chủ đề</label>
                                    <Controller
                                        name="categoryIds"
                                        control={control}
                                        render={({
                                            field: { onChange, value, ref },
                                        }) => (
                                            <Select
                                                placeholder="-- Chọn danh mục --"
                                                inputRef={ref}
                                                value={categoryOptions.find(
                                                    (c) => c.value === value
                                                )}
                                                defaultValue={categoryOptions.filter(item => (courseData.categories.map(category => category.id)).includes(item.value))}
                                                onChange={(val) =>
                                                    onChange(
                                                        val.map((c) => c.value)
                                                    )
                                                }
                                                options={categoryOptions}
                                                isMulti
                                            />
                                        )}
                                    />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="course-supporter" className="form-label fw-bold">Tư vấn viên</label>
                                    <Controller
                                        name="userId"
                                        control={control}
                                        defaultValue={courseData?.user?.id}
                                        render={({ field }) => (
                                            <Select
                                                {...field}
                                                placeholder="-- Chọn nhân viên tư vấn --"
                                                options={userOptions}
                                                value={userOptions.find(
                                                    (c) =>
                                                        c.value === field.value
                                                )}
                                                onChange={(val) =>
                                                    field.onChange(val.value)
                                                }
                                            />
                                        )}
                                    />
                                </div>

                                <div className="mb-3">
                                    <label className="form-label fw-bold">Thumnail</label>
                                    <div className="course-logo-preview mb-3 rounded">
                                        <img id="course-logo-preview" className="rounded" src={courseData?.thumbnail ? courseData?.thumbnail : "https://via.placeholder.com/200"} />
                                    </div>

                                    <label htmlFor="course-logo-input" className="btn btn-warning">Đổi ảnh</label>
                                        <input type="file" id="course-logo-input" className="d-none" {...register("thumbnail")}/>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </>
  )
}

export default CourseUpdate