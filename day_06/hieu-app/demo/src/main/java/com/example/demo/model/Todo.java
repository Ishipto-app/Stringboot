package com.example.demo.model;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//Builder annocation cho phep khoi tao doi tuong theo tung phan
@Builder
public class Todo {
    private Integer id;
    private String title;
    private Boolean status;
//    private LocalDateTime createAt;
//    private String level;
}
