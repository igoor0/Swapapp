package dev.university.project.service;

import dev.university.project.model.Category;
import dev.university.project.model.Product;
import dev.university.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    public Product getProduct(String id) {
        return productRepository.findById(id).orElse(null);
    }
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    public Product updateProduct(String id, Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct == null) {
            return null;
        }
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setImage(product.getImage());
        return productRepository.save(existingProduct);
    }
    public boolean deleteProduct(String id) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct == null) {
            return false;
        }
        productRepository.delete(existingProduct);
        return true;
    }
    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }
    public Product getProductByCategory(Category category) {
        return productRepository.findByCategory(category);
    }
    public Product getProductByPrice(double price) {
        return productRepository.findByPrice(price);
    }
}
