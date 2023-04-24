import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";

const END_POINT = "http://localhost:8080/api/v1/admin";

export const blogApi = createApi({
    reducerPath: "blogApi",
    baseQuery: fetchBaseQuery({
        baseUrl: END_POINT,
        prepareHeaders: (headers, { getState }) => {
            const token = getState().auth.token // Lấy trong state

            // Nếu tồn tại token => set
            if (token) {
                headers.set('Authorization', `Bearer ${token}`)
            }

            return headers
        },
    }),
    endpoints: (builder) => ({
        getBlogs: builder.query({
            query: ({ page, pageSize }) => `blogs?page=${page}&pageSize=${pageSize}`,
        }),
    }),
});

export const {
    useGetBlogsQuery
} = blogApi;
