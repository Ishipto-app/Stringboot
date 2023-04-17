
import React, {useState, useEffect} from 'react'
import { Helmet } from "react-helmet";
import { Link } from "react-router-dom";
import { useGetBlogsByTitleQuery } from '../../app/service/blogService';

function SearchPage() {
    
    const [searchValue, setSearchValue] = useState('');
    const [term, setTerm] = useState('');
    const handleSearchChange = e => {
        setSearchValue(e.target.value);
    }
    const { data: blogs, isLoading: isLoadingBlog, isError: isLoadingBlogError, error: loadingBlogError } = useGetBlogsByTitleQuery(term && {term});
    
    const getBlog = (value) => {
        if (value !== '') {
            setTerm(value);
        }
    };
    if(isLoadingBlog) {
      return <h2>Loading...</h2>
    }
    if(isLoadingBlogError){
        return <h2>Error: {loadingBlogError}</h2>
    }
  return (
    <>
      <Helmet>
          <title>Tìm kiếm</title>
      </Helmet>
      
      <main className="main">
          <header className="page-header">
              <h1>
                  Search
                  <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width="28"
                      height="28"
                      viewBox="0 0 24 24"
                      fill="none"
                      stroke="currentcolor"
                      strokeWidth="2"
                      strokeLinecap="round"
                      strokeLinejoin="round"
                  >
                      <circle cx="11" cy="11" r="8" />
                      <line x1="21" y1="21" x2="16.65" y2="16.65" />
                  </svg>
              </h1>
              <div className="post-description">Tìm kiếm bài viết</div>
              <div className="post-meta"></div>
          </header>
          <div id="searchbox">
              <input
                  id="searchInput"
                  placeholder="Tìm kiếm bài viết"
                  type="search"
                  value={searchValue} 
                  onChange={handleSearchChange} 
                  onKeyDown={(e) => e.key === "Enter" && getBlog(e.target.value)}
              />
              <ul id="searchResults">
                {blogs && blogs.map(blog => (
                  <li className="post-entry" key={blog.id}>
                      <header className="entry-header">
                          {blog.description}&nbsp;»
                      </header>
                      <Link
                          to={"/blog/" + blog.id + "/" + blog.slug}
                          aria-label={blog.slug}
                      ></Link>
                  </li>
                ))}
              </ul>
          </div>
      </main>
    </>
  )
}

export default SearchPage