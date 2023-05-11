package com.example.blogbackend.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserPasswordRequest {
    @NotEmpty(message = "name không được để trống")
    private String oldPassword;
    @NotEmpty(message = "name không được để trống")
    private String newPassword;
}
