package com.example.blogbackend.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateBlogRequest {
    @NotEmpty(message = "title không được để trống")
    private String title;

    @NotEmpty(message = "description không được để trống")
    @Size(min = 10, message = "description có độ dài ký tự > 10")
    private String description;

    @NotEmpty(message = "content không được để trống")
    private String content;

    private String thumbnail;

    private Boolean status;

    //private List<Category> categories;
    private List<Integer> categoryIds;
}

