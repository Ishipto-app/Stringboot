import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import * as yup from "yup";
import { yupResolver } from '@hookform/resolvers/yup';
import { useForm } from "react-hook-form";
import axios from 'axios';


const API_URL = "http://localhost:8080/api/v1/users"

function UserCreate() {
  const [addressList, setAddressList] = useState([]);
  const navigate = useNavigate();

  const schema = yup.object({
    name: yup.string().required("Ten khong duoc de trong"),
    email: yup.string()
      .matches(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/, "Email khong hop le")
      .required("Email khong duoc de trong"),
    phone: yup.string()
      //0912345678
      .matches(/(0[3|5|7|8|9])+([0-9]{8})\b/g, "Phone khong dung dinh dang")
      .required("Phone khong duoc de trong"),
    address: yup.string().required("Dia chi khong duoc de trong")
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
                              <option hidden value="">Chọn tỉnh thành phố</option>
                              {addressList.map((item) => (
                                <option key={item.code} value={item.name}>{item.name}</option>
                              ))}
                                
                            </select>
                            <p className='text-danger'>{errors.address?.message}</p>
                        </div>
                        <div className="mb-3">
                            <label className="col-form-label">Password</label>
                            <input type="text" id="password" className="form-control" {...register("password")}  />
                        </div>
                    </div>
                    <div className="text-center mt-3">
                        <button className="btn btn-secondary btn-back" type="button" onClick={() => navigate("/users")}>Quay lại</button>
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