import React from 'react'
import { Outlet } from 'react-router-dom'
import CourseHeader from '../header/CourseHeader'

function Layout() {
  return (
    <>
        <CourseHeader />

        <Outlet />
    </>
  )
}

export default Layout