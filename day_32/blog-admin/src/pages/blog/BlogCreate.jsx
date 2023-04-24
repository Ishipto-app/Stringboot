import React from 'react'
import { useNavigate } from 'react-router-dom';
import useFetchQuery from './hooks/useFetchQuery';
import {
    getCategoryOptions,
    getStatusOptions,
} from "./options/options";
import { Link } from 'react-router-dom'
import { Controller } from 'react-hook-form';
import Select from 'react-select';
import useCreate from './hooks/useCreate';

function BlogCreate() {
    const navigate = useNavigate();

    const { categories, isLoading } = useFetchQuery();
   
    const { control, register, handleSubmit, errors, onCreateBlog } = useCreate();

    const categoryOptions = getCategoryOptions(categories);
    const statusOptions = getStatusOptions();

    const onCreateBlog2 = (data) => {
        console.log(data)
    }
    if (isLoading) {
        return <h2>Loading ...</h2>;
    }
  return (
    <>
        <section className="content">
            <form  className="container-fluid" onSubmit={handleSubmit(onCreateBlog)}>
                <div className="row py-2">
                    <div className="col-6">
                        <button type="button" className="btn btn-default">
                            <i className="fas fa-chevron-left"></i> Quay lại
                        </button>
                        <button type="submit" className="btn btn-info px-4">
                            Lưu
                        </button>
                        <Link to="/admin/blogs" className="btn btn-primary px-4">
                            Preview
                        </Link>
                    </div>

                    <div className="col-6 d-flex justify-content-end">
                    </div>
                </div>

                <div className="row">
                    <div className="col-12">
                        <div className="card">
                            <div className="card-body">
                                <div className="row">
                                    <div className="col-md-8">
                                        <div className="form-group">
                                            <label>Tiêu đề</label>
                                            <input type="text" className="form-control" id="title" {...register("title")} />
                                            <p className='text-danger'>{errors.title?.message}</p>
                                        </div>

                                        <div className="form-group">
                                            <label>Nội dung</label>
                                            <textarea id="content" {...register("content")}></textarea>
                                            <p className='text-danger'>{errors.content?.message}</p>
                                        </div>

                                        <div className="form-group">
                                            <label>Mô tả ngắn</label>
                                            <textarea id="description" {...register("description")} rows="3"></textarea>
                                            <p className='text-danger'>{errors.description?.message}</p>
                                        </div>
                                    </div>

                                    <div className="col-md-4">
                                        <div className="form-group">
                                            <label>Trạng thái</label>
                                            <Controller
                                                name="status"
                                                control={control}
                                                defaultValue={statusOptions[0]?.value}
                                                render={({ field }) => (
                                                    <Select
                                                        {...field}
                                                        placeholder="-- Chọn status --"
                                                        options={statusOptions}
                                                        value={statusOptions.find(
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
                                        <div className="form-group">
                                            <label>Danh mục</label>
                                            <div className="select2-purple">
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
                                        </div>
                                        <div className="form-group">
                                            <div className="thumbnail-preview-container mb-3">
                                                <img src="" alt="" id="thumbnail" />
                                            </div>
                                            <button type="button" className="btn btn-info btn-flat" data-toggle="modal"
                                                data-target="#modal-xl">
                                                Chọn hình ảnh
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </section>
    </>
  )
}

export default BlogCreate