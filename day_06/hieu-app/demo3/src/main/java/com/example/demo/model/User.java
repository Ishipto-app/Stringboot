package com.example.demo.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User{
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String avatar;
}
