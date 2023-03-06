import { useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'
import Content from './component/useState/Content'
import Post from './component/useEffect/Post'
import Data from './component/useEffect/Data'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <Data />
    </>
  )
}

export default App
