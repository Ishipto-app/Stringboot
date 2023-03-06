package user.example.usermanager.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserPasswordForgotRequest {
    @NotEmpty(message = "name không được để trống")
    private String password;
}
