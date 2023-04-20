import { useState } from 'react'
import './App.css'
import {Routes, Route} from 'react-router-dom'
import HomePage from './pages/home/HomePage'
import CategoryList from './pages/category/CategoryList'
import CategoryDetail from './pages/category/CategoryDetail'
import BlogDetail from './pages/blog/BlogDetail'
import SearchPage from './pages/search/SearchPage'
import NotFound from './pages/not-found/NotFound'
import Layout from './components/layout/Layout'
import BlogList from './pages/blog/BlogList'
import LoginPage from './pages/login/LoginPage'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <Routes>
        <Route path='/' element={<Layout />}>
          <Route index element={<HomePage />}/>
          <Route path='page/:page' element={<BlogList />} />
          <Route path='search' element={<SearchPage />} />
          <Route path='categories'>
            <Route index element={<CategoryList />} />
            <Route path=':categoryName' element={<CategoryDetail />} />
          </Route>

          <Route path='blog/:blogId/:blogSlug' element={<BlogDetail />} />
          <Route path='/login' element={<LoginPage />} />
          <Route path='*' element={<NotFound />} />

        </Route>
      </Routes>
    
    </>
  )
}

export default App
