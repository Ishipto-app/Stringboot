import React, { useState } from 'react'
import "./LoginPage.css";
import { useLoginMutation } from '../../app/apis/authApi';
import { Navigate, useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';

function LoginPage() {
    const navigate = useNavigate()
    const {isAuthenticated} = useSelector((state) => state.auth)
    //validate
    const [email, setEmail] = useState();
    const [password, setPassword] = useState();
    const [login] = useLoginMutation()

    if(isAuthenticated){
        return <Navigate to={"/admin/blogs/own-blog"} />
    }
    const handleLogin = (e) => {
        e.preventDefault();
        login({email, password})
            .unwrap()
            .then(res => {
                alert("login thanh cong")
                navigate("/admin/blogs/own-blog")
                console.log(res)
            })
            .catch(err => console.log(err))
    }
  return (
    <>
        <div
            className="d-flex justify-content-center align-items-center"
            style={{ height: "100vh", overflow: "hidden" }}
        >
            <div className="login shadow p-5" style={{ width: 500 }}>
                <h1 className="mb-3 text-center">Đăng nhập</h1>
                <form onSubmit={handleLogin}>
                    <input
                        className="form-control mb-3"
                        type="text"
                        placeholder="Nhập email"
                        required="required"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                    <input
                        className="form-control mb-3"
                        type="password"
                        placeholder="Nhập password"
                        required="required"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                    <button type="submit" className="btn btn-primary btn-block">
                        Đăng nhập
                    </button>
                </form>
            </div>
        </div>
    </>
  )
}

export default LoginPage