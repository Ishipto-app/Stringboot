import React, {useState, useEffect} from 'react';
import { useParams, useNavigate } from "react-router-dom";
import axios from 'axios';
const API_URL = "http://localhost:8080/api/v1/users";

// mot so thu vien validate form
// React-hook-form
// Formik
// Redux-form

function UserDetail() {
  let userData = null;
  //lay ra user id tren url
  
  const {userId} = useParams();
  const [addressList, setAddressList] = useState([]);
  const [user, setUser] = useState(null);
  const history = useNavigate();

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
                  <div className="bg-light p-4">
                      <div className="mb-3">
                          <label className="col-form-label">Fullname</label>
                          <input type="text" id="name" className="form-control" value={user?.name} onChange={e => setUser({...user, name: e.target.value})} />
                      </div>
                      <div className="mb-3">
                          <label className="col-form-label">Email</label>
                          <input type="text" id="email" className="form-control" disabled value={user?.email} />
                      </div>
                      <div className="mb-3">
                          <label className="col-form-label">Phone</label>
                          <input type="text" id="phone" className="form-control" value={user?.phone} onChange={e => setUser({...user, phone: e.target.value})} />
                      </div>
                      <div className="mb-3">
                          <label className="col-form-label">Address</label>
                          <select className="form-select" id="address" value={user?.address} onChange={e => setUser({...user, address: e.target.value})}>
                              {addressList.map((item) => (
                                <option key={item.code} value={item.name}>{item.name}</option>
                              ))}
                          </select>
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
                              <button type="button" className="btn btn-primary" data-bs-toggle="modal"
                                  data-bs-target="#modal-change-password">
                                  Đổi mật khẩu
                              </button>
                              <button className="btn btn-warning" id="btn-forgot-password" onClick={sendNewPassword}>
                                  Quên mật khẩu
                              </button>
                          </div>
                      </div>
                  </div>
                  <div className="text-center mt-3">
                      <button className="btn btn-secondary btn-back" onClick={() => history("/users")}>Quay lại</button>
                      <button className="btn btn-success" id="btn-save" onClick={() => updateUser(1)}>Cập nhật</button>
                  </div>
              </div>
          </div>

          <div className="modal fade" id="modal-change-password" data-bs-backdrop="static" data-bs-keyboard="false"
              tabIndex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
              <div className="modal-dialog">
                  <div className="modal-content">
                      <div className="modal-header">
                          <h5 className="modal-title" id="staticBackdropLabel">Đổi mật khẩu</h5>
                          <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                      </div>
                      <div className="modal-body">
                          <div className="mb-3">
                              <label className="col-form-label">Mật khẩu cũ</label>
                              <input type="text" id="old-password" className="form-control" />
                          </div>
                          <div className="mb-3">
                              <label className="col-form-label">Mật khẩu mới</label>
                              <input type="text" id="new-password" className="form-control" />
                          </div>
                      </div>
                      <div className="modal-footer">
                          <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                          <button type="button" className="btn btn-primary" id="btn-change-password">Xác nhận</button>
                      </div>
                  </div>
              </div>
          </div>
      </div>
    </>
  )
}

export default UserDetail