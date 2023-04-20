package com.example.blogbackend.mapper;

import com.example.blogbackend.dto.BlogDto;
import com.example.blogbackend.entity.Blog;

public class BlogMapper {
    public static BlogDto toBlogDto(Blog blog) {
        BlogDto blogDto = new BlogDto(
                blog.getId(),
                blog.getTitle(),
                blog.getSlug(),
                blog.getDescription(),
                blog.getContent(),
                blog.getThumbnail(),
                blog.getCreatedAt(),
                blog.getUpdatedAt(),
                blog.getPulishedAt(),
                true,
                UserCommentMapper.toUserCommentDto(blog.getUser()),
                blog.getCategories(),
                blog.getComments().stream()
                        .map(comment -> CommentMapper.toCommentDto(comment))
                        .toList()
                );
        return blogDto;
    }
}
