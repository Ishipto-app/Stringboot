package com.example.shoppingcartbackend.controller;

import com.example.shoppingcartbackend.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.shoppingcartbackend.dto.CartItemDto;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class CartController {
    @Autowired
    private final CartService cartService;


    // /api/v1/cartItems
    @GetMapping("cartItems")
    public List<CartItemDto> getAllCartItems(){
        return cartService.getAllCartItems();
    }

    // /api/v1/cartItems/{id}
    @DeleteMapping("cartItems/{id}")
    public void deleteCartItem(@PathVariable Integer id){
        cartService.deleteCartItem(id);
    }

    // /api/v1/cartItems/{id}/increment
    @PutMapping("cartItems/{id}/increment")
    public void cartItemIncrementById(@PathVariable Integer id){
        cartService.cartItemIncrementById(id);
    }

    // /api/v1/cartItems/{id}/decrement
    @PutMapping("cartItems/{id}/decrement")
    public void cartItemDecrementById(@PathVariable Integer id){
        cartService.cartItemDecrementById(id);
    }


}
