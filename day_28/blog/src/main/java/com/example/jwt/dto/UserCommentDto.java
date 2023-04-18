package com.example.jwt.dto;

import com.example.jwt.entity.Role;
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
