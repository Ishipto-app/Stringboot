package login.example.applogin.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserServer{
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String avatar;
}
