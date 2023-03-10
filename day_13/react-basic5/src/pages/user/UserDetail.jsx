import React, { useRef, useState, useEffect} from 'react';
import { useParams, useNavigate } from "react-router-dom";
import axios from 'axios';
import * as yup from "yup";
import { yupResolver } from '@hookform/resolvers/yup';
import { useForm } from "react-hook-form";
const API_URL = "http://localhost:8080/api/v1/users";
import * as bootstrap from "bootstrap/dist/js/bootstrap.bundle";

// mot so thu vien validate form
// React-hook-form
// Formik
// Redux-form

function UserDetail() {
  const modalRef = useRef(null);
  const modalElement = modalRef.current;
  let modal
  if (modalElement) {
    modal = new bootstrap.Modal(modalElement);
  }
  //lay ra user id tren url
  
  const {userId} = useParams();
  const [addressList, setAddressList] = useState([]);

  // controller <input> value null/underfind change to ""
  const [user, setUser] = useState({
    id: "",
    name: "",
    email: "",
    phone: "",
    address: "",
    avatar: ""
  });
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
  }).required();
  
  const { register, handleSubmit, formState:{ errors }, setValue } = useForm({
    // lien ket thong tin da dinh nghia
    resolver: yupResolver(schema),
    mode: "all"
  });
  
  const onSubmit = async data => {
    try {
      let rs = await axios.put(API_URL + "/" + userId, data)
      console.log(rs)
      alert("update user thanh cong");
    } catch (error) {
        console.error(error)
    }
  }

  const changePassword = yup.object({
    oldPassword: yup.string()
      .required("Nhap lai password"),
    newPassword: yup.string()
      .min(6, "Mat khau moi co it nhat 6 ky tu")
      .required("Nhap password moi")
  }).required();
  
  const { register: reUpdatePass, handleSubmit: handleSubmitPass, formState:{ errors: errorsPass } } = useForm({
    // lien ket thong tin da dinh nghia
    resolver: yupResolver(changePassword),
    mode: "all"
  });  

  const onSubmitPass = async data => {
    try {
      let rs = await axios.put(API_URL + "/" + userId + "/update-password", data)
      console.log(rs)
      if(modal) modal.hide();
      alert("update password thanh cong");
    } catch (error) {
        console.error(error)
        console.log(error.resopnse.data)
        alert("update fail")
    }
  }

  useEffect(() => {
    const getProvinces = async () => {
      try {
          let rs = await axios.get(`https://provinces.open-api.vn/api/p/`);
          setAddressList([...rs.data]);
      } catch (error) {
          console.error(error)
      }
    }
    getProvinces();
    const getUserById = async () => {
      try {
          let rs = await axios.get(API_URL + "/" + userId);
          setUser(rs.data);
      } catch (error) {
          console.error(error)
      }
    }
    getUserById();
  }, []);

  useEffect(() => {
    if(user.id){
      setValue('name', user.name);
      setValue('email', user.email);
      setValue('phone', user.phone);
      setValue('address', user.address);
    }
  }, [user, setValue]);

  const updateUser = async (value) => {
    try {
      let rs = await fetch(API_URL + "/" + userId, {
          headers: {
          'Content-type': 'application/json; charset=UTF-8'
          },
          method: 'PUT',
          body: JSON.stringify(user)
      });
      if(rs.status === 200){
        alert("update success!")
      } else {
        console.error(rs);
        alert("error");
      }
    } catch (error) {
        console.error(error);
    }
  }

  const handleChangeAvatar = async (e) => {
    const file = e.target.files[0];
    console.log(file);
    
    const formData = new FormData();
    formData.append("file", file)

    try {
        const rs = await axios.put(API_URL + "/" + userId + "/update-avatar", formData, {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        });
        setUser({...user, avatar: rs.data.url})
    } catch (error) {
        console.error(error)
    }
  }

  const sendNewPassword = async () => {
    try {
      let rs = await axios.get(`${API_URL}/${user.id}/fotgot-password`)
      alert("Mat khau da thay doi vui long check email de thay doi");
      console.log(rs);
    } catch (error) {
        console.error(error);
    }
  }

  return (
    <>
        <div className="container mt-5 mb-5">
          <h2 className="text-center text-uppercase mb-3">Thông tin user</h2>

          <div className="row justify-content-center">
              <div className="col-md-6">
                <form onSubmit={handleSubmit(onSubmit)}>
                  <div className="bg-light p-4">
                      <div className="mb-3">
                          <label className="col-form-label">Fullname</label>
                          <input type="text" id="name" className="form-control" {...register("name")} />
                          <p className='text-danger'>{errors.name?.message}</p>
                      </div>
                      <div className="mb-3">
                          <label className="col-form-label">Email</label>
                          <input type="text" id="email" className="form-control" disabled {...register("email")} />
                          <p className='text-danger'>{errors.email?.message}</p>
                      </div>
                      <div className="mb-3">
                          <label className="col-form-label">Phone</label>
                          <input type="text" id="phone" className="form-control" {...register("phone")} />
                          <p className='text-danger'>{errors.phone?.message}</p>
                      </div>
                      <div className="mb-3">
                          <label className="col-form-label">Address</label>
                          <select className="form-select" id="address" {...register("address")}>
                              {addressList.map((item) => (
                                <option key={item.code} value={item.name}>{item.name}</option>
                              ))}
                          </select>
                          <p className='text-danger'>{errors.address?.message}</p>
                      </div>
                      <div className="mb-3">
                          <label className="form-label">Avatar</label>
                          <div className="avatar-preview mb-3 rounded">
                              <img src={user?.avatar ? `http://localhost:8080${user.avatar}` : "https://via.placeholder.com/200"} alt="avatar" id="avatar-preview" className="rounded" />
                          </div>

                          <label className="btn btn-warning" htmlFor="input">
                              Chọn ảnh
                          </label>
                          <input type="file" id="input" className="d-none" onChange={(e) => handleChangeAvatar(e)} />
                      </div>
                      <div className="mb-3">
                          <label className="col-form-label">Password</label>
                          <div className="">
                              <button type="button" className="btn btn-primary" onClick={() => modal?.show()}>
                                  Đổi mật khẩu
                              </button>
                              <button className="btn btn-warning" id="btn-forgot-password" onClick={sendNewPassword}>
                                  Quên mật khẩu
                              </button>
                          </div>
                      </div>
                  </div>
                  <div className="text-center mt-3">
                      <button className="btn btn-secondary btn-back" onClick={() => navigate("/users")}>Quay lại</button>
                      <button className="btn btn-success" id="btn-save" type="submit">Cập nhật</button>
                  </div>
                </form>
              </div>
          </div>

          <div className="modal fade" ref={modalRef} id="modal-change-password" data-bs-backdrop="static" data-bs-keyboard="false"
              tabIndex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
              <div className="modal-dialog">
                  <div className="modal-content">
                    <form  onSubmit={handleSubmitPass(onSubmitPass)}>
                      <div className="modal-header">
                          <h5 className="modal-title" id="staticBackdropLabel">Đổi mật khẩu</h5>
                          <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                      </div>
                      <div className="modal-body">
                          <div className="mb-3">
                              <label className="col-form-label">Mật khẩu cũ</label>
                              <input type="text" id="old-password" className="form-control" {...reUpdatePass("oldPassword")}/>
                              <p className='text-danger'>{errorsPass.oldPassword?.message}</p>
                          </div>
                          <div className="mb-3">
                              <label className="col-form-label">Mật khẩu mới</label>
                              <input type="text" id="new-password" className="form-control" {...reUpdatePass("newPassword")} />
                              <p className='text-danger'>{errorsPass.newPassword?.message}</p>
                          </div>
                      </div>
                      <div className="modal-footer">
                          <button type="button" className="btn btn-secondary" onClick={() => modal?.hide()}>Đóng</button>
                          <button type="submit" className="btn btn-primary" id="btn-change-password">Xác nhận</button>
                      </div>
                    </form>
                  </div>
              </div>
          </div>
      </div>
    </>
  )
}

export default UserDetail