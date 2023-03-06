import { useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'
import Todos from './component/Todos'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <Todos />
    </>
  )
}

export default App
