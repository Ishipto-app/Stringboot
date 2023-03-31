import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'

// Define a service using a base URL and expected endpoints
export const courseApi = createApi({
  reducerPath: 'courseApi',
  baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8080/api/v1/admin' }), //cau hinh header
  tagTypes: ["Course"],
  //dinh nghia api
  endpoints: (builder) => ({ 
    getAllCourse: builder.query({
      query: () => "courses", // query chap nhan 1 tham so
      providesTags: ["Course"]
    }),
    getCourseById: builder.query({
      query: (id) => "courses/" + id,
    }),
    createCourse: builder.mutation({
        query: (data) => ({
            url: "courses",
            method: "POST",
            body: data
        })
    }),
    // data = {id: 1, title: "khoa hoc", price: 10} =>tach ra {id, ...data}
    updateCourse: builder.mutation({
        query: ({id, ...data}) => ({
            url: "courses/" + id,
            method: "PUT",
            body: data
        })
    }),
    deleteCourse: builder.mutation({
        query: (id) => ({
            url: "courses/" + id,
            method: "DELETE",
        }),
        //invalid tag de goi lai all course
        invalidatesTags:["Course"]
    }),
  }),
})

// export hook: dat ten "use" + ten end point + Query/Mutation
export const { 
    useGetAllCourseQuery, 
    useGetCourseByIdQuery, 
    useCreateCourseMutation, 
    useUpdateCourseMutation, 
    useDeleteCourseMutation 
} = courseApi