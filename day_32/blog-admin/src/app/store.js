import { configureStore } from '@reduxjs/toolkit'
import { categoryApi } from './apis/categoryApi'
import { blogApi } from './apis/blogApi'
import { authApi } from './apis/authApi'
import authReducer from './slice/authSilce'
import { userApi } from './apis/userApi'

//Context api + useReduer thay cho
export const store = configureStore({
  reducer: {
    [categoryApi.reducerPath]: categoryApi.reducer,
    [userApi.reducerPath]: userApi.reducer,
    [blogApi.reducerPath]: blogApi.reducer,
    [authApi.reducerPath]: authApi.reducer,
    auth: authReducer
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware().concat(
        categoryApi.middleware,
        userApi.middleware,
        blogApi.middleware,
        authApi.middleware,
    ),
})
