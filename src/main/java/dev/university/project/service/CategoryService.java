package dev.university.project.service;

import dev.university.project.model.Category;
import dev.university.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryByNameOrId(String category) {
        return categoryRepository.findById(category).orElseGet(() -> categoryRepository.findByName(category).orElse(null));
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
}
