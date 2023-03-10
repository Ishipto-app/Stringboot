import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import * as yup from "yup";
import { yupResolver } from '@hookform/resolvers/yup';
import { useForm } from "react-hook-form";
import axios from 'axios';
import "bootstrap/dist/js/bootstrap.bundle"


const API_URL = "http://localhost:8080/api/v1/users"

function UserCreate() {
  const [addressList, setAddressList] = useState([]);
  const navigate = useNavigate();

  const schema = yup.object({
    name: yup.string().required("Ten khong duoc de trong"),
    email: yup.string()
      .matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$", "Email khong hop le")
      .required("Email khong duoc de trong"),
    phone: yup.string().required("Phone khong duoc de trong"),
  }).required();
  
  const { register, handleSubmit, formState:{ errors } } = useForm({
    // lien ket thong tin da dinh nghia
    resolver: yupResolver(schema),
    mode: "all"
  });
  
  const onSubmit = async data => {
    try {
      let rs = await axios.post(API_URL, data)
      console.log(rs)
      alert("tao user thanh cong");
      setTimeout(() => {
        navigate("/users/" + rs.data.id)
      }, 1000);
    } catch (error) {
        console.error(error)
    }
  }
  useEffect(() => {
    const getProvinces = async () => {
      try {
          let rs = await fetch(`https://provinces.open-api.vn/api/p/`);
          let provinces = await rs.json();
          setAddressList([...provinces]);
      } catch (error) {
          console.error(error)
      }
    }
    getProvinces();
  }, []);

  const createUser = async () => {
    let userData = {
      name: name,
      email: email,
      phone: phone,
      address: address,
      password: password
    }
    // if(title.trim() === ""){
    //     alert("Tieu de khong duoc de trong");
    //     return;
    // }
    try {
      let rs = await fetch(API_URL, {
        headers: {
        'Content-type': 'application/json; charset=UTF-8'
        },
        method: 'POST',
        body: JSON.stringify(userData)
      });
      console.log(rs)
      let data = await rs.json();
      if(data.id) {
        navigate("/users/" + data.id)
      } else {
        alert("error")
        console.error(data)
      }
      
    } catch (error) {
        console.error(error);
    }
  }
  
  return (
    <>
        <div className="container mt-5 mb-5">
            <h2 className="text-center text-uppercase mb-3">Tạo user</h2>

            <div className="row justify-content-center">
                <div className="col-md-6">
                  <form onSubmit={handleSubmit(onSubmit)}>
                    <div className="bg-light p-4">
                        <div className="mb-3">
                            <label className="col-form-label">Name</label>
                            <input type="text" id="name" className="form-control" {...register("name")} />
                            <p className='text-danger'>{errors.name?.message}</p>
                        </div>
                        <div className="mb-3">
                            <label className="col-form-label">Email</label>
                            <input type="text" id="email" className="form-control" {...register("email")}  />
                            <p className='text-danger'>{errors.email?.message}</p>
                        </div>
                        <div className="mb-3">
                            <label className="col-form-label">Phone</label>
                            <input type="text" id="phone" className="form-control" {...register("phone")} />
                            <p className='text-danger'>{errors.phone?.message}</p>
                        </div>
                        <div className="mb-3">
                            <label className="col-form-label">Address</label>
                            <select className="form-select" id="address" {...register("address")} >
                              <option hidden >Chọn tỉnh thành phố</option>
                              {addressList.map((item) => (
                                <option key={item.code} value={item.name}>{item.name}</option>
                              ))}
                                
                            </select>
                        </div>
                        <div className="mb-3">
                            <label className="col-form-label">Password</label>
                            <input type="text" id="password" className="form-control" {...register("password")}  />
                        </div>
                    </div>
                    <div className="text-center mt-3">
                        <button className="btn btn-secondary btn-back">Quay lại</button>
                        <button className="btn btn-success" type="submit" id="btn-save">Tạo User</button>
                    </div>
                  </form>
                </div>
            </div>
        </div>
    </>
  )
}

export default UserCreate