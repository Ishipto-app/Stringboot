package hieu.example.todolist.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Todo {
    private Integer id;
    private String title;
    private Boolean status;
//    private LocalDateTime createAt;
//    private String level;
}
