import { useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'
import Sidebar from './component/Sidebar'
import Courses from './component/Courses'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div className="course-container mt-5 mb-5">
        <div className="container">
            <div className="row">
                <div className="col-md-3">
                    <Sidebar></Sidebar>
                </div>

                <div className="col-md-9">
                    <Courses></Courses>
                </div>
            </div>
        </div>
    </div>
  )
}

export default App
