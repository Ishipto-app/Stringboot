package com.example.blogbackend.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateCommentRequest {
    @NotEmpty(message = "content không được để trống")
    private String content;
}
