import { useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'
import TodoList from './component/todo-list/TodoList'
import Counter from './component/counter/Counter'
import Register from './component/register/Register'

function App() {
  const [show, setShow] = useState(true);
  const toggle = () => {
    setShow(!show);
  }

  return (
    <>
      <TodoList appName="Todo App"></TodoList>
      {show && <Counter/>}

      <button onClick={toggle}>Toggle</button>
      <Register></Register>
    </>

  )
}

export default App
