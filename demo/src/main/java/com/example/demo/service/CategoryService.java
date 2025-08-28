package com.example.demo.service;

import com.example.demo.dto.CategoryCreateRequest;
import com.example.demo.dto.CategoryResponse;
import com.example.demo.dto.CategoryUpdateRequest;

import java.util.List;

/**
 * Service interface for category operations
 */
public interface CategoryService {
    
    /**
     * Get all categories
     * @return list of all categories
     */
    List<CategoryResponse> getAllCategories();
    
    /**
     * Get category by ID
     * @param id category ID
     * @return category details
     */
    CategoryResponse getCategoryById(Long id);
    
    /**
     * Create a new category
     * @param request category creation data
     * @return created category details
     */
    CategoryResponse createCategory(CategoryCreateRequest request);
    
    /**
     * Update an existing category
     * @param id category ID
     * @param request category update data
     * @return updated category details
     */
    CategoryResponse updateCategory(Long id, CategoryUpdateRequest request);
    
    /**
     * Delete a category
     * @param id category ID
     */
    void deleteCategory(Long id);
    
    /**
     * Search categories by name
     * @param name category name to search
     * @return list of categories matching the name
     */
    List<CategoryResponse> searchCategoriesByName(String name);
}
