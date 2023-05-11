import React from 'react'
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom'
import { useCreateCategory } from '../hooks/useCreate';

function CategoryCreate() {
    const navigate = useNavigate();
    console.log(navigate)
   
    const { control, register, handleSubmit, errors, onCreateCategory } = useCreateCategory();

  return (
    <>
        <section className="content">
            <form  className="container-fluid" onSubmit={handleSubmit(onCreateCategory)}>
                <div className="row py-2">
                    <div className="col-6">
                        <button type="button" className="btn btn-default" onClick={() => navigate(-1)}>
                            <i className="fas fa-chevron-left"></i> Quay lại
                        </button>
                        <button type="submit" className="btn btn-info px-4">
                            Lưu
                        </button>
                        <Link to="/admin/category" className="btn btn-primary px-4">
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
                                <h1 style={{marginBottom: "1rem"}}>Danh mục</h1>
                                <div className="row">
                                    <div className="col-md-8">
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
    </>
  )
}

export default CategoryCreate