package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Double getTotalMoney() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                 .map(product -> (Double.valueOf(product.getPrice()) * product.getCount()))
                .reduce(Double.valueOf(0), (total, product) -> total + product);
    }

    public List<Product> findByBrand(String name){

        return productRepository.findByBrandIgnoreCase(name);
    }
    public List<Product> findByPriceGreater(Double price){
        return productRepository.findByPriceGreater(price);
    }

    public List<Product> findByNameContainsIgnoreCase(String name) {
        return productRepository.findByNameContainsIgnoreCase(name);
    }


    public List<Product> addRamdomProduct() throws IOException {
        return productRepository.addRamdomProduct();
    }

    public void deleteProductByBrand(String brand) throws IOException {
        productRepository.deleteProductByBrand(brand);
    }

    public List<Product> sortProductByParams(String column, String type) {
        return productRepository.sortProductByParams(column, type);
    }

    public List<Product> getRamdomProduct() {
        return productRepository.getRamdomProduct();
    }
}
