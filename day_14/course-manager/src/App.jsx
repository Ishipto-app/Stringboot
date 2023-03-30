import NotFound from './pages/NotFound'
import { Routes, Route } from "react-router-dom";
import CourseList from './pages/course/CourseList';
import CourseDetail from './pages/course/CourseDetail';
import CourseOnlabList from './pages/course/CourseOnlabList';
import CourseOnlineList from './pages/course/CourseOnlineList';
import Layout from './components/layout/Layout';

function App() {
  return (
    <>
      <Routes>
        <Route element={<Layout/>}>
          <Route path="/khoa-hoc">
            <Route index element={<CourseList />}></Route>
            <Route path="phong-lab" element={<CourseOnlabList />}></Route>
            <Route path="truc-tuyen" element={<CourseOnlineList />}></Route>
            <Route path=":id" element={<CourseDetail />}></Route>
          </Route>

        </Route>
        <Route path="*" element={<NotFound />}></Route>
      </Routes>
      
    </>
  )
}

export default App
