package hieu.example.todolist.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateTodoRequest {
    @NotEmpty(message = "title không được để trống")
    private String title;

//    @NotEmpty(message = "level không được để trống")
//    private String level;
}
