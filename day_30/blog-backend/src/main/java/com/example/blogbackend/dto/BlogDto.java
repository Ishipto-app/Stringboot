package com.example.blogbackend.dto;

import com.example.blogbackend.entity.Category;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BlogDto {
    private Integer id;
    private String title;
    private String slug;
    private String description;
    private String content;
    private String thumbnail;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime publishedAt;

    private boolean status;

    private UserCommentDto user;

    private List<Category> categories = new ArrayList<>();

    private List<CommentDto> comments = new ArrayList<>();
}
