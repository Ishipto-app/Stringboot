package com.example.blogbackend.request;

import jakarta.validation.constraints.Email;
import lombok.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateUserRequest {
    @NotEmpty(message = "name không được để trống")
    private String name;
    @NotEmpty(message = "email không được để trống")
    @Email(message = "email khong dung type")
    private String email;
    @NotEmpty(message = "password không được để trống")
    private String password;
    private List<Integer> roleIds; // Danh sách id của các role áp dụng
}

