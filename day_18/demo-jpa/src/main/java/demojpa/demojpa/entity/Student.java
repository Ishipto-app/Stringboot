package demojpa.demojpa.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
@Builder
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "email", unique = true)
    private String email;

}