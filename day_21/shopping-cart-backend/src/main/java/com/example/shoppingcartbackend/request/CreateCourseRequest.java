package com.example.shoppingcartbackend.request;

import com.example.shoppingcartbackend.entity.Category;
import com.example.shoppingcartbackend.entity.User;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateCourseRequest {
    @NotEmpty(message = "name không được để trống")
    private String name;
    @NotEmpty(message = "description không được để trống")
    @Size(min = 51, message = "description có độ dài ký tự > 50")
    private String description;
    @NotEmpty(message = "type không được để trống")
    private String type;
    private String thumbnail;
    private Integer price;
    private Double rating;
    @NotNull(message = "User không được để trống")
    //private User user;
    private Integer userId;

    //private List<Category> categories;
    private List<Integer> categoryIds;
}
