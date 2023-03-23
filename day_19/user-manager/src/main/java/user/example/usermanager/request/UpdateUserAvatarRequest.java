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
public class UpdateUserAvatarRequest {
    @NotEmpty(message = "avatar not nut")
    private String avatar;
}
