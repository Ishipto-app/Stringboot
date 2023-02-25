package hieu.test.coursesmanager.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String avatar;
}
