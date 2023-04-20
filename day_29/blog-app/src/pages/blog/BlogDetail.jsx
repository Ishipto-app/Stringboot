import React from 'react'
import { useGetBlogByIdQuery } from '../../app/apis/blogApi';
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
                <div className="post-meta"><span>{blog ? new Date(...blog.publishedAt).toLocaleDateString() : null}</span>
                </div>
            </header>
            <div className="post-content">
                <p>{blog?.content}</p>
                <h4>Create by: {blog?.user?.name}</h4>
                <table>
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Comment</th>
                            <th>Create by</th>
                            <th>Create at</th>
                        </tr>
                    </thead>
                    <tbody>
                        {blog?.comments && blog.comments.map((comment, index) => (
                            <tr key={comment.id}>
                                <td>{index + 1}</td>
                                <td>{comment.content}</td>
                                <td>{comment.user.name}</td>
                                <td>{comment.createdAt}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </article>
    </main>
  )
}

export default BlogDetail