package com.example.demo.service;

import com.example.demo.dto.CategoryCreateRequest;
import com.example.demo.dto.CategoryResponse;
import com.example.demo.dto.CategoryUpdateRequest;

import java.util.List;

public interface CategoryService {
    
    List<CategoryResponse> getAllCategories();
    
    CategoryResponse getCategoryById(Long id);
    
    CategoryResponse createCategory(CategoryCreateRequest request);
    
    CategoryResponse updateCategory(Long id, CategoryUpdateRequest request);
    
    void deleteCategory(Long id);
    
    List<CategoryResponse> searchCategoriesByName(String name);
}
