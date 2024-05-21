package dev.university.project.controller;

import dev.university.project.model.Category;
import dev.university.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }
    @GetMapping("/{category}")
    public ResponseEntity<Category> getCategoryByNameOrId(String category) {
        return ResponseEntity.ok(categoryService.getCategoryByNameOrId(category));
    }
    @PostMapping
    public ResponseEntity<Category> createCategory(Category category) {
        return ResponseEntity.ok(categoryService.createCategory(category));
    }
}
