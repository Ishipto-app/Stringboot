import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'

export const userApi = createApi({
    reducerPath: 'userApi',
    baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8080/api/v1/admin' }),
    tagTypes: ["User"],
    endpoints: (builder) => ({ 
        getAllUsers: builder.query({
            query: () => "users",
        }),
    })
})

export const { 
    useGetAllUsersQuery
} = userApi