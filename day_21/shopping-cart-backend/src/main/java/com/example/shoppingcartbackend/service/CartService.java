package com.example.shoppingcartbackend.service;

import com.example.shoppingcartbackend.dto.CartItemDto;
import com.example.shoppingcartbackend.repository.CartItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartService {
    @Autowired
    private final CartItemRepository cartItemRepository;

    public List<CartItemDto> getAllCartItems() {
        //return cartItemRepository.getAllCartItems();
        return null;
    }

    public void deleteCartItem(Integer id) {
        //cartItemRepository.deleteCartItem(id);
    }

    public void cartItemIncrementById(Integer id) {
        //cartItemRepository.cartItemIncrementById(id);
    }

    public void cartItemDecrementById(Integer id) {
        //cartItemRepository.cartItemDecrementById(id);

    }

}
