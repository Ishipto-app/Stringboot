import { createSlice } from "@reduxjs/toolkit";
import { authApi } from "../apis/authApi";
import { getData, setData } from "../../utils/localStorageUtils";

const defaultState = {
    auth: null,
    token: null,
    isAuthenticated: false
}
const initialState = getData("authBlog") ? getData("authBlog") : defaultState;
console.log(getData("authBlog"))
// const initialState = {
//     auth: null,
//     token: null,
//     isAuthenticated: false
// }
const authSlice = createSlice({
    name: "auth",
    initialState,
    reducers: {},
    //doi tuong tac dong ben ngoai
    extraReducers: (builder) => {
        builder.addMatcher(authApi.endpoints.login.matchFulfilled, (state, action) => {
            state.auth = action.payload.auth;
            state.token = action.payload.token;
            state.isAuthenticated = action.payload.isAuthenticated;

            setData("authBlog", state);
        })
    }
})

export const { } = authSlice.actions

export default authSlice.reducer