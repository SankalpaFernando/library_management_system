package com.example.SubscriptionManagementSystem.Repository;

import com.example.SubscriptionManagementSystem.Entity.Book;
import com.example.SubscriptionManagementSystem.Entity.BookCopy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface BookCopyRepository extends JpaRepository<BookCopy, UUID> {
    @Query(value="SELECT * FROM book_copy bc INNER JOIN book b ON bc.isbn_id=b.isbn WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :search, '%')) OR CAST(b.ISBN AS CHAR) LIKE LOWER(CONCAT('%', :search, '%')) OR bc.id = :search",nativeQuery = true)
    Page<BookCopy> searchBooks(@Param("search") String search, Pageable pageable);
}
