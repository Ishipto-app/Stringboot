import { configureStore } from '@reduxjs/toolkit'
import { categoryApi } from './serivce/categoryService'
import { courseApi } from './serivce/courseService'
import { userApi } from './serivce/userService'

export const store = configureStore({
  reducer: {
    [courseApi.reducerPath]: courseApi.reducer ,
    [categoryApi.reducerPath]: categoryApi.reducer,
    [userApi.reducerPath]: userApi.reducer
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware().concat(
        courseApi.middleware,
        categoryApi.middleware,
        userApi.middleware
    ),
})
