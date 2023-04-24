import Sidebar from './Sidebar'
import Header from './Header'
import { Outlet } from 'react-router-dom'

function Layout() {
  return (
    <div>
        <Sidebar />
        <div className="wrapper-container">
            <Header />
            
            <Outlet />
        </div>
    </div>
  )
}

export default Layout