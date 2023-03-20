package demojpa.demojpa.entity;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "USER")
public class User {
    // danh dau thuoc tinh la khoa chinh @Id => auto unique = true, nullable = false
    // tu sinh =>
    // 1 database tu sinh
    // 2 user tu sinh
    // khong tu sinh: user tu them dam nao khong trung id
    @Id
    @Column(name = "id", unique = true, nullable = false)
    //1 database tu sinh
    //IDENTITY tu dong generate id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
}
