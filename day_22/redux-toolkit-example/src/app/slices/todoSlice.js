import { createSlice } from '@reduxjs/toolkit'

const initialState = [
    {id: 1, title: "lam bai tap 1"},
    {id: 2, title: "lam bai tap 2"},
    {id: 3, title: "lam bai tap 3"}
];

const todoSlice = createSlice({
  name: "todoList",
  initialState,
  reducers: {
    addTodo(state, action){
        state.push(action.payload);
    },
    deleteTodo(state, action){
        const index = state.findIndex(todo => todo.id == action.payload);
        state.splice(index, 1)
        //state = state.filter(todo => todo.id != action.payload)
    }
  }
});

export const {addTodo, deleteTodo} = todoSlice.actions

export default todoSlice.reducer