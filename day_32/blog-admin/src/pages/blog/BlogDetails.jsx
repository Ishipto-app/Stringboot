import React, { useEffect } from 'react'
import { Link, useNavigate, useParams } from "react-router-dom";
import { useDeleteBlogMutation, useGetBlogByIdQuery } from '../../app/apis/blogApi';
import { useUpdateBlog } from '../hooks/useUpdate';
import useFetchQuery from '../hooks/useFetchQuery';
import { getCategoryOptions, getStatusOptions } from '../options/options';
import { Controller } from 'react-hook-form';
import Select from 'react-select';

function BlogDetails() {
    const navigate = useNavigate();
    const {blogId} = useParams();
    const { categories, isLoading: loadItem } = useFetchQuery();
    const {data: blogData, isLoading, isError, error} = useGetBlogByIdQuery(blogId);

    const { control, register, handleSubmit, errors, setValue, onUpdateBlog } = useUpdateBlog(blogId);

    const categoryOptions = getCategoryOptions(categories);
    const statusOptions = getStatusOptions();

    
    const [deleteBlog] = useDeleteBlogMutation()

    const handDelete = (id) => {
        deleteBlog(id)
            .unwrap()
            .then(() => {
                alert("xoa thanh cong");
                //chuyen huong
                navigate("/admin/blogs/")
            })
            .catch(err => alert("xoa error"));
    }
    useEffect(() => {
        if(blogData){
            setValue('title', blogData.title);
            setValue('description', blogData.description);
            setValue('content', blogData.content);
            setValue('status', blogData.status);
            setValue('categories', blogData.categories.map(a => a.id));
            setValue('thumbnail', blogData.thumbnail);
        }
    }, [blogData, setValue]);

  return (
    <>
    {blogData && (
        <section className="content">
            <form  className="container-fluid" onSubmit={handleSubmit(onUpdateBlog)}>
                <div className="row py-2">
                    <div className="col-6">
                        <button type="button" className="btn btn-default" onClick={() => navigate(-1)}>
                            <i className="fas fa-chevron-left"></i> Quay lại
                        </button>
                        <button type="submit" className="btn btn-info px-4">
                            Lưu
                        </button>
                        <button type="button" className="btn btn-primary px-4">
                            Preview
                        </button>
                    </div>

                    <div className="col-6 d-flex justify-content-end">
                        <button type="button" className="btn btn-danger px-4" onClick={() => handDelete(blogId)}>
                            Xóa
                        </button>
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
                                            <textarea id="content"{...register("content")}></textarea>
                                            <p className='text-danger'>{errors.content?.message}</p>
                                        </div>

                                        <div className="form-group">
                                            <label>Mô tả ngắn</label>
                                            <textarea id="description" className="form-control" {...register("description")} rows="3"></textarea>
                                            <p className='text-danger'>{errors.description?.message}</p>
                                        </div>
                                    </div>

                                    <div className="col-md-4">
                                        <div className="form-group">
                                            <label>Trạng thái</label>
                                            <Controller
                                                name="status"
                                                control={control}
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
                                                            defaultValue={categoryOptions.filter(item => (blogData.categories.map(category => category.id)).includes(item.value))}
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
    )}
    </>
  )
}

export default BlogDetails