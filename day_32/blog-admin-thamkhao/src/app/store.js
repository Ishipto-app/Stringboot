import { configureStore } from "@reduxjs/toolkit";
import { authApi } from "./apis/authApi";
import { blogApi } from "./apis/blogApi";
import authReducer from "./slice/authSlice";

// Context API + useReducer có thể thay thế cho redux
const store = configureStore({
    reducer: {
        [blogApi.reducerPath]: blogApi.reducer,
        [authApi.reducerPath]: authApi.reducer,
        auth: authReducer
    },
    middleware: (getDefaultMiddleware) =>
        getDefaultMiddleware().concat(
            blogApi.middleware,
            authApi.middleware
        ),
});

export default store;
