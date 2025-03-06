package com.example.SubscriptionManagementSystem.Repository;

import com.example.SubscriptionManagementSystem.DTO.Book.BookDTO;
import com.example.SubscriptionManagementSystem.Entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    @Query("SELECT new com.example.SubscriptionManagementSystem.DTO.Book.BookDTO(b.name, a.firstName) FROM Book b JOIN b.authors a")
    List<BookDTO> getBooksWithAuthors();
    @Query(value="SELECT * FROM book b WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :search, '%')) OR CAST(b.ISBN AS CHAR) LIKE LOWER(CONCAT('%', :search, '%'))",nativeQuery = true)
    Page<Book> searchBooks(@Param("search") String search, Pageable pageable);
}
