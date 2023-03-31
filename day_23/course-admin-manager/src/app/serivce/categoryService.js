import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'

// Define a service using a base URL and expected endpoints
export const categoryApi = createApi({
  reducerPath: 'categoryApi',
  baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8080/api/v1/admin' }), //cau hinh header
  tagTypes: ["Category"],
  //dinh nghia api
  endpoints: (builder) => ({ 
    getAllCategory: builder.query({
      query: () => "category", // query chap nhan 1 tham so
      providesTags: ["Category"]
    }),
  })
})

// export hook: dat ten "use" + ten end point + Query/Mutation
export const { 
    useGetAllCategoryQuery, 
} = categoryApi