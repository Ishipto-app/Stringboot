package com.example.firstapp.mapper;

import com.example.firstapp.dto.UserDto;
import com.example.firstapp.model.User;

public class UserMapper {
    public static UserDto userDto(User user){
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getAddress(), user.getAvatar());
    }
}
