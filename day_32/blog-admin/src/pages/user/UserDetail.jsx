import React, { useEffect, useRef } from 'react'
import { Helmet } from "react-helmet";
import { Link, useNavigate, useParams } from "react-router-dom";
import { useGetUserByIdQuery, useLazyGetUserByIdQuery, useUpdateUserAvatarMutation } from '../../app/apis/userApi';
import { useUpdateUser, useUpdateUserPassword } from '../hooks/useUpdate';
import { Controller } from "react-hook-form";
import Select from 'react-select';
import {
    getRolesOptions
} from "../options/options";
import useFetchQuery from '../hooks/useFetchQuery';
import * as bootstrap from "bootstrap/dist/js/bootstrap.bundle";
import { useDispatch } from 'react-redux';


function UserDetail() {
    const modalRef = useRef(null);
    const modalElement = modalRef.current;
    const dispatch = useDispatch()

    let modal
    if (modalElement) {
      modal = new bootstrap.Modal(modalElement);
    }
    const navigate = useNavigate();
    const {userId} = useParams();
    const [getUser, {data: userData, isLoading: isLoadingUser,  isError: isLoadingUserError, error: loadingUserError}]= useLazyGetUserByIdQuery(userId);
    
    const { control, register, handleSubmit, errors, setValue, onUpdateUser } = useUpdateUser(userId);
    const [ updateAvatarData, { isLoading: isAvatarUpdating, isError: isUpdateAvatarError, error: updateAvatarError }] = useUpdateUserAvatarMutation();
    const { control: controlPass, register: registerPass, handleSubmit: handleSubmitPass, isUpdating: isUpdatingPass, errors: errorsPass, setValue: setValuePass, onUpdatePassword } = useUpdateUserPassword(userId);
    const { roles, isLoading: loadItem } = useFetchQuery();
    const rolesOptions = getRolesOptions(roles);

    if(isLoadingUserError) console.error(loadingUserError)
    if(isUpdateAvatarError) console.error(updateAvatarError)
    
    useEffect(() => {
        if(userData){
            setValue('name', userData.name);
            setValue('email', userData.email);
            setValue('avatar', userData.avatar);
            setValue('roles', userData.roles);
        } else {
            getUser(userId)
        }
    }, [userData, setValue]);
    
    const handleChangeAvatar = async (e) => {
        const file = e.target.files[0];
        
        const formData = new FormData();
        formData.append("file", file)
        
        updateAvatarData([userId, formData])
            .unwrap()
            .then((res) => {
                getUser(userId);
                alert("update success")
            })
            .catch(err => {
                console.error(err)
                alert('update error')
            });
    }
    const handleModalPassword = (type) => {
        console.log(type)
        if(type == 'show' && modal){
            modal.show()
        }
        if(type == 'hide' && modal){
            modal.hide()
        }
    } 
    
    const sendNewPassword = async () => {
        //gui api thay doi pass
        // try {
        //   let rs = await axios.get(`${API_URL}/${user.id}/fotgot-password`)
        //   alert("Mat khau da thay doi vui long check email de thay doi");
        //   console.log(rs);
        // } catch (error) {
        //     console.error(error);
        // }
        }
    if(isLoadingUser || isAvatarUpdating || isUpdatingPass) {
        return <h2>Loading...</h2>
    }
    if(isLoadingUserError){
        return <h2>Error: {loadingUserError}</h2>
    }
  return (
    <>
    {userData && (
        <section className="content">
            <form  className="container-fluid" onSubmit={handleSubmit(onUpdateUser)}>
                <div className="row py-2">
                    <div className="col-6">
                        <button type="button" className="btn btn-default" onClick={() => navigate(-1)}>
                            <i className="fas fa-chevron-left"></i> Quay lại
                        </button>
                        <button type="submit" className="btn btn-info px-4">
                            Lưu
                        </button>
                        <button type="button" className="btn btn-primary px-4">
                            Preview
                        </button>
                    </div>
                </div>

                <div className="row">
                    <div className="col-12">
                        <div className="card">
                            <div className="card-body">
                                <h1 style={{marginBottom: "1rem"}}>User</h1>
                                <div className="row">
                                    <div className="col-md-6">
                                        <div className="form-group">
                                            <label>Full name</label>
                                            <input type="text" className="form-control" id="name" {...register("name")} />
                                            <p className='text-danger'>{errors.name?.message}</p>
                                        </div>
                                    </div>
                                    <div className="col-md-6">
                                        <div className="form-group">
                                            <label>Email</label>
                                            <input type="text" className="form-control" id="email" {...register("email")} />
                                            <p className='text-danger'>{errors.email?.message}</p>
                                        </div>
                                    </div>
                                    <div className="col-md-3">
                                        <div className="form-group">
                                            <label>Roles</label>
                                            <Controller
                                                name="roles"
                                                control={control}
                                                render={({
                                                    field: { onChange, value, ref },
                                                }) => (
                                                    <Select
                                                        placeholder="-- Chọn roles --"
                                                        inputRef={ref}
                                                        value={rolesOptions.find(
                                                            (c) => c.value === value
                                                        )}
                                                        defaultValue={rolesOptions.filter(item => (userData.roles.map(role => role.id)).includes(item.value))}
                                                        onChange={(val) =>
                                                            onChange(
                                                                val.map((c) => c.value)
                                                            )
                                                        }
                                                        options={rolesOptions}
                                                        isMulti
                                                    />
                                                )}
                                            />
                                        </div>
                                    </div>
                                    <div className="col-md-3">
                                        <div className="form-group">
                                            <label>Password</label>
                                            <div className="">
                                                <button type="button" className="btn btn-primary" onClick={() => handleModalPassword("show")}>
                                                    Đổi mật khẩu
                                                </button>
                                                <button type="button" className="btn btn-warning" id="btn-forgot-password" onClick={sendNewPassword}>
                                                    Quên mật khẩu
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-md-6">
                                        <div className="form-group">
                                            <label>Avatar</label>
                                            <div className="avatar-preview mb-3 rounded">
                                                <img src={userData?.avatar ? `http://localhost:8080${userData.avatar}` : "https://via.placeholder.com/200"} alt="avatar" id="avatar-preview" className="rounded" />
                                            </div>

                                            <label className="btn btn-warning" htmlFor="input">
                                                Chọn ảnh
                                            </label>
                                            <input type="file" id="input" className="d-none" onChange={(e) => handleChangeAvatar(e)} />
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div className="modal fade" ref={modalRef} id="modal-change-password" data-bs-backdrop="static" data-bs-keyboard="false"
              tabIndex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
              <div className="modal-dialog">
                  <div className="modal-content">
                    <form  onSubmit={handleSubmitPass(onUpdatePassword)}>
                      <div className="modal-header">
                          <h5 className="modal-title" id="staticBackdropLabel">Đổi mật khẩu</h5>
                          <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close" onClick={() => handleModalPassword("hide")}></button>
                      </div>
                      <div className="modal-body">
                          <div className="mb-3">
                              <label className="col-form-label">Mật khẩu cũ</label>
                              <input type="text" id="old-password" className="form-control" {...registerPass("oldPassword")}/>
                              <p className='text-danger'>{errorsPass.oldPassword?.message}</p>
                          </div>
                          <div className="mb-3">
                              <label className="col-form-label">Mật khẩu mới</label>
                              <input type="text" id="new-password" className="form-control" {...registerPass("newPassword")} />
                              <p className='text-danger'>{errorsPass.newPassword?.message}</p>
                          </div>
                      </div>
                      <div className="modal-footer">
                          <button type="button" className="btn btn-secondary" onClick={() => handleModalPassword("hide")}>Đóng</button>
                          <button type="submit" className="btn btn-primary" id="btn-change-password">Xác nhận</button>
                      </div>
                    </form>
                  </div>
              </div>
            </div>
        </section>
    )}
    </>
  )
}

export default UserDetail