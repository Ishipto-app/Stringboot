import React, { useState } from 'react'
import "./LoginPage.css";
import { useLoginMutation } from '../../app/apis/authApi';
import { Navigate, useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';

function LoginPage() {
    const navigate = useNavigate()
    const {isAuthenticated} = useSelector((state) => state.auth)
    console.log(isAuthenticated)
    //validate
    const [email, setEmail] = useState();
    const [password, setPassword] = useState();
    const [login] = useLoginMutation()

    if(isAuthenticated){
        //return navigate("/")
        return <Navigate to={"/"} />
    }
    const handleLogin = (e) => {
        e.preventDefault();
        login({email, password})
            .unwrap()
            .then(res => {
                alert("login thanh cong")
                navigate("/")
                console.log(res)
            })
            .catch(err => console.log(err))
    }
  return (
    <>
        <div className="login">
            <h1>Đăng nhập</h1>
            <form onSubmit={handleLogin}>
                <input type="text" 
                    placeholder="Nhập email" 
                    required="required" 
                    value={email} 
                    onChange={e => setEmail(e.target.value)}
                    />
                <input type="password"
                    placeholder="Nhập password" 
                    required="required" 
                    value={password} 
                    onChange={e => setPassword(e.target.value)}
                />
                <button type="submit" className="btn btn-primary btn-block btn-large">Đăng nhập</button>
            </form>
        </div>
    </>
  )
}

export default LoginPage