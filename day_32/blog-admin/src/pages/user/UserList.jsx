import React, { useEffect } from 'react'
import { Helmet } from "react-helmet";
import { Link, useNavigate, useParams } from "react-router-dom";
import { useGetAllUsersQuery, useLazyGetAllUsersQuery } from '../../app/apis/userApi';
import ReactPaginate from 'react-paginate';

function UserList() {
    const {page, pageSize} = useParams(); 
    const navigate = useNavigate();
    // const [page, setPage] = useState(1);
    // const [pageSize, setPageSize] = useState(5);

    const [getUser, {data: pageInfo, isLoading: isLoadingUser}] = useLazyGetAllUsersQuery();
    console.log(pageInfo)
    //const { data: categories, isLoading: isLoadingUser, isError: isLoadingUserError, error: loadingUserError } = useGetAllUsersQuery();

    useEffect(() => {
        getUser({
            page: page ? page : 1,
            pageSize: pageSize ? pageSize : 5,
        })
    }, [page, pageSize])
    
    const handlePageClick = (selectPage) => {
        navigate("/admin/user/" + (selectPage.selected + 1).toString() + "/" + (pageSize ? pageSize : "5"))
    }
    
    if (isLoadingUser) {
        return <h2>Loading ...</h2>;
    }
  return (
    <>
    {pageInfo && (
        <section className="content">
            <div className="container-fluid">
                <div className="row py-2">
                    <div className="col-12">
                        <Link to="/admin/user/create" className="btn btn-primary" >
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
                                <h1 style={{marginBottom: "1rem"}}>User</h1>
                                <table className="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>No</th>
                                            <th>Name</th>
                                            <th>Email</th>
                                            <th>Avatar</th>
                                            <th>Role</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {pageInfo && pageInfo.content.map((user, index) => (
                                            <tr key={user.id}>
                                                <td>{ index + 1 }</td>
                                                <td>
                                                    <Link to={"/admin/user/" + user.id}>{user.name}</Link>
                                                </td>
                                                <td>{user.email}</td>
                                                <td>{user.avatar}</td>
                                                <td>
                                                    {user.roles.map(r => r.name).join(", ")}
                                                </td>
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
                                        <select class="form-control" value={pageSize} onChange={navigate("/admin/user/" + (selectPage.selected + 1).toString() + "/" + (pageSize ? pageSize : "5"))}>
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

export default UserList