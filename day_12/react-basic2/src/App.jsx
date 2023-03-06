import { useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'
import Box from './component/Box'

function App() {
  const colors = [
    '#3498db',
    '#9b59b6',
    '#e74c3c',
    '#2c3e50',
    '#d35400',
  ]
  const [colorArr, setColorArr] = useState([...colors])
  const handleDelete = (index) => {
    let newColorArr = [...colorArr];
    newColorArr.splice(index, 1)
    setColorArr(newColorArr);
  }
  const addBoxes = () => {
    let newColorArr = [...colorArr, ...colors];
    setColorArr(newColorArr);
  }

  return (
    <>
        <div className="wrap">
            <h1> JS DOM</h1>
            <button id="btn" onClick={addBoxes}>More boxes</button>
            <h4 id="score"> Total box:<span className="points">{colorArr.length}</span></h4>
            {colorArr.map((color, index) => (
                <Box 
                      key={index} 
                      color={color} 
                      index={index}
                      onDelete = {handleDelete}
                  ></Box>
            ))}
        </div>
    </>
  )
}

export default App
