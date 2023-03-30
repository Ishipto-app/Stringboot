import React, { useState } from 'react'
let categories = [
    {id:1, name: "frontend"}, 
    {id:2, name: "backend"}, 
    {id:3, name: "database"},
    {id:4, name: "devops"},
    {id:5, name: "basic"}, 
    {id:6, name: "mobile"},
    {id:7, name: "Illaoi"} //lay category tren database
]
const API_URL = "http://localhost:8080/api/v1/"

function CourseSidebar({category, onCategoryChange}) {
    const [selectedValue, setSelectedValue] = useState(category);
    
    // const [categories setCategories] = useState([])
    // useEffect(() => {
    //     const getAllCategories = async () => {
    //       try {
    //           let rs = await axios.get(API_URL + 'category');
    //           console.log(rs.data)
    //           setCategories(rs.data);
    //       } catch (error) {
    //           console.error(error)
    //       }
    //     }
    //     getAllCategories();
    //   }, []);
    const handleChange = (e) => {
        setSelectedValue(e.target.value);
        onCategoryChange(e.target.value);
    }

  return (
    <>
        <div className="col-md-3">
            <h2 className="fs-5 mb-4">Chủ đề</h2>
            {categories.map(item => (
                <div className="category-item input-group d-flex align-items-center mb-1" key={item.id}>
                    <input type="radio" name="category" id={item.name} 
                        value={item.name}
                        checked={selectedValue === item.name}
                        onChange={handleChange}/>
                    <label htmlFor={item.name} className="ms-2 fs-5">{item.name}</label>
                </div>
            ))}
        </div>
    </>
  )
}

export default CourseSidebar