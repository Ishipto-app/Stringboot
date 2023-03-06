import React, { useState, useEffect } from 'react'

/* 
    useEffect(cb, [deps])
    TH1 ko co deps -> useEffect(cb)
    - duoc chay lai moi lan re-render

    TH2 deps trong -> useEffect(cb, [])
    - chi chay 1 lan duy nhat sau lan render dau tien

    TH3 co deps -> useEffect(cb, [deps])
    - duoc chay lai neu co thay doi ve gia tri cua 1 trong cac dependencies

    chung: deu chay sau render dau tien: render xong => chay useEffect

*/
function Post() {
  const [count, setCount] = useState(0);

  const addCount = () => {
    setCount(count => count + 1)
  }
  const [count1, setCount1] = useState(0);

  const addCount1 = () => {
    setCount1(count1 => count1 + 1)
  }
  // useEffect(() => {
  //   console.log("TH1 ko co deps -> useEffect(cb)");
  // })

  // useEffect(() => {
  //   console.log("TH2 deps [] -> useEffect(cb, [])");
  // }, [])

  useEffect(() => {
    console.log("TH3 co deps -> useEffect(cb, [deps])");
  }, [count])
  return (
    <>
      {console.log("render")}
      <h2>Counter: {count}</h2>
      <button onClick={addCount}>Add</button>
      <hr />
      <h2>Counter1: {count1}</h2>
      <button onClick={addCount1}>Add</button>
    </>
  )
}

export default Post