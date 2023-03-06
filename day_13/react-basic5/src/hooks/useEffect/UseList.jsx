import React, { useEffect, useState } from 'react'

function UseList() {
    const [users, setUsers] = useState([]);
    const [filterUsers, setFilterUsers] = useState([]);
    const [searchValue, setSearchValue] = useState("");

    useEffect(() => {
      const getAllUsers = async () => {
        try {
            let rs = await fetch(`http://localhost:8080/api/v1/users`);
            let data = await rs.json();
            setUsers(data);
            setFilterUsers([...data]);
        } catch (error) {
            console.error(error)
        }
      }
      getAllUsers();
    }, []);

    const deleteUser = async id => {
      try {
        let rs = await fetch(`http://localhost:8080/api/v1/users/${id}`, {
            method: 'DELETE'
        });
        let newUsers = users.filter(user => user.id !== id);
        setUsers(newUsers)
      } catch (error) {
          console.error(error)
      }

    }

    const filterUser = () => {
      let newUsers = [];
      if(searchValue.trim() == ""){
        newUsers = users.slice()
      } else {
        newUsers = users.filter(user => user.name.toLowerCase().includes(searchValue.toLowerCase()));
      }
      setFilterUsers(newUsers)
    }
  return (
    <>
        <div className="container mt-5 mb-5">
            <h2 className="text-center text-uppercase">Danh sách user</h2>

            <div className="row justify-content-center">
                <div className="col-md-10">

                    <div className="d-flex justify-content-between align-items-center mt-5 mb-4">
                        <a href="./create.html" className="btn btn-warning">Tạo user</a>
                        <input type="text" id="search" className="form-control w-50" placeholder="Tìm kiếm user" 
                          onKeyDown={e => e.key === "Enter" ? filterUser() : null} 
                          value={searchValue} 
                          onChange={e => setSearchValue(e.target.value)}
                        />
                    </div>

                    <div className="bg-light p-4">
                        <table className="table table-hover">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Phone</th>
                                    <th>Address</th>
                                    <th></th>
                                </tr>
                            </thead>

                            <tbody>
                              {filterUsers.map((user, index) => (
                                <tr key={user.id}>
                                  <td>{index + 1}</td>
                                  <td>{user.name}</td>
                                  <td>{user.email}</td>
                                  <td>{user.phone}</td>
                                  <td>{user.address}</td>
                                  <td>
                                    <a href=""className="btn btn-success">Xem chi tiết</a>
                                    <button className="btn btn-danger" onClick={() => deleteUser(user.id)}>Xóa</button>
                                  </td>
                                </tr>
                              ))}
                            </tbody>
                        </table>

                        <p className="message d-none"></p>
                    </div>
                </div>
            </div>
        </div>
    </>
  )
}

export default UseList