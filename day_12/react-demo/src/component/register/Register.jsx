import React, { useState } from 'react'

function Register() {
    const [name, setName] = useState("");
    const [password, setPassword] = useState("");
    const handRegister = (e) => {
        e.preventDefault();
        alert(JSON.stringify({name, password}));
    }
  return (
    <form onSubmit={handRegister}>
        <input 
            type="text" 
            placeholder='enter name' 
            value={name} 
            onChange={e => setName(e.target.value)} 
        />
        <input 
            type="password" 
            placeholder='enter password' 
            value={password} 
            onChange={e => setPassword(e.target.value)}
        />
        <button>Register</button>
    </form>
  )
}

export default Register