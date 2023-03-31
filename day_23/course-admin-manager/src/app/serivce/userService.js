import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'

// Define a service using a base URL and expected endpoints
export const userApi = createApi({
  reducerPath: 'userApi',
  baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8080/api/v1/admin' }), //cau hinh header
  tagTypes: ["User"],
  //dinh nghia api
  endpoints: (builder) => ({ 
    getAllUser: builder.query({
      query: () => "user", // query chap nhan 1 tham so
      providesTags: ["User"]
    }),
  })
})

// export hook: dat ten "use" + ten end point + Query/Mutation
export const { 
    useGetAllUserQuery, 
} = userApi