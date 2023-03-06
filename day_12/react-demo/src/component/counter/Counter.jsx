import React, { useState } from 'react'

// mounting -> render lan dau
// updating -> thuc hien update sau do render lai
// unmounting -> xoa bo component
function Counter() {
    const [count, setCount] = useState(10);
    const increment = () => {
        setCount(count + 1);
    }
    const decrement = () => {
        setCount(count - 1);
    }

  return (
    <div>
        <h1>Counter: {count}</h1>
        <button onClick={increment}>Tăng</button>
        <button onClick={decrement}>Giảm</button>
    </div>
  )
}

export default Counter