package hieu.test.coursesmanager.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCourseRequest {
    @NotEmpty(message = "name không được để trống")
    private String name;
    @NotEmpty(message = "description không được để trống")
    @Size(min = 51, message = "description có độ dài ký tự > 50")
    private String description;
    @NotEmpty(message = "type không được để trống")
    private String type;
    private List<String> topics;
    private String thumbnail;
    @NonNull
    private Integer userId;
}