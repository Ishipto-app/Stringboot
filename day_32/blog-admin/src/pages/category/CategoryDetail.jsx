import React, { useEffect } from 'react'
import { Helmet } from "react-helmet";
import { Link, useNavigate, useParams } from "react-router-dom";
import { useGetCategoryByIdQuery } from '../../app/apis/categoryApi';
import { useUpdateCategory } from '../hooks/useUpdate';

function CategoryDetail() {
    const navigate = useNavigate();
    const {categoryId} = useParams();
    const { data: categoryData, isLoading: isLoadingCategory, isError: isLoadingCategoryError, error: loadingCategoryError } = useGetCategoryByIdQuery(categoryId);
    
    const { control, register, handleSubmit, errors, setValue, onUpdateCategory } = useUpdateCategory(categoryId);
    
    useEffect(() => {
        if(categoryData){
            setValue('name', categoryData.name);
        }
    }, [categoryData, setValue]);

    if(isLoadingCategory) {
        return <h2>Loading...</h2>
    }
    if(isLoadingCategoryError){
        return <h2>Error: {loadingCategoryError}</h2>
    }
  return (
    <>
    {categoryData && (
        <section className="content">
            <form  className="container-fluid" onSubmit={handleSubmit(onUpdateCategory)}>
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
                </div>

                <div className="row">
                    <div className="col-12">
                        <div className="card">
                            <div className="card-body">
                                <h1 style={{marginBottom: "1rem"}}>Danh mục</h1>
                                <div className="row">
                                    <div className="col-md-12">
                                        <div className="form-group">
                                            <label>Name</label>
                                            <input type="text" className="form-control" id="name" {...register("name")} />
                                            <p className='text-danger'>{errors.name?.message}</p>
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

export default CategoryDetail