import React from 'react'
import { Link } from "react-router-dom";

function Header() {
  return (
    <>
        <div className="header d-flex align-items-center">
            <div className="container-fluid">
                <div className="d-flex justify-content-start align-items-center">
                    <div className="logo">
                        <Link to="/admin/khoa-hoc">
                            <img src="https://techmaster.vn/resources/image/logo-techmaster/white/white_200x74.png" alt="logo" />
                        </Link>
                    </div>
                    <div className="menu">
                        <Link className="text-white ms-5" to="/admin/khoa-hoc">Danh sách khóa học</Link>
                        <Link className="text-white ms-3" to="/admin/khoa-hoc/tao-khoa-hoc">Tạo khóa học</Link>
                    </div>
                </div>
            </div>
        </div>
    </>
  )
}

export default Header