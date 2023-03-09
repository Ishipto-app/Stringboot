import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
const API_URL = "http://localhost:8080/api/v1/users"

function UserCreate() {
  const [addressList, setAddressList] = useState([]);
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [address, setAddress] = useState("");
  const [password, setPassword] = useState("");
  const history = useNavigate();

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
        history("/users/" + data.id)
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
                    <div className="bg-light p-4">
                        <div className="mb-3">
                            <label className="col-form-label">Name</label>
                            <input type="text" id="name" className="form-control" value={name} onChange={e => setName(e.target.value)} />
                        </div>
                        <div className="mb-3">
                            <label className="col-form-label">Email</label>
                            <input type="text" id="email" className="form-control" value={email} onChange={e => setEmail(e.target.value)} />
                        </div>
                        <div className="mb-3">
                            <label className="col-form-label">Phone</label>
                            <input type="text" id="phone" className="form-control" value={phone} onChange={e => setPhone(e.target.value)} />
                        </div>
                        <div className="mb-3">
                            <label className="col-form-label">Address</label>
                            <select className="form-select" id="address" value={address} onChange={e => setAddress(e.target.value)}>
                              <option hidden>Chọn tỉnh thành phố</option>
                              {addressList.map((item) => (
                                <option key={item.code} value={item.name}>{item.name}</option>
                              ))}
                                
                            </select>
                        </div>
                        <div className="mb-3">
                            <label className="col-form-label">Password</label>
                            <input type="text" id="password" className="form-control" value={password} onChange={e => setPassword(e.target.value)} />
                        </div>
                    </div>
                    <div className="text-center mt-3">
                        <button className="btn btn-secondary btn-back">Quay lại</button>
                        <button className="btn btn-success" id="btn-save" onClick={createUser}>Tạo User</button>
                    </div>
                </div>
            </div>
        </div>
    </>
  )
}

export default UserCreate