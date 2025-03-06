package com.example.SubscriptionManagementSystem.Controller;

import com.example.SubscriptionManagementSystem.DTO.Author.UpdateAuthorDTO;
import com.example.SubscriptionManagementSystem.DTO.Book.*;
import com.example.SubscriptionManagementSystem.DTO.BookCopy.UpdateBookCopyDTO;
import com.example.SubscriptionManagementSystem.DTO.PageResponse;
import com.example.SubscriptionManagementSystem.Entity.Book;
import com.example.SubscriptionManagementSystem.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @PutMapping
    public ResponseEntity<Void> updateBook(@Valid @RequestBody CreateBookDTO createBookDTO){
        bookService.updateBook(createBookDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/author/add")
    public ResponseEntity<Void> addAuthor(@Valid @RequestBody UpdateAuthorDTO updateAuthorDTO){
        bookService.addAuthorToBook(updateAuthorDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/copy/add/")
    public ResponseEntity<Void> createCopy(@RequestParam Long ISBN){
        System.out.println(ISBN);
        bookService.createBookCopy(ISBN);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/isbn")
    public List<BookDTO> getBookByID(@RequestParam Long ISBN){
        return bookService.getBookByID(ISBN);
    }

    @GetMapping("/")
    public PageResponse<GetBookDTO> getBooksByName(
            @RequestParam String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return bookService.getBooksByName(search,page,size);
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteBook(@RequestParam Long ISBN){
        this.bookService.deleteBook(ISBN);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/copy")
    public PageResponse<GetBookCopyDTO> getBookCopies(
            @RequestParam String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return bookService.getBookCopies(search,page,size);
    }

    @GetMapping("/list")
    public List<Book> getBookList(){
        return bookService.getBookList();
    }

    @PutMapping("/copy/status")
    public ResponseEntity<Void> updateStatus(@Valid @RequestBody UpdateBookCopyDTO bookCopyDTO){
        bookService.updateBookStatus(bookCopyDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/copy")
    public ResponseEntity<Void> deleteBookCopy(@RequestParam UUID id){
        bookService.deleteBookCopy(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
