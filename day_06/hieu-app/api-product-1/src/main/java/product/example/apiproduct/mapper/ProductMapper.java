package product.example.apiproduct.mapper;

import product.example.apiproduct.model.Product;
import product.example.apiproduct.model.ProductDto;

public class ProductMapper {
    public static ProductDto toProductDto (Product product){
        return new ProductDto(product.getName(), product.getBrand());
    }

}
