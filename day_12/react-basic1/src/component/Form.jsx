import React, {useState} from 'react'

function Form({addItemToList}) {
    const [name, setName] = useState("");
    const addItem = () => {
        if(name.trim() == ""){
        alert("name required");
            return;
        }
        addItemToList(name);
        setName("");
    }
  return (
    <>
        <input 
            type="text" 
            placeholder='Enter item name' 
            value={name} 
            onChange={e => setName(e.target.value)}
        />
        <button onClick={addItem}>Add</button>
    </>
  )
}

export default Form