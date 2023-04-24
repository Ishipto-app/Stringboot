import React, { useEffect, useState } from 'react'
import { Helmet } from "react-helmet";
import { Link, useNavigate, useParams } from "react-router-dom";
import NotFound from '../not-found/NotFound';
import { useGetAllBlogsQuery } from '../../app/apis/blogApi';

function BlogList() {
    let { page, pageSize } = useParams();
    if(!page) page = 1;
    if(!pageSize) pageSize = 5;
    if (!Number(page) || !Number(pageSize)) { // Nếu page không phải là số => NotFoundPage
        return <NotFound />;
    }
    const { data: pageInfo, isLoading: isLoadingBlog } = useGetAllBlogsQuery({
        page: page,
        pageSize: pageSize,
    });
    if (pageInfo && pageInfo.totalPages < page ) { // Nếu page không phải là số => NotFoundPage
        return <NotFound />;
    }
    
    if (isLoadingBlog) {
        return <h2>Loading ...</h2>;
    }

    return (
        <>
            <section className="content">
                <div className="container-fluid">
                    <div className="row py-2">
                        <div className="col-12">
                            <Link to="/admin/blogs/create" className="btn btn-primary" >
                                <i className="fas fa-plus"></i> Viết bài
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
                                    <table className="table table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th>Tiêu đề</th>
                                                <th>Tác giả</th>
                                                <th>Danh mục</th>
                                                <th>Trạng thái</th>
                                                <th>Ngày tạo</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {pageInfo && pageInfo.content.map((blog) => (
                                                <tr key={blog.id}>
                                                    <td>
                                                        <Link to={"/admin/blogs/" + blog.id}>{blog.content}</Link>
                                                    </td>
                                                    <td>
                                                        <Link to={"/admin/users/" + blog.user.id}>{blog.user.name}</Link>
                                                    </td>
                                                    <td>
                                                        {blog.categories.map(c => c.name).join(", ")}
                                                    </td>
                                                    <td>{blog.status ? "Công khai" : "Nháp"}</td>
                                                    <td>{new Date(blog.createdAt).toLocaleDateString()}</td>
                                                </tr>
                                            ))}
                                        </tbody>
                                    </table>
                                    <div className="d-flex justify-content-center mt-3" id="pagination">
                                        <ul className="pagination mb-0">
                                            {!pageInfo.first && (
                                                <li className="paginate_button page-item previous"
                                                    id="example2_previous">
                                                    <Link to={page == 2 ? "/admin/blogs" : `/admin/blogs/${Number(page) - 1}/5`} aria-controls="example2" 
                                                        className="page-link">Previous</Link>
                                                </li>
                                            )}
                                            {[...Array(pageInfo.totalPages).keys()].map(pageNumber => (
                                                <li className="paginate_button page-item active" key={pageNumber}>
                                                    <Link to={pageNumber == 0 ? "/admin/blogs" : `/admin/blogs/${pageNumber + 1}/5`} aria-controls="example2" 
                                                        className="page-link">{pageNumber + 1}</Link>
                                                </li>
                                            ))}
                                            {!pageInfo.last && (
                                                <li className="paginate_button page-item next" id="example2_next">
                                                    <Link to={`/admin/blogs/${Number(page) + 1}/5`} aria-controls="example2" 
                                                        className="page-link">Next</Link>
                                                </li>
                                            )}
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </>
    )
}

export default BlogList