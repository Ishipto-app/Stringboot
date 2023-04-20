import React from 'react'
import { Helmet } from "react-helmet";
import { Link, useNavigate, useParams } from "react-router-dom";
import { useGetAllBlogsQuery } from '../../app/apis/blogApi';
import { useGetAllCategoriesQuery } from '../../app/apis/categoryApi';
import NotFound from '../not-found/NotFound';

function BlogList() {
    const {page} = useParams();
    const navigate = useNavigate;
    if (!Number(page)) { // Nếu page không phải là số => NotFoundPage
        return <NotFound />;
    }
    const { data: pageInfo, isLoading: isLoadingBlog } = useGetAllBlogsQuery({
        page: page,
        pageSize: 5,
    });
    const { data: categories, isLoading: isLoadingCategory } =
        useGetAllCategoriesQuery();

    if(isLoadingBlog || isLoadingCategory) {
        return <h2>Loading...</h2>
    }
  return (
    <>
        <Helmet>
            <title>Trang chủ</title>
        </Helmet>
        <main className="main">
            
            <header className="entry-header">
                <h1>
                    Blog app 
                </h1>
            </header>
            <ul className="terms-tags top-tags">
                {categories && categories.map(category => (
                    <li key={category.id}>
                        <Link to={"/categories/" + category.name}>
                            {category.name}
                        </Link>
                    </li>
                ))}
            </ul>
            {pageInfo && pageInfo.content.map(blog => (
                <article className="post-entry" key={blog.id}>
                    <header className="entry-header">
                        <h2>
                            {blog.title}
                        </h2>
                    </header>
                    <div className="entry-content">
                        <p>
                            {blog.content}
                        </p>
                    </div>
                    <footer className="entry-footer">
                        <span>{new Date(...blog.publishedAt).toLocaleDateString()}</span>
                    </footer>
                    <Link
                            className="entry-link"
                            aria-label={blog.slug}
                            to={"/blog/" + blog.id + "/" + blog.slug}
                        ></Link>
                </article>
            ))}
            {pageInfo && (<footer className="page-footer">
                <nav className="pagination">
                    {!pageInfo.first && <Link className="prev" to={page == 2 ? "/" : `/page/${Number(page) - 1}`}>« Trang trước</Link>}
                    {!pageInfo.last && <Link className="next" to={`/page/${Number(page) + 1}`}>Trang tiếp theo »</Link>}
                </nav>
            </footer>
            )}
        </main>
    </>
  )
}

export default BlogList