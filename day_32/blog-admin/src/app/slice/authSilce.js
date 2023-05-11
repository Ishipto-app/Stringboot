import { createSlice } from "@reduxjs/toolkit";
import { authApi } from "../apis/authApi";
import { getData, setData } from "../../utils/localStorageUtils";

const defaultState = {
    auth: null,
    token: null,
    isAuthenticated: false
}
const parseJwt = (token) => {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(jsonPayload);
}
const isTokenExpired = (token) => {
    try {
      const { exp } = parseJwt(token);
      const currentTime = Date.now() / 1000; // convert to seconds
      return exp < currentTime;
    } catch (error) {
      return true; // invalid token or decoding failed
    }
}
const { token } = getData("authBlog");
// dam bao co token va token chua exprired
if(!token || isTokenExpired(token)){
    setData("authBlog", defaultState)
}
const initialState = getData("authBlog")
// const initialState = getData("authBlog") ? getData("authBlog") : defaultState;
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