import React from 'react'
import { Helmet } from "react-helmet";
import { Link } from "react-router-dom";
import { useGetAllBlogsQuery } from '../../app/apis/blogApi';
import { useGetAllCategoriesQuery } from '../../app/apis/categoryApi';

function HomePage() {
    const { data: data, isLoading: isLoadingBlog, isError: isLoadingBlogError, error: loadingBlogError } = useGetAllBlogsQuery();
    const { data: categories, isLoading: isLoadingCategory, isError: isLoadingCategoryError, error: loadingCategoryError } = useGetAllCategoriesQuery();
    
    if(isLoadingBlog || isLoadingCategory) {
        return <h2>Loading...</h2>
    }
    if(isLoadingBlogError || isLoadingCategoryError){
        return <h2>Error: {loadingBlogError}</h2>
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
            {data && data.content.map(blog => (
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
                        <span>{blog.publishedAt ? new Date(...blog.publishedAt).toLocaleDateString() : null}</span>
                    </footer>
                    <Link
                            className="entry-link"
                            aria-label={blog.slug}
                            to={"/blog/" + blog.id + "/" + blog.slug}
                        ></Link>
                </article>
            ))}
        </main>
    </>
  )
}

export default HomePage