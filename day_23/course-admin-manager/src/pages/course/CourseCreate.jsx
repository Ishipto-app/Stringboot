import React, {useState, useEffect} from 'react'
import { useNavigate } from 'react-router-dom'
import { Link } from "react-router-dom";
import { useGetAllCategoryQuery } from '../../app/serivce/categoryService';
import { useCreateCourseMutation } from '../../app/serivce/courseService';
import { useGetAllUserQuery } from '../../app/serivce/userService';
import * as yup from "yup";
import { yupResolver } from '@hookform/resolvers/yup';
import { useForm } from "react-hook-form";
import Select from 'react-select';

function CourseCreate() {
    //const [options, setOptions] = useState([])
    const navigate = useNavigate();
    const {data: categoryData} = useGetAllCategoryQuery();
    const {data: userData} = useGetAllUserQuery();

    const [createData, { isLoading: isCreating, isError: isCreateError, error: createError }] = useCreateCourseMutation();

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

    useEffect(() => {
        //cho load du lieu de set data vao form
        if(userData && categoryData){
            setOptions(changeCategoryToOption(categoryData));
        }
    }, [userData, categoryData]);

    let options = [];
    if(categoryData && categoryData.length > 0){
        options = changeCategoryToOption(categoryData);
    }

    const onSubmitCourse = async data => {
        let newData = {...data}
        if(newData.categories) newData.categories = changeSelectOptionToCategory(data.categories)
        newData.user = userData.find(user => user.id == newData.user);
        createData(newData)
            .unwrap()
            .then((data) => {
                alert("create success")
                //chuyen huong
                navigate("/admin/khoa-hoc/" + data.id)
            })
            .catch(err => alert('create error'));
    }

    if(isCreating) {
        return <h2>Loading...</h2>
    }
    if(isCreateError){
        console.log(createError)
        //return <h2>Error: {createError}</h2>
    }
  return (
    <>
        {userData && categoryData &&
        <div className="course-list mt-4 mb-4">
            <form onSubmit={handleSubmit(onSubmitCourse)}>
                <div className="container">
                    <div className="mb-4">
                        <button className="btn-custom btn-create-course" type="submit">
                            <span><i className="fa-solid fa-plus"></i></span>
                            Tạo
                        </button>
                        <Link to="/admin/khoa-hoc" className="btn-custom btn-refresh">
                            <span><i className="fa-solid fa-angle-left"></i></span>
                            Quay lại
                        </Link>
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
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        }
    </>
  )
}

export default CourseCreate