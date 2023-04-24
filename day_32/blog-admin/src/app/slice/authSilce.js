import { createSlice } from "@reduxjs/toolkit";
import { authApi } from "../apis/authApi";
import { getData, setData } from "../../utils/localStorageUtils";

const defaultState = {
    auth: null,
    token: null,
    isAuthenticated: false
}
const initialState = getData("authBlog") ? getData("authBlog") : defaultState;
// const initialState = {
//     auth: null,
//     token: null,
//     isAuthenticated: false
// }
const authSlice = createSlice({
    name: "auth",
    initialState,
    reducers: {
        logout: (state) => { //action type: auth/logout
            state = defaultState;
            setData("authBlog", state);
            return state;
        }
    },
    //doi tuong tac dong ben ngoai thay doi state (api)
    extraReducers: (builder) => {
        builder.addMatcher(authApi.endpoints.login.matchFulfilled, (state, action) => {
            state.auth = action.payload.auth;
            state.token = action.payload.token;
            state.isAuthenticated = action.payload.isAuthenticated;

            setData("authBlog", state);
        })
    }
})

export const { logout } = authSlice.actions

export default authSlice.reducer