package hieu.example.todolist.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTodoRequest{
    @NotEmpty(message = "title không được để trống")
    private String title;
    private Boolean status;

//    @NotEmpty(message = "level không được để trống")
//    private String level;
}

