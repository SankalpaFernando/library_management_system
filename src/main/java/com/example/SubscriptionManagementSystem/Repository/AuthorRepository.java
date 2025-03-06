package com.example.SubscriptionManagementSystem.Repository;

import com.example.SubscriptionManagementSystem.Entity.Author;
import com.example.SubscriptionManagementSystem.Entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    @Query(value="SELECT a.*,COUNT(b.isbn) AS 'book_count' FROM author a LEFT JOIN book_author ba ON a.id=ba.author_id LEFT JOIN book b ON b.isbn=ba.isbn_id WHERE LOWER(a.first_name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(a.last_name) LIKE LOWER(CONCAT('%', :search, '%')) GROUP BY a.id, a.first_name, a.last_name  ",nativeQuery = true)
    Page<Author> searchAuthors(@Param("search") String search, Pageable pageable);
}
