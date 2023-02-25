package com.example.demo.repository;

import com.example.demo.database.ProductDB;
import com.example.demo.model.Product;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//quy dinh chi repo moi dc thao tac voi DB
@Repository
public class ProductRepository {
    public List<Product> findAll() {
        return ProductDB.products;
    }
    public List<Product> findByBrandIgnoreCase(String brand) {
        List<Product> products = this.findAll();
        return products.stream()
                .filter(product -> product.getBrand().equalsIgnoreCase(brand))
                .toList();
    }
    public List<Product> findByPriceGreater(Double price) {
        List<Product> products = this.findAll();
        return ProductDB.products.stream()
                .filter(product -> product.getPrice() > price)
                .toList();
    }
    public List<Product> findByNameContainsIgnoreCase(String name) {
        List<Product> products = this.findAll();
        return ProductDB.products.stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
    public List<Product> addRamdomProduct() throws IOException {
        List<Product> products = this.findAll();
        Product updatedProduct = products.stream()
                .findAny()
                .map(product -> {
                    product.setCount(product.getCount() + 1);
                    return product;
                })
                .orElse(null);
        ProductDB.saveProductToFile();
        return (List<Product>) updatedProduct;
    }
    public void deleteProductByBrand(String brand) throws IOException {
        ProductDB.products.removeIf(product -> product.getBrand().equals(brand));
        ProductDB.saveProductToFile();
    }
    public List<Product> sortProductByParams(String column, String type) {
        System.out.println("col " + column + " type " + type);
        switch (column) {
            case "name" -> {
                if(Objects.equals(type, "DESC")) ProductDB.products.sort(Comparator.comparing(Product::getName).reversed());
                if(Objects.equals(type, "ASC")) ProductDB.products.sort(Comparator.comparing(Product::getName));
            }
            case "brand" -> {
                if(Objects.equals(type, "DESC")) ProductDB.products.sort(Comparator.comparing(Product::getBrand).reversed());
                if(Objects.equals(type, "ASC")) ProductDB.products.sort(Comparator.comparing(Product::getBrand));
            }
            case "price" -> {
                if(Objects.equals(type, "DESC")) ProductDB.products.sort(Comparator.comparing(Product::getPrice).reversed());
                if(Objects.equals(type, "ASC")) ProductDB.products.sort(Comparator.comparing(Product::getPrice));
            }
            case "count" -> {
                if (Objects.equals(type, "DESC")) ProductDB.products.sort(Comparator.comparing(Product::getCount).reversed());
                if (Objects.equals(type, "ASC")) ProductDB.products.sort(Comparator.comparing(Product::getCount));
            }
            default -> {
                if (Objects.equals(type, "DESC")) ProductDB.products.sort(Comparator.comparing(Product::getId).reversed());
                if (Objects.equals(type, "ASC")) ProductDB.products.sort(Comparator.comparing(Product::getId));
            }
        };
        return ProductDB.products;
    }

    public List<Product> getRamdomProduct() {
        List<Product> twoProduct = ProductDB.products.stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), lst -> {
                    Collections.shuffle(lst);
                    return lst.stream().limit(2).collect(Collectors.toList());
                }));
        return twoProduct;
    }
}
