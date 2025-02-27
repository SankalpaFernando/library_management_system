package com.example.SubscriptionManagementSystem.Controller;

import com.example.SubscriptionManagementSystem.DTO.Book.BookDTO;
import com.example.SubscriptionManagementSystem.DTO.Book.CreateBookCopyDTO;
import com.example.SubscriptionManagementSystem.DTO.Book.CreateBookDTO;
import com.example.SubscriptionManagementSystem.DTO.Book.PutAuthorDTO;
import com.example.SubscriptionManagementSystem.Entity.Book;
import com.example.SubscriptionManagementSystem.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/")
    public ResponseEntity<Void> createBook(@Valid @RequestBody CreateBookDTO createBookDTO){
        bookService.addBook(createBookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateBook(@Valid @RequestBody CreateBookDTO createBookDTO){
        bookService.updateBook(createBookDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/author/add")
    public ResponseEntity<Void> addAuthor(@Valid @RequestBody PutAuthorDTO putAuthorDTO){
        bookService.addAuthorToBook(putAuthorDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/copy")
    public ResponseEntity<Void> createCopy(@RequestParam Long ISBN){
        bookService.createBookCopy(ISBN);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public List<BookDTO> getBookByID(@RequestParam Long ISBN){
        return bookService.getBookByID(ISBN);
    }


}
