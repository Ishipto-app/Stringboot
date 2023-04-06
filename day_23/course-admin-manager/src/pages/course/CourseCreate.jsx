import React from 'react'
import { Link } from 'react-router-dom'

import useCreate from "./hooks/useCreate";
import useFetchQuery from "./hooks/useFetchQuery";
import { Controller } from "react-hook-form";
import Select from 'react-select';
import {
    getCategoryOptions,
    getTypeOptions,
    getUserOptions,
} from "./options/options";

function CourseCreate() {
    const navigate = useNavigate();

    const { categories, users, isLoading } = useFetchQuery();
   
    const { control, register, handleSubmit, errors, onCreateCourse } = useCreate();

    const categoryOptions = getCategoryOptions(categories);
    const userOptions = getUserOptions(users);
    const typeOptions = getTypeOptions();

    if (isLoading) {
        return <h2>Loading ...</h2>;
    }
  return (
    <>
        <div className="course-list mt-4 mb-4">
            <form onSubmit={handleSubmit(onCreateCourse)}>
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
                                    <Controller
                                        name="type"
                                        control={control}
                                        defaultValue={typeOptions[0]?.value}
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
                                        defaultValue={userOptions[0]?.value}
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
                                    <label htmlFor="course-price" className="form-label fw-bold">Price</label>
                                    <input type="number" className="form-control" id="course-price" {...register("price")} />
                                    <p className='text-danger'>{errors.description?.price}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </>
  )
}

export default CourseCreate