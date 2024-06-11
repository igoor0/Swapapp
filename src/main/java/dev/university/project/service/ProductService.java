package dev.university.project.service;

import dev.university.project.model.Category;
import dev.university.project.model.Product;
import dev.university.project.model.User;
import dev.university.project.repository.CategoryRepository;
import dev.university.project.repository.ProductRepository;
import dev.university.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    public Product getProduct(String id) {
        return productRepository.findById(id).orElse(null);
    }

//    public Product createProduct(String categoryId, String userId, Product product) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        product.setOwnerId(user.getId());
//        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
//        product.setCategory(category);
//
//        User productOwner = userRepository.findById(product.getOwnerId()).orElseThrow(() -> new RuntimeException("User not found"));
//        productOwner.getOwnedProductIdList().add(product.getOwnerId());
//        userRepository.save(productOwner);
//        return productRepository.save(product);
//    }

    public Product createProduct(String categoryId, String userId, Product product) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        product.setOwnerId(user.getId());
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);
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
        existingProduct.setCategory(product.getCategory());
        //existingProduct.setImage(product.getImage());
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
    public List<Product> getProductsByProductOwner(String productOwnerId) {
        return productRepository.findAllByOwnerId(productOwnerId);
    }
}
