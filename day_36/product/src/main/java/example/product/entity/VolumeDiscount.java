package example.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "volume_discount")
public class VolumeDiscount {
    // Todo: chua suy nghi ra cach tinh gia theo nhieu khung gia
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "description")
    private String description;
    @Column(name = "volume")
    private Integer volume;
    @Column(name = "price")
    private Float price;
}