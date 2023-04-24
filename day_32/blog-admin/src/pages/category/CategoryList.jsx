import React from 'react'
import { Helmet } from "react-helmet";
import { Link } from "react-router-dom";
import { useGetAllCategoriesQuery } from '../../app/apis/categoryApi';

function CategoryList() {
    const { data: categories, isLoading: isLoadingCategory, isError: isLoadingCategoryError, error: loadingCategoryError } = useGetAllCategoriesQuery();
    
    if(isLoadingCategory) {
        return <h2>Loading...</h2>
    }
    if(isLoadingCategoryError){
        return <h2>Error: {loadingCategoryError}</h2>
    }
  return (
    <>
        <Helmet>
            <title>Danh mục</title>
        </Helmet>
        <main className="main">
            <header className="page-header">
                <h1>Category</h1>
            </header>
            <ul className="terms-tags">
                {categories && categories.map(category => (
                    <li key={category.id}>
                        <Link to={"/categories/" + category.name}>
                            {category.name}
                            <sup>
                                <strong>
                                    <sup>{category.used}</sup>
                                </strong>
                            </sup>
                        </Link>
                    </li>
                ))}
            </ul>
        </main>
    </>
  )
}

export default CategoryList