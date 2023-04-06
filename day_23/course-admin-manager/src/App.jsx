import { Route, Routes } from "react-router-dom";
import Layout from "./components/layout/Layout";
import CourseCreate from "./pages/course/CourseCreate";
import CourseUpdate from "./pages/course/CourseUpdate";
import CourseList from "./pages/course/CourseList";
import NotFound from "./pages/NotFound";

function App() {
    return (
        <>
            <Routes>
                <Route path="/admin" element={<Layout />}>
                    <Route path="khoa-hoc">
                        <Route index element={<CourseList />} />
                        <Route path="tao-khoa-hoc" element={<CourseCreate />} />
                        <Route path=":id" element={<CourseUpdate />} />
                    </Route>
                </Route>
        <       Route path="*" element={<NotFound />}></Route>
            </Routes>
        </>
    );
}

export default App;