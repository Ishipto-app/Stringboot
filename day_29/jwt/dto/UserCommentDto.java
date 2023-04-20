package com.example.blogbackend.dto;

import com.example.blogbackend.entity.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCommentDto {
    private Integer id;
    private String name;
    private String email;
}
