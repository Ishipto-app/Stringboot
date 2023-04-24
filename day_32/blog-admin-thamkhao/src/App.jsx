import { Route, Routes } from "react-router-dom";
import Layout from "./components/Layout";
import PrivateRoutes from "./components/PrivateRoutes";
import BlogList from "./pages/blog/BlogList";
import OwnBlog from "./pages/blog/OwnBlog";
import BlogDetail from "./pages/blog/BlogDetail";
import BlogCreate from "./pages/blog/BlogCreate";
import LoginPage from "./pages/login/LoginPage";

function App() {
    return (
        <>
            <Routes>
                <Route path="/admin" element={<Layout />}>
                    <Route element={<PrivateRoutes />}>
                        {/* Blog */}
                        <Route path="blogs">
                            <Route index element={<BlogList />}/>
                            <Route path="own-blogs" element={<OwnBlog />}/>
                            <Route path=":blogId" element={<BlogDetail />}/>
                            <Route path="create" element={<BlogCreate />}/>
                        </Route>
                    </Route>
                </Route>

                <Route path="/admin/login" element={<LoginPage />}/>
            </Routes>
        </>
    );
}

export default App;
