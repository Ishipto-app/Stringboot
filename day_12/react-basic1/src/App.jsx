import { useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'
import List from './component/list'
import Form from './component/form';

function App() {
  const [list, setList] = useState(["Item 1", "Item 2", "Item 3"]);
  const addItemToList = (name) => {
      let newList = list.slice();
      newList.push(name);
      setList(newList);
  }
  const removeItem = () => {
    let newList = list.slice();
    newList.splice(list.length - 1, 1)
    setList(newList);
  }
  const [show, setShow] = useState(true);
  const toggle = () => {
    setShow(!show);
  }

  return (
    <>
      <button onClick={toggle}>{show ? "Hide" : "Show"}</button>
      <br></br>
      {show && <List list={list}/>}
      <br></br>
      <button onClick={removeItem}>Remove</button>
      <br></br>
      <Form addItemToList={addItemToList} />
    </>
  )
}

export default App
