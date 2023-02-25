package product.example.apiproduct.controller;

import product.example.apiproduct.model.Product;
import product.example.apiproduct.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("products")
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @GetMapping("products/total-money")
    public Double getTotalMoney(){
        return productService.getTotalMoney();
    }

    // api/products/brand?name=apple
    @GetMapping("products/brand")
    public List<Product> findProductByBrand(@RequestParam String name){
        return productService.findByBrand(name);
    }
    // api/products/price?number=20000000
    @GetMapping("products/price")
    public List<Product> findProductPriceGreater(@RequestParam Double number){
        return productService.findByPriceGreater(number);
    }

    // api/products/name?name=pro
    @GetMapping("products/name")
    public List<Product> findProductFilterNameIgnoreCase(@RequestParam String name){
        return productService.findByNameContainsIgnoreCase(name);
    }
    @PostMapping("products/random")
    public List<Product> addRamdomProduct() throws IOException {
        return productService.addRamdomProduct();
    }
    @DeleteMapping("products/{brand}")
    public void deleteProductByBand(@PathVariable String brand) throws IOException {
        productService.deleteProductByBrand(brand);
    }

    // api/products/sort?column=price&type=ASC
    // api/products/sort?column=count&type=DESC
    @GetMapping("products/sort")
    public List<Product> sortProductByParams(@RequestParam(required = false) String column, @RequestParam String type){
        return productService.sortProductByParams(column, type);
    }
    @GetMapping("products/random")
    public List<Product> getRamdomProduct(){
        return productService.getRamdomProduct();
    }
}
