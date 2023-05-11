import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'
const END_POINT = "http://localhost:8080/api/v1/admin";

export const userApi = createApi({
    reducerPath: 'userApi',
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
    tagTypes: ["User"],
    endpoints: (builder) => ({ 
        getAllUsers: builder.query({
            query: () => "users",
            //query: ({page, pageSize} = {page: 1, pageSize: 10}) => "users?page=" + page + "&pageSize=" + pageSize,
            providesTags: ["User"]
        }),
        getAllUserRoles: builder.query({
            query: () => "users/roles",
        }),
        getUserById: builder.query({
            query: (id) => "users/" + id,
        }),
        createUser: builder.mutation({
            query: (data) => ({
                url: "users",
                method: "POST",
                body: data
            })
        }),
        updateUser: builder.mutation({
            query: ({id, ...data}) => ({
                url: "users/" + id,
                method: "PUT",
                body: data
            })
        }),
        updateUserAvatar: builder.mutation({
            query: ([id, data]) => ({
                url: "users/" + id + "/update-avatar",
                method: "PUT",
                body: data
            }),
            //invalid tag de goi lai user
            invalidatesTags:["User"]
        }),
        updateUserPassword: builder.mutation({
            query: ({id, ...data}) => ({
                url: "users/" + id + "/update-password",
                method: "PUT",
                body: data
            })
        }),
        deleteUser: builder.mutation({
            query: (id) => ({
                url: "users/" + id,
                method: "DELETE",
            }),
            //invalid tag de goi lai user
            invalidatesTags:["User"]
        }),
    })
})
export const { 
    useGetAllUsersQuery, useGetAllUserRolesQuery, useLazyGetAllUsersQuery, useGetUserByIdQuery, useLazyGetUserByIdQuery, useCreateUserMutation,
    useUpdateUserMutation, useDeleteUserMutation, useUpdateUserAvatarMutation, useUpdateUserPasswordMutation
} = userApi