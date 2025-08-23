package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    
    @Column(name = "title", nullable = false, length = 255)
    private String title;
    
    @Column(name = "isbn", unique = true, length = 20)
    private String isbn;
    
    @Column(name = "publisher", length = 255)
    private String publisher;
    
    @Column(name = "published_date")
    private LocalDate publishedDate;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "language", length = 50)
    private String language;

    @Column(name = "borrowerId")
    private Long borrowerId;
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Relationships
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "book_category",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
} 