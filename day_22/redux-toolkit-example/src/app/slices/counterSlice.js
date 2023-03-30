import { createSlice } from '@reduxjs/toolkit'

const initialState = 0 //gia tri ban dau cua state

const counterSlice = createSlice({
  name: "counter", // ten slice la duy nhat
  initialState,
  reducers: {
    increment(state, action) { //action type : counter/increment
      // Redux Toolkit allows us to write "mutating" logic in reducers. It
      // doesn't actually mutate the state because it uses the Immer library,
      // which detects changes to a "draft state" and produces a brand new
      // immutable state based off those changes
      return state + 1
    },
    decrement(state, action) { //action type : counter/decrement
      return state - 1
    },
  }
});

export const {increment, decrement} = counterSlice.actions

export default counterSlice.reducer