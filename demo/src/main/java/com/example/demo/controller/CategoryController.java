package com.example.demo.controller;

import com.example.demo.dto.CategoryCreateRequest;
import com.example.demo.dto.CategoryResponse;
import com.example.demo.dto.CategoryUpdateRequest;
import com.example.demo.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryController {
    
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        CategoryResponse category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('LIBRARIAN') or hasRole('ADMIN')")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryCreateRequest request) {
        CategoryResponse createdCategory = categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN') or hasRole('ADMIN')")
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryUpdateRequest request) {
        CategoryResponse updatedCategory = categoryService.updateCategory(id, request);
        return ResponseEntity.ok(updatedCategory);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<CategoryResponse>> searchCategoriesByName(@RequestParam String name) {
        List<CategoryResponse> categories = categoryService.searchCategoriesByName(name);
        return ResponseEntity.ok(categories);
    }
}
