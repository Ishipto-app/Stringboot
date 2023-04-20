package com.example.blogbackend.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateCategoryRequest {
    @NotEmpty(message = "name không được để trống")
    private String name;
}
