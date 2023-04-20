import { configureStore } from '@reduxjs/toolkit'
import { categoryApi } from './apis/categoryApi'
import { blogApi } from './apis/blogApi'

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
