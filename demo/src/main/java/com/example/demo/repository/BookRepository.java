package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByIsbn(String isbn);

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.author LEFT JOIN FETCH b.categories WHERE b.id = :id")
    Optional<Book> findByIdWithAuthorAndCategories(@Param("id") Long id);

    /**
     * Find all books borrowed by a specific user
     *
     * @param borrowerId ID of the user who borrowed the books
     * @return List of books borrowed by the user
     */
    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.author LEFT JOIN FETCH b.categories WHERE b.borrowerId = :borrowerId")
    List<Book> findByBorrowerIdWithAuthorAndCategories(@Param("borrowerId") Long borrowerId);
}


