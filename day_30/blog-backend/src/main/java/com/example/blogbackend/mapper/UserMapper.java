package com.example.blogbackend.mapper;

import com.example.blogbackend.dto.UserDto;
import com.example.blogbackend.entity.User;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAvatar(),
                user.getRoles()
        );
        return userDto;
    }
}
