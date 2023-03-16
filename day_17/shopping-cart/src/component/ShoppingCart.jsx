import axios from 'axios';
import React, { useState, useEffect } from 'react'
import BillInformation from './BillInformation'
import ProductList from './ProductList'
const API_URL = "http://localhost:8080/api/v1/cartItems"

function ShoppingCart() {
    const [cartItems, setCartItems] = useState([]);
    const [money, setMoney] = useState({
        subtotal: 0,
        vat: 0,
        total: 0
    });

    useEffect(() => {
        const getAllCartItems = async () => {
          try {
              let rs = await axios.get(API_URL);
              console.log(rs.data)
              setCartItems(rs.data);
              getMoney(rs.data)
          } catch (error) {
              console.error(error)
          }
        }
        getAllCartItems();
      }, []);
    const increment = async (data) => {
        console.log(data)
        try {
            let rs = await axios.put(API_URL + "/" + data.id + "/increment");
            let newItems = cartItems.map(item => {
                if(item.id == data.id) item.count++;
                return item;
            })
            setCartItems(newItems)
            getMoney(newItems)
        } catch (error) {
            console.error(error)
        }
    }
    const decrement = async (data) => {
        console.log(data)
        try {
            let rs = await axios.put(API_URL + "/" + data.id + "/decrement");
            let newItems = cartItems.map(item => {
                if(item.id == data.id) item.count--;
                return item;
            })
            setCartItems(newItems)
            getMoney(newItems)
        } catch (error) {
            console.error(error);
            alert("error");
        }
    }
    const deleteItem = async (id) => {
        if(confirm("Bạn có muốn xóa sản phẩm này không?")){
            try {
                let rs = await axios.delete(API_URL + "/" + id);
                let newItems = cartItems.filter(cart => cart.id != id)

                setCartItems(newItems)
                getMoney(newItems)
            } catch (error) {
                console.error(error)
            }
        }
    }
    const getMoney = (cartItems) => {
        let [subtotal, vat, total] = [0,0,0];
        cartItems.map(item => {
            subtotal += item.count * item.course.price;
            vat += item.count * item.course.price / 10; // 10%
            total += item.count * item.course.price * 11 / 10; // 110%
        })
        setMoney({
            subtotal: subtotal,
            vat: vat,
            total: total
        })
    }
  return (
    <>
        <div className="shopping-cart-container mt-5">
            <div className="container">
                <div className="row">
                    <div className="col-md-12">
                        <div className="mb-4">
                            <h2>Shopping Cart</h2>
                        </div>
                    </div>
                </div>

                {cartItems.length == 0 && <p className="fst-italic message" >Không có sản phẩm trong giỏ hàng</p>}
                {cartItems.length > 0 && <div className="row shopping-cart">
                    <ProductList cartItems={cartItems} increment={increment} decrement={decrement} deleteItem={deleteItem} />
                    <BillInformation money={money}/>
                </div>}
            </div>
        </div>
    </>
  )
}

export default ShoppingCart