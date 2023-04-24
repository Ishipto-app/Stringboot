import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'
const END_POINT = "http://localhost:8080/api/v1/admin";

export const categoryApi = createApi({
    reducerPath: 'categoryApi',
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
    tagTypes: ["Category"],
    endpoints: (builder) => ({ 
        getAllCategories: builder.query({
            query: () => "categories/all",
        }),
    })
})

export const { 
    useGetAllCategoriesQuery
} = categoryApi