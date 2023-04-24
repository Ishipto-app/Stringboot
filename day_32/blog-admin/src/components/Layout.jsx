import React from 'react'
import {Outlet} from 'react-router-dom'
import Header from './Header'
import Sidebar from './Sidebar'

function Layout() {
  return (
    <>
      <Sidebar />
      <div className="wrapper-container">
        <Header />
        <Outlet />
      </div>
    </>
  )
}

export default Layout