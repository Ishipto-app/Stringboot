import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'
const END_POINT = "http://localhost:8080/api/v1/admin";

export const blogApi = createApi({
    reducerPath: 'blogApi',
    baseQuery: fetchBaseQuery({ 
        baseUrl: END_POINT, 
        // build header trong request
        prepareHeaders: (headers, { getState }) => {
            const token = getState().auth.token // Lấy trong state
            if (token) {
                headers.set('Authorization', `Bearer ${token}`)
            }
            return headers
        },
    }),
    tagTypes: ["Blog"],
    endpoints: (builder) => ({ 
        getAllBlogs: builder.query({
            query: ({page, pageSize} = {page: 1, pageSize: 5}) => "blogs?page=" + page + "&pageSize=" + pageSize,
            providesTags: ["Course"]
        }),
        getAllBlogsByUserId: builder.query({
            query: ({page, pageSize} = {page: 1, pageSize: 5}) => "blogs/own-blogs?page=" + page + "&pageSize=" + pageSize,
            providesTags: ["Course"]
        }),
        getBlogById: builder.query({
          query: (id) => "blogs/" + id,
        }),
        createBlog: builder.mutation({
            query: (data) => ({
                url: "blogs",
                method: "POST",
                body: data
            })
        }),
        updateBlog: builder.mutation({
            query: ({id, ...data}) => ({
                url: "blogs/" + id,
                method: "PUT",
                body: data
            })
        }),
        deleteBlog: builder.mutation({
            query: (id) => ({
                url: "blogs/" + id,
                method: "DELETE",
            }),
            //invalid tag de goi lai all course
            invalidatesTags:["Blog"]
        }),
    })
})

export const { 
    useGetAllBlogsQuery, useGetAllBlogsByUserIdQuery, useGetBlogByIdQuery, useCreateBlogMutation,
    useUpdateBlogMutation, useDeleteBlogMutation 
} = blogApi