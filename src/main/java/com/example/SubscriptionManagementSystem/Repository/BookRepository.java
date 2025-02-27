package com.example.SubscriptionManagementSystem.Repository;

import com.example.SubscriptionManagementSystem.DTO.Book.BookDTO;
import com.example.SubscriptionManagementSystem.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Query("SELECT new com.example.SubscriptionManagementSystem.DTO.Book.BookDTO(b.name, a.firstName) FROM Book b JOIN b.authors a")
    List<BookDTO> getBooksWithAuthors();
}
