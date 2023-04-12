package com.example.registeruser.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotEmpty(message = "name không được để trống")
    private String name;

    @NotEmpty(message = "email không được để trống")
    private String email;

    @NotEmpty(message = "password không được để trống")
    @Size(min = 6, message = "password có độ dài ký tự >= 6")
    private String password;
}
