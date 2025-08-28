package com.example.demo.service.impl;

import com.example.demo.dto.CategoryCreateRequest;
import com.example.demo.dto.CategoryResponse;
import com.example.demo.dto.CategoryUpdateRequest;
import com.example.demo.entity.Category;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of CategoryService for category operations
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAllWithBooks();
        return categories.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findByIdWithBooks(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        return convertToResponse(category);
    }
    
    @Override
    public CategoryResponse createCategory(CategoryCreateRequest request) {
        // Check if category name already exists
        if (categoryRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Category with name '" + request.getName() + "' already exists");
        }
        
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        
        Category savedCategory = categoryRepository.save(category);
        return convertToResponse(savedCategory);
    }
    
    @Override
    public CategoryResponse updateCategory(Long id, CategoryUpdateRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        
        // Check if the new name already exists for another category
        if (!category.getName().equals(request.getName()) && 
            categoryRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Category with name '" + request.getName() + "' already exists");
        }
        
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        
        Category updatedCategory = categoryRepository.save(category);
        return convertToResponse(updatedCategory);
    }
    
    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        
        // Check if category has associated books
        Long bookCount = categoryRepository.countBooksByCategoryId(id);
        if (bookCount > 0) {
            throw new IllegalStateException("Cannot delete category with id " + id + " because it has " + bookCount + " associated books");
        }
        
        categoryRepository.delete(category);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> searchCategoriesByName(String name) {
        List<Category> categories = categoryRepository.findByNameContainingIgnoreCase(name);
        return categories.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    private CategoryResponse convertToResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        response.setCreatedAt(category.getCreatedAt());
        response.setUpdatedAt(category.getUpdatedAt());
        
        // Count books for this category
        Long bookCount = categoryRepository.countBooksByCategoryId(category.getId());
        response.setBookCount(bookCount);
        
        return response;
    }
}
