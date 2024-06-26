package dev.university.project.controller;

import dev.university.project.model.Category;
import dev.university.project.model.Product;
import dev.university.project.service.CategoryService;
import dev.university.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable String id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
    @PostMapping("{categoryId}/{productOwnerId}")
    public ResponseEntity<Product> createProduct(@PathVariable String categoryId, @PathVariable String productOwnerId, @RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(categoryId, productOwnerId, product));
    }
    @GetMapping("/user/{productOwner}")
    public ResponseEntity<List<Product>> getProductByProductOwnerId(@PathVariable String productOwner) {
        return ResponseEntity.ok(productService.getProductsByProductOwner(productOwner));
    }
}
