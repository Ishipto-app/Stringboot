import React, {useState} from 'react'

const orders = [10000, 20000, 30000]

function Content() {
  // khi setState bat ki toan bo function duoc thuc hien lai de render view
  // xu ly phuc tap ton kem nhieu thoi gian thuc hien => chuyen thanh ham call back trong useState
  // const rs = orders.reduce((a, b) => a + b, 0);
  // console.log(rs)
  const [total, setTotal] = useState(() => {
    const rs = orders.reduce((a, b) => a + b, 0);
    console.log(rs)
    return rs;
  });

  const [count, setCount] = useState(0);

  const addCount = () => {
    //tinh toan state moi tu state cu dung callback function
    // render 1 lan duy nhat
    setCount(count => count + 1)
    setCount(count => count + 1)
    setCount(count => count + 1)
  }

  const [user, setUser] = useState({
    id: 1,
    name: "Nguyen Van A",
    email: "a@gmail.com"
  });

  const randomName = () => {
    const rdName = `New name ${Math.floor(Math.random() * 1000)}`;
    // khong thao tac thay doi user truc tiep su dung setUser
    setUser({...user, name: rdName});
  }

  const [products, setProduct] = useState([
    {id: 1, name: "Product 1", price: 10000},
    {id: 2, name: "Product 2", price: 20000},
    {id: 3, name: "Product 3", price: 30000}
  ])

  const randomPrice = () => {
    const productId = 1;
    const rdPrice = Math.floor(Math.random() * 100000);
    //map moi vong lap la 1 callback function ket thuc function dung return => ko co continus break
    const newProduct = products.map(p => {
      if(p.id === productId){
        return{...p, price: rdPrice};
      }
      return p;
    })
    setProduct(newProduct);
  }

  const deleteProduct = (id) => {
    const newProduct = products.filter(p => p.id !== id);
    setProduct(newProduct);
  }

  return (
    <>
      {console.log("render")}
      <h2>Counter: {count}</h2>
      <button onClick={addCount}>Add</button>
      <hr />
      <h2>User</h2>
      <p>
        {user.id} - {user.name} - {user.email}
      </p>
      <button onClick={randomName}>Random name</button>
      <hr />
      <h2>Product</h2>
      <ul>
        {products.map(p => (
          <li key={p.id}>
            {p.id} - {p.name} - {p.price}
            <button onClick={() => deleteProduct(p.id)}>Xoa</button>
          </li>
        ))}
      </ul>
      <button onClick={randomPrice}>Random price</button>
    </>
    //neu co tham so truyen vao thi dung anonimus funciton de function ko kick hoat khi render
  )
}

export default Content