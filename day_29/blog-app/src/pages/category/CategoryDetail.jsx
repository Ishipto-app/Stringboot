import React from 'react'
import { Helmet } from "react-helmet";
import { Link, useParams } from "react-router-dom";
import { useGetCategoryByNameQuery } from '../../app/apis/categoryApi';

function CategoryDetail() {
    const {categoryName} = useParams();
    const { data: blogs, isLoading: isLoadingBlog, isError: isLoadingBlogError, error: loadingBlogError } = useGetCategoryByNameQuery(categoryName);
    
    if(isLoadingBlog) {
        return <h2>Loading...</h2>
    }
    if(isLoadingBlogError){
        return <h2>Error: {loadingBlogError}</h2>
    }
  return (
    <>
        <Helmet>
            <title>{`Danh mục : ${categoryName}`}</title>
        </Helmet>
        <main className="main">
            <header className="entry-header">
                <h1 style={{marginBottom: "1rem"}}>Category : {categoryName}</h1>
            </header>

            {blogs && blogs.map(blog => (
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
                        <span>{blog ? new Date(...blog.publishedAt).toLocaleDateString() : null}</span>
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

export default CategoryDetail