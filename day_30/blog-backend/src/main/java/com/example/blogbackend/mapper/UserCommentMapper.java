package com.example.blogbackend.mapper;

import com.example.blogbackend.dto.UserCommentDto;
import com.example.blogbackend.entity.User;

public class UserCommentMapper {
    public static UserCommentDto toUserCommentDto(User user) {
        UserCommentDto userCommentDto = new UserCommentDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
        return userCommentDto;
    }
}
