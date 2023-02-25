package user.example.usermanager.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserPasswordRequest {
    @NotEmpty(message = "password không được để trống")
    private String oldPassword;
    @NotEmpty(message = "new password không được để trống")
    private String newPassword;
}
