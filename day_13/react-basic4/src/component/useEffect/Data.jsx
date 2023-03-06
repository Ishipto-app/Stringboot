import React, { useState, useEffect } from 'react'

// vi du ve goi api
function Data() {
  const [type, setType] = useState("posts")
  const [data, setData] = useState([]);
  useEffect(() => {
    const fetchData = async () => {
      const data = await fetch("https://jsonplaceholder.typicode.com/" + type);
      const dataJson = await data.json()
      console.log(dataJson)
      setData(dataJson);
    }

    fetchData();
    // khi type thay doi useEffect thuc hien lai
  }, [type])
  return (
    <>
      <h2>Type: {type}</h2>

      {["posts", "photos", "albums"].map((item, index) => (
        <button key={index} onClick={() => setType(item)} style={item == type ? {color: "white", background: "red"} : null}>{item}</button>
      ))
      
      /*
      <button onClick={() => setType("posts")} style={type == "posts" ? {color: "white", background: "red"} : null}>posts</button>
      <button onClick={() => setType("photos")}>photos</button>
      <button onClick={() => setType("albums")}>albums</button>
      */}
      <ul>
        {data.map(item => (
          <li key={item.id}>{item.title}</li>
        ))}
      </ul>
    </>
  )
}

export default Data