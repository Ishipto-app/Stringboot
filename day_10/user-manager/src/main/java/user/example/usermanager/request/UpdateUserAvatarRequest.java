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
public class UpdateUserAvatarRequest {
    @NotEmpty(message = "avatar không được để trống")
    private String avatar;
}
