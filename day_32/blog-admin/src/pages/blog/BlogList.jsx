import React, { useEffect, useState } from 'react'
import { Helmet } from "react-helmet";
import { Link, useNavigate, useParams } from "react-router-dom";
import NotFound from '../not-found/NotFound';
import { useGetAllBlogsQuery, useLazyGetAllBlogsQuery } from '../../app/apis/blogApi';
import ReactPaginate from "react-paginate";

function BlogList() {
    const {page, pageSize} = useParams(); 
    const navigate = useNavigate();
    // const [page, setPage] = useState(1);
    // const [pageSize, setPageSize] = useState(5);

    const [getBlog, {data: pageInfo, isLoading: isLoadingBlog}] = useLazyGetAllBlogsQuery();

    useEffect(() => {
        getBlog({
            page: page ? page : 1,
            pageSize: pageSize ? pageSize : 5,
        })
    }, [page, pageSize])

    const handlePageClick = (selectPage) => {
        navigate("/admin/blogs/" + (selectPage.selected + 1).toString() + "/" + (pageSize ? pageSize : "5"))
    }
    
    if (isLoadingBlog) {
        return <h2>Loading ...</h2>;
    }

    return (
        <>
        {pageInfo && (
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
                                        <ReactPaginate
                                            nextLabel="next >"
                                            onPageChange={handlePageClick}
                                            pageRangeDisplayed={3}
                                            marginPagesDisplayed={2}
                                            forcePage={page ? page - 1 : 0}
                                            pageCount={pageInfo.totalPages}
                                            previousLabel="< previous"
                                            pageClassName="page-item"
                                            pageLinkClassName="page-link"
                                            previousClassName="page-item"
                                            previousLinkClassName="page-link"
                                            nextClassName="page-item"
                                            nextLinkClassName="page-link"
                                            breakLabel="..."
                                            breakClassName="page-item"
                                            breakLinkClassName="page-link"
                                            containerClassName="pagination"
                                            activeClassName="active"
                                            renderOnZeroPageCount={null}
                                        />
                                        {/*
                                        <div class="form-group">
                                            <label>Page size</label>
                                            <select class="form-control" value={pageSize} onChange={navigate("/admin/blogs/" + (selectPage.selected + 1).toString() + "/" + (pageSize ? pageSize : "5"))}>
                                                <option value="5">5</option>
                                                <option value="10">10</option>
                                            </select>
                                            </div> */}
                                    </div>
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

export default BlogList