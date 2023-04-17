import { configureStore } from '@reduxjs/toolkit'
import { categoryApi } from './service/categoryService'
import { blogApi } from './service/blogService'

export const store = configureStore({
  reducer: {
    [categoryApi.reducerPath]: categoryApi.reducer,
    [blogApi.reducerPath]: blogApi.reducer ,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware().concat(
        categoryApi.middleware,
        blogApi.middleware,
    ),
})
