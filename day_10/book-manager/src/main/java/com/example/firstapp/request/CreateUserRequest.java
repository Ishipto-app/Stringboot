package com.example.firstapp.request;

import javax.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateUserRequest {
    @NotEmpty(message = "name không được để trống")
    private String name;
    private String email;
    private String phone;
    private String address;
    @NotEmpty(message = "password không được để trống")
    private String password;
}
