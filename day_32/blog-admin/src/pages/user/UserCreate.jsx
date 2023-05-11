import React from 'react'
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom'
import { useCreateUser } from '../hooks/useCreate';
import useFetchQuery from '../hooks/useFetchQuery';
import { getRolesOptions } from '../options/options';
import { Controller } from "react-hook-form";
import Select from 'react-select';

function UserCreate() {
    const navigate = useNavigate();
   
    const { control, register, handleSubmit, errors, onCreateUser } = useCreateUser();

    const { roles, isLoading: loadItem } = useFetchQuery();
    const rolesOptions = getRolesOptions(roles);
   
  return (
    <>
        <section className="content">
            <form  className="container-fluid" onSubmit={handleSubmit(onCreateUser)}>
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
                                    <div className="col-md-6">
                                        <div className="form-group">
                                            <label>Password</label>
                                            <input type="password" className="form-control" id="password" {...register("password")} />
                                            <p className='text-danger'>{errors.password?.message}</p>
                                        </div>
                                    </div>
                                    <div className="col-md-3">
                                        <div className="form-group">
                                            <label>Roles</label>
                                            <Controller
                                                name="roles"
                                                control={control}
                                                value={[]}
                                                render={({
                                                    field: { onChange, value, ref },
                                                }) => (
                                                    <Select
                                                        placeholder="-- Chọn roles --"
                                                        inputRef={ref}
                                                        value={rolesOptions.find(
                                                            (c) => c.value === value
                                                        )}
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
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </section>
    </>
  )
}

export default UserCreate