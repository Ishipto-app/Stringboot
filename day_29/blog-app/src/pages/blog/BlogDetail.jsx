import React from 'react'
import { useGetBlogByIdQuery } from '../../app/service/blogService';
import { Link, useParams } from "react-router-dom";

function BlogDetail() {
  const {blogId, blogSlug} = useParams();
  const { data: blog, isLoading: isLoadingBlog, isError: isLoadingBlogError, error: loadingBlogError } = useGetBlogByIdQuery({id: blogId, slug: blogSlug});
  
  if(isLoadingBlog) {
    return <h2>Loading...</h2>
  }
  if(isLoadingBlogError){
      return <h2>Error: {loadingBlogError}</h2>
  }
  return (
    <main className="main">
        <ul className="terms-tags">
            {blog && blog.categories.map(category => (
                <li key={category.id}>
                    <Link to={"/categories/" + category.name}>
                        {category.name}
                    </Link>
                </li>
            ))}
        </ul>
        <article className="post-single">
            <header className="post-header">
                <h1 className="post-title">{blog?.title}</h1>
                <div className="post-meta"><span>{blog?.pulishedAt}</span>
                </div>
            </header>
            <div className="post-content">
                <p>{blog?.content}</p>
            </div>
        </article>
    </main>
  )
}

export default BlogDetail