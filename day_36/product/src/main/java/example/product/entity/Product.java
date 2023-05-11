package example.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupProduct group;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "primary_unit")
    private String primaryUnit;
    @Column(name = "type1_unit")
    private String type1Unit;
    @Column(name = "type2_unit")
    private String type2Unit;
    @Column(name = "type1_exchance")
    private Integer type1Exchange;
    @Column(name = "type2_exchance")
    private Integer type2Exchange;

    @Column(name = "description")
    private String description;
    @Column(name = "packaging")
    private String packaging;
    @Column(name = "weight")
    private String weight;
    @Column(name = "width")
    private String width;
    @Column(name = "thickness")
    private String thickness;
    @Column(name = "length")
    private String length;
    @Column(name = "remark")
    private String remark;
    @Column(name = "base_price")
    private Float basePrice;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}