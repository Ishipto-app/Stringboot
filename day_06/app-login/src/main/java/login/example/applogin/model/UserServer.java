package login.example.applogin.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserServer{
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String avatar;
}
