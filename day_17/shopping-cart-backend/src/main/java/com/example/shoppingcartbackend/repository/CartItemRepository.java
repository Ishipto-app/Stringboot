package com.example.shoppingcartbackend.repository;

import com.example.shoppingcartbackend.dto.CartItemDto;
import com.example.shoppingcartbackend.exception.BadRequestException;
import com.example.shoppingcartbackend.mapper.CartMapper;
import com.example.shoppingcartbackend.model.CartItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.shoppingcartbackend.db.CartDB.cart;

@Repository
public class CartItemRepository {
    public List<CartItem> findAll() {
        return cart;
    }

    public List<CartItemDto> getAllCartItems() {
        List<CartItem> cartItems = this.findAll();
        return cartItems.stream()
                .map(item -> CartMapper.cartItemDto(item))
                .toList();
    }

    public void deleteCartItem(Integer id) {
        List<CartItem> cartItems = this.findAll();
        Optional<CartItem> cartItemOptional = cartItems.stream()
                .filter(course -> Objects.equals(course.getId(), id))
                .findFirst();
        if(cartItemOptional.isPresent()){
            // thuc hien delete database
            cart.removeIf(item -> Objects.equals(item.getId(), id));
            System.out.println("delete success cart item id = " + id);
        } else {
            throw new BadRequestException("không có cart item với id = " + id);
        }
    }


    public void cartItemIncrementById(Integer id) {
        List<CartItem> cartItems = this.findAll();
        Optional<CartItem> cartItemOptional = cartItems.stream()
                .filter(item -> Objects.equals(item.getId(), id))
                .findFirst();
        if(cartItemOptional.isPresent()){
            // thuc hien update database
            cart.stream()
                .filter(item -> Objects.equals(item.getId(), id))
                .map(item -> {
                    item.setCount(item.getCount() + 1);
                    return new CartItem(item.getId(), item.getCourseId(), item.getCount());
                })
                .collect(Collectors.toList());
            System.out.println("tang 1 voi id = " + id);
        } else {
            throw new BadRequestException("không có cart item với id = " + id);
        }

    }

    public void cartItemDecrementById(Integer id) {
        List<CartItem> cartItems = this.findAll();
        Optional<CartItem> cartItemOptional = cartItems.stream()
                .filter(item -> Objects.equals(item.getId(), id))
                .findFirst();
        if(cartItemOptional.isPresent()){
            // thuc hien update database
            cart.stream()
                    .filter(item -> Objects.equals(item.getId(), id))
                    .map(item -> {
                        if(item.getCount() == 1) throw new BadRequestException("số lượng < 1 với id = " + id);
                        item.setCount(item.getCount() - 1);
                        return new CartItem(item.getId(), item.getCourseId(), item.getCount());
                    })
                    .collect(Collectors.toList());
            System.out.println("giam 1 voi id = " + id);
        } else {
            throw new BadRequestException("không có cart item với id = " + id);
        }
    }
}
