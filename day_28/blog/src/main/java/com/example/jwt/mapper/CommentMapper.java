package com.example.jwt.mapper;

import com.example.jwt.dto.CommentDto;
import com.example.jwt.dto.UserDto;
import com.example.jwt.entity.Comment;
import com.example.jwt.entity.User;

public class CommentMapper {
    public static CommentDto toCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                UserCommentMapper.toUserCommentDto(comment.getUser())
        );
        return commentDto;
    }
}
