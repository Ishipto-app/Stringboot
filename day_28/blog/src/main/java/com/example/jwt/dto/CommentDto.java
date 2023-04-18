package com.example.jwt.dto;

import com.example.jwt.entity.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDto {
    private Integer id;
    private String content;
    private LocalDateTime createdAt;
    private UserCommentDto user;
}
