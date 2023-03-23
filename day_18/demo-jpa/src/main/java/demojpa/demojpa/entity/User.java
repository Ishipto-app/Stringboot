package demojpa.demojpa.entity;

import demojpa.demojpa.dto.UserDto;
import lombok.*;

import javax.persistence.*;

@SqlResultSetMappings(value = {
        @SqlResultSetMapping(
                name = "userInfo", // ten ket qua buoc 1
                classes = @ConstructorResult(
                        targetClass = UserDto.class,
                        columns = {
                                @ColumnResult(name = "id", type = Integer.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "email", type=String.class)
                        }
                )
        )
})
@NamedNativeQuery(
        name = "getUserDtoUsingNativeQuery", //dat ten cho cau lenh query su dung trong repo
        resultSetMapping = "userInfo", // dat ten cho ket qua tra ve
        query = "select u.id, u.name, u.email from user u") //dinh nghia cau lenh native query
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
