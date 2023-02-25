package product.example.apiproduct.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Product {
    private Integer id;
    private String name;
    private String brand;
    private Double price;
    private Integer count;
}

