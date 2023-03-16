package com.example.shoppingcartbackend.mapper;

import com.example.shoppingcartbackend.dto.CartItemDto;
import com.example.shoppingcartbackend.exception.BadRequestException;
import com.example.shoppingcartbackend.model.CartItem;
import com.example.shoppingcartbackend.model.Course;

import java.util.Objects;
import java.util.Optional;

import static com.example.shoppingcartbackend.db.CourseDB.courses;

public class CartMapper {
    public static CartItemDto cartItemDto(CartItem cartItem){
        Optional<Course> courseOptional = courses.stream()
                .filter(course -> Objects.equals(course.getId(), cartItem.getCourseId()))
                .findFirst();
        if(courseOptional.isPresent()){
            Course course = courseOptional.get();
            return new CartItemDto(cartItem.getId(), course, cartItem.getCount());
        }
        throw new BadRequestException("không có user với id = " + cartItem.getCourseId());
    }
}
