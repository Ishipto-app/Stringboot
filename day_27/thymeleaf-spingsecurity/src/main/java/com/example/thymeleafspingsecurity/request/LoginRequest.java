package com.example.thymeleafspingsecurity.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class LoginRequest {
    @NotEmpty(message = "email không được để trống")
    private String email;
    @NotEmpty(message = "password không được để trống")
    private String password;
}
