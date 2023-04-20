package com.example.blogbackend.mapper;

import com.example.blogbackend.dto.CommentDto;
import com.example.blogbackend.dto.UserDto;
import com.example.blogbackend.entity.Comment;
import com.example.blogbackend.entity.User;

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
