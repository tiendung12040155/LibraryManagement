package com.example.demo.repository;

import com.example.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    Optional<Category> findByName(String name);
    
    boolean existsByName(String name);
    
    List<Category> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.books")
    List<Category> findAllWithBooks();
    
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.books WHERE c.id = :id")
    Optional<Category> findByIdWithBooks(@Param("id") Long id);
    
    @Query("SELECT COUNT(b) FROM Category c LEFT JOIN c.books b WHERE c.id = :categoryId")
    Long countBooksByCategoryId(@Param("categoryId") Long categoryId);
}
