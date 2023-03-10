import './App.css'
import UseList from './pages/user/UserList'
import UserCreate from './pages/user/UserCreate'
import UserDetail from './pages/user/UserDetail'
import NotFound from './pages/not-found/NotFound'
import { Routes, Route } from "react-router-dom";

function App() {
  // /users
  // /users/create
  // /users/{id}
  return (
    <>
      <Routes>
        <Route path="/users">
          <Route index element={<UseList />}></Route>
          <Route path="create" element={<UserCreate />}></Route>
          <Route path=":userId" element={<UserDetail />}></Route>

        </Route>
        <Route path="*" element={<NotFound />}></Route>
      </Routes>
      
    </>
  )
}

export default App
