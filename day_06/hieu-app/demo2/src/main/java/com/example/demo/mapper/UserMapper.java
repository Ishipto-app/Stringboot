package com.example.demo.mapper;

import com.example.demo.model.User;
import com.example.demo.model.UserDto;

public class UserMapper {
    public static UserDto toUserDto (User user){
        return new UserDto(user.getUsername(), user.getEmail(), user.getAvatar());
    }

}
