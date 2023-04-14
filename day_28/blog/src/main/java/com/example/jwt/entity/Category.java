package com.example.jwt.entity;

import com.example.jwt.dto.CategoryDto;
import jakarta.persistence.*;
import lombok.*;


@SqlResultSetMappings(value = {
        @SqlResultSetMapping(
                name = "categoryInfo",
                classes = @ConstructorResult(
                        targetClass = CategoryDto.class,
                        columns = {
                                @ColumnResult(name = "id", type = Integer.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "used", type = Long.class)
                        }
                )
        )
})
@NamedNativeQuery(
        name = "findLimitCategories",
        resultSetMapping = "categoryInfo",
//                "ORDER BY used DESC " +
//                "LIMIT 5"
        query = "select c.id, c.name, COUNT(b.id) AS used from category c\n" +
                "left join blog_category bc\n" +
                "on c.id = bc.category_id\n" +
                "left join blog b\n" +
                "on bc.blog_id = b.id\n" +
                "group by c.id\n" +
                "order by used DESC\n" +
                "limit :limit"
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

}