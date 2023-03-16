import NotFound from './pages/NotFound'
import { Routes, Route } from "react-router-dom";
import CourseList from './pages/CourseList';
import CourseDetail from './pages/CourseDetail';
import CourseOnlabList from './pages/CourseOnlabList';
import CourseOnlineList from './pages/CourseOnlineList';

function App() {
  return (
    <>
      <Routes>
        <Route path="/khoa-hoc">
          <Route index element={<CourseList />}></Route>
          <Route path=":id" element={<CourseDetail />}></Route>
          <Route path="phong-lab" element={<CourseOnlabList />}></Route>
          <Route path="truc-tuyen" element={<CourseOnlineList />}></Route>

        </Route>
        <Route path="*" element={<NotFound />}></Route>
      </Routes>
      
    </>
  )
}

export default App
