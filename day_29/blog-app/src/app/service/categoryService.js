import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'

export const categoryApi = createApi({
    reducerPath: 'categoryApi',
    baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8080/api/v1/public' }),
    tagTypes: ["Category"],
    endpoints: (builder) => ({ 
        getAllCategories: builder.query({
            query: () => "categories",
        }),
        getCategoriesTop5: builder.query({
            query: () => "category/top5",
        }),
        getCategoryByName: builder.query({
            query: (name) => "category/" + name,
        }),
    })
})

export const { 
    useGetAllCategoriesQuery, useGetCategoriesTop5Query, useGetCategoryByNameQuery
} = categoryApi