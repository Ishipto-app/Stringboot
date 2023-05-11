import React from 'react'
import { Helmet } from "react-helmet";
import { Link } from "react-router-dom";
import { useDeleteCategoryMutation, useGetAllCategoriesQuery } from '../../app/apis/categoryApi';

function CategoryList() {
    const { data: categories, isLoading: isLoadingCategory, isError: isLoadingCategoryError, error: loadingCategoryError } = useGetAllCategoriesQuery();
    
    const [deleteCategory] = useDeleteCategoryMutation()

    const handDelete = (id) => {
        deleteCategory(id)
            .unwrap()
            .then(() => {
                alert("xoa thanh cong");
            })
            .catch(err => alert("xoa error"));
    }
    
    if(isLoadingCategory) {
        return <h2>Loading...</h2>
    }
    if(isLoadingCategoryError){
        return <h2>Error: {loadingCategoryError}</h2>
    }
  return (
    <>
    {categories && (
        <section className="content">
            <div className="container-fluid">
                <div className="row py-2">
                    <div className="col-12">
                        <Link to="/admin/category/create" className="btn btn-primary" >
                            <i className="fas fa-plus"></i> Thêm mới
                        </Link>
                        <button type="button" className="btn btn-info">
                            <i className="fas fa-redo"></i> Refresh
                        </button>
                    </div>
                </div>
                <div className="row">
                    <div className="col-12">
                        <div className="card">
                            <div className="card-body">
                                <h1 style={{marginBottom: "1rem"}}>Danh mục</h1>
                                <table className="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>No</th>
                                            <th>Name</th>
                                            <th>Used</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {categories && categories.map((category, index) => (
                                            <tr key={category.id}>
                                                <td>
                                                    {index + 1}
                                                </td>
                                                <td>
                                                    <Link to={"/admin/category/" + category.id}>{category.name}</Link>
                                                </td>
                                                <td>{category.used}</td>
                                                <td>
                                                    {category.used == 0 && (<button type="button" className="btn btn-danger px-4" onClick={() => handDelete(category.id)}>
                                                        Xóa
                                                    </button>)}
                                                </td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    )}
    </>
  )
}

export default CategoryList