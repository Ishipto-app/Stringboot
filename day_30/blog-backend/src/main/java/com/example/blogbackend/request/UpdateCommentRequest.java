package com.example.blogbackend.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateCommentRequest {
    @NotEmpty(message = "content không được để trống")
    private String content;
}
