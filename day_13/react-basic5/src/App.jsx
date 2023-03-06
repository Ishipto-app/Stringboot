import { useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'
import UseList from './hooks/useEffect/UseList'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <UseList/>
    </>
  )
}

export default App
