import React, {useEffect, useState} from 'react'
import { Link, useParams, useNavigate } from "react-router-dom";
import { useGetCourseByIdQuery, useDeleteCourseMutation, useUpdateCourseMutation } from '../../app/serivce/courseService';
import * as yup from "yup";
import { yupResolver } from '@hookform/resolvers/yup';
import { useForm } from "react-hook-form";
import { useGetAllCategoryQuery } from '../../app/serivce/categoryService';
import { useGetAllUserQuery } from '../../app/serivce/userService';
import Select from 'react-select';

function CourseUpdate() {
    const {id} = useParams();
    //const [options, setOptions] = useState([])
    // const [course, setCourse] = useState({
    //     id: "",
    //     name: "",
    //     description: "",
    //     type: "",
    //     categories: "",
    //     thumbnail: "",
    //     price: "",
    //     rating: "",
    //     user: "",
    // });
    const {data: courseData, isLoading, isError, error} = useGetCourseByIdQuery(id);
    const {data: categoryData} = useGetAllCategoryQuery();
    const {data: userData} = useGetAllUserQuery();

    const [updateData, { isLoading: isUpdating, isError: isUpdateError, error: updateError }] = useUpdateCourseMutation();

    //const navigate = useNavigate();

    const changeCategoryToOption = (categories) => {
        return categories.map((category) => ({
            value: category.id,
            label: category.name,
        }))
    }
    const changeSelectOptionToCategory = (selectOptions) => {
        return selectOptions.map((item) => ({
            id: item.value,
            name: item.label,
        }))
    }
    
    const [deleteCourse] = useDeleteCourseMutation()

    const handDelete = (id) => {
        deleteCourse(id)
            .unwrap()
            .then(() => {
                alert("xoa thanh cong");
            })
            .catch(err => alert("xoa error"));
    }
      
    const schema = yup.object({
        name: yup.string().required("Ten khong duoc de trong"),
        description: yup.string()
            .min(50, "Mo ta co it nhat 50 ky tu")
            .required("Mo ta khong duoc de trong"),
        type: yup.string().required("type khong duoc de trong"),
        user: yup.string().required("User khong duoc de trong"),
    }).required();
      
    const { register, handleSubmit, formState:{ errors }, setValue } = useForm({
        resolver: yupResolver(schema),
        mode: "all"
    });

    let options = [];
    if(categoryData && categoryData.length > 0){
        options = changeCategoryToOption(categoryData);
    }

    useEffect(() => {
        //cho load du lieu de set data vao form
        if(courseData && userData && categoryData){
            setValue('name', courseData.name);
            setValue('description', courseData.description);
            setValue('type', courseData.type);
            setValue('categories', changeCategoryToOption(courseData.categories));
            setValue('thumbnail', courseData.thumbnail);
            setValue('price', courseData.price);
            setValue('rating', courseData.rating);
            setValue('user', courseData.user.id);
        }
    }, [courseData, userData, categoryData, setValue]);

    const onSubmitCourse = async data => {
        let newData = {...data}
        if(newData.categories) newData.categories = changeSelectOptionToCategory(data.categories)
        newData.user = userData.find(user => user.id == newData.user);
        newData.id = id;
        updateData(newData)
            .unwrap()
            .then((data) => {
                alert("update success")
            })
            .catch(err => alert('update error'));
    }

    if(isLoading) {
        return <h2>Loading...</h2>
    }
    if(isError){
        console.error(error)
        //return <h2>Error: {error}</h2>
    }
    if(isUpdateError){
        console.error(updateError)
        //return <h2>Error: {updateError}</h2>
    }
  return (
    <>
        {courseData && userData && categoryData &&
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
                            <button className="btn-custom btn-delete-course bg-danger" onClick={() => handDelete(data.data.id)}>
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
                                    <select className="form-control" id="course-type" {...register("type")}>
                                        <option hidden value="">- Chọn hình thức học</option>
                                        <option value="online">Online</option>
                                        <option value="onlab">Onlab</option>
                                    </select>
                                    <p className='text-danger'>{errors.type?.message}</p>
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="course-topic" className="form-label fw-bold">Chủ đề</label>
                                    <Select
                                        isMulti
                                        name="categories"
                                        {...register("categories")}
                                        defaultValue={options.filter(item => (courseData.categories.map(category => category.id)).includes(item.value))}
                                        options={options}
                                        onChange={e => setValue('categories', e)}
                                    />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="course-supporter" className="form-label fw-bold">Tư vấn viên</label>
                                    <select className="form-control" id="course-supporter" {...register("user")}>
                                        <option hidden value="">- Chọn tư vấn viên</option>
                                        {userData && userData.map(user => (
                                            <option key={user.id} value={user.id}>{user.name}</option>
                                        ))}
                                    </select>
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
        }
    </>
  )
}

export default CourseUpdate