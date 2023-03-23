package user.example.usermanager.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Builder
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;

    @Column(name = "avatar")
    private String avatar;
    @Column(name = "password", nullable = false)
    private String password;

}