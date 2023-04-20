import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'

export const blogApi = createApi({
    reducerPath: 'blogApi',
    baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8080/api/v1/public' }),
    tagTypes: ["Blog"],
    endpoints: (builder) => ({ 
        getAllBlogs: builder.query({
            query: ({page, pageSize} = {page: 1, pageSize: 5}) => "blogs?page=" + page + "&pageSize=" + pageSize,
        }),
        getBlogsByTitle: builder.query({
            query: ({term}) => "search?term=" + term,
        }),
        getBlogById: builder.query({
            query: ({id, slug}) => "blogs/" + id + "/" + slug,
        })
    })
})

export const { 
    useGetAllBlogsQuery, useGetBlogsByTitleQuery, useGetBlogByIdQuery
} = blogApi