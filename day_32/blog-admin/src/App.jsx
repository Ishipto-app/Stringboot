import { useState } from 'react'
import { Route, Routes, useNavigate } from 'react-router-dom'
import Layout from './components/Layout'
import PrivateRoutes from './components/PrivateRoutes'
import BlogList from './pages/blog/BlogList'
import OwnBlog from './pages/blog/OwnBlog'
import BlogDetails from './pages/blog/BlogDetails'
import BlogCreate from './pages/blog/BlogCreate'
import LoginPage from './pages/login/LoginPage'
import AuthorizeRoutes from './components/AuthorizeRoutes'
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import CategoryList from './pages/category/CategoryList'
import CategoryDetail from './pages/category/CategoryDetail'
import CategoryCreate from './pages/category/CategoryCreate'
import UserList from './pages/user/UserList'
import UserDetail from './pages/user/UserDetail'
import UserCreate from './pages/user/UserCreate'

function App() {

  return (
    <>
      <Routes>
        {/* bat buoc dang nhap moi vao dc*/}
        <Route element={<PrivateRoutes />}>
          <Route path="/admin" element={<Layout />}>
            {/*blog*/}
            <Route path="blogs">
              <Route element={<AuthorizeRoutes requireRoles={["ADMIN"]} />} >
                <Route index element={<BlogList />} />
              </Route>
              <Route element={<AuthorizeRoutes requireRoles={["ADMIN", "AUTHOR"]}/>} >
                <Route path=":page/:pageSize" element={<BlogList />} />
                <Route path="own-blog" element={<OwnBlog />}/>
                <Route path="own-blog/:page/:pageSize" element={<OwnBlog />} />
                <Route path=":blogId" element={<BlogDetails />} />
                <Route path="create" element={<BlogCreate />} />
              </Route>
            </Route>
            <Route path="category" >
              <Route element={<AuthorizeRoutes requireRoles={["ADMIN"]} />} >
                <Route index element={<CategoryList />} />
                <Route path=":categoryId" element={<CategoryDetail />} />
                <Route path="create" element={<CategoryCreate />} />
              </Route>
            </Route>
            <Route path="user" >
              <Route element={<AuthorizeRoutes requireRoles={["ADMIN"]} />} >
                <Route index element={<UserList />} />
                <Route path=":userId" element={<UserDetail />} />
                <Route path="create" element={<UserCreate />} />
              </Route>
            </Route>
          </Route>
        </Route>
        <Route path='/admin/login' element={<LoginPage />} />
      </Routes>
      <ToastContainer
        position="top-center"
        autoClose={2000}
        hideProgressBar
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="colored"
        />
    </>
  )
}

export default App
