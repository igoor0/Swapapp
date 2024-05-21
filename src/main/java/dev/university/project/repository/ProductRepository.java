package dev.university.project.repository;

import dev.university.project.model.Category;
import dev.university.project.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>{
    Product findByName(String name);
    Product findByCategory(Category category);
    Product findByPrice(double price);
    List<Product> findAllByProductOwner(String productOwnerId);
}
