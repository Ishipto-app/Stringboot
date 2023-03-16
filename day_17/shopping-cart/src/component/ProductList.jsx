import React from 'react'
import ProductItem from './ProductItem'

function ProductList({cartItems, increment, decrement, deleteItem}) {
  return (
    <div className="col-md-8">
        <div className="product-list">
            {cartItems.map((item, index) => (
                <ProductItem key={item.id} data={item} increment={increment} decrement={decrement} deleteItem={deleteItem} />
            ))}
        </div>
    </div>
  )
}

export default ProductList