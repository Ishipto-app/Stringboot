import React, { useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { decrement, increment } from '../app/actions/counterActions';

function Counter() {
    const counter = useSelector(state => state.counter);
    const dispatch = useDispatch(); // ban chat la gui action  => store de xu ly
    const handleIncrement = () => {
        //muon co action thi can goi function tuong ung
        dispatch(increment())
    }
    const handleDecrement = () => {
        dispatch(decrement())
    }

  return (
    <div>
        <h1>Counter: {counter}</h1>
        <button onClick={handleIncrement}>Tăng</button>
        <button onClick={handleDecrement}>Giảm</button>
    </div>
  )
}

export default Counter