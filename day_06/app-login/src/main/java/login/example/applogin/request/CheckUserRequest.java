package login.example.applogin.request;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CheckUserRequest {
    @NotEmpty(message = "username không được để trống")
    private String username;
    @NotEmpty(message = "password không được để trống")
    private String password;
}
