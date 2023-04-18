package com.example.jwt.mapper;

import com.example.jwt.dto.UserCommentDto;
import com.example.jwt.entity.User;

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
