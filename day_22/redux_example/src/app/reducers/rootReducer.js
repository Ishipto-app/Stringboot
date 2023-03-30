import { combineReducers } from "redux";
import counterReducer from "./counterReducer";
import todoReducer from "./todoReducer";

const rootReducer = combineReducers({
    counter: counterReducer, //counter ten state
    todos: todoReducer
})

export default rootReducer;