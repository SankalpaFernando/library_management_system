package com.example.SubscriptionManagementSystem.Service;

import com.example.SubscriptionManagementSystem.DTO.Book.BookDTO;
import com.example.SubscriptionManagementSystem.DTO.Book.CreateBookDTO;
import com.example.SubscriptionManagementSystem.DTO.Book.PutAuthorDTO;
import com.example.SubscriptionManagementSystem.Entity.Author;
import com.example.SubscriptionManagementSystem.Entity.Book;
import com.example.SubscriptionManagementSystem.Entity.BookCopy;
import com.example.SubscriptionManagementSystem.Enum.BookStatus;
import com.example.SubscriptionManagementSystem.Repository.AuthorRepository;
import com.example.SubscriptionManagementSystem.Repository.BookCopyRepository;
import com.example.SubscriptionManagementSystem.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookCopyRepository bookCopyRepository;

    public void addBook(CreateBookDTO createBookDTO){
        Book book = new Book(createBookDTO.getISBN(),createBookDTO.getName(),createBookDTO.getPublishedYear(),createBookDTO.getGenre(),createBookDTO.getCopiesAvailable(),createBookDTO.getBookCategory());
        createBookDTO.getAuthorIDs().forEach(id->{
            Author author = authorRepository.findById(id).orElseThrow();
            book.getAuthors().add(author);
        });
        bookRepository.save(book);
    }

    public void createBookCopy(Long ISBN){
        Book book = bookRepository.findById(ISBN).orElseThrow();
        BookCopy bookCopy = new BookCopy();
        bookCopy.setBook(book);
        bookCopy.setStatus(BookStatus.AVAILABLE);
        bookCopyRepository.save(bookCopy);
    }

    public void updateBook(CreateBookDTO createBookDTO){
        Book book = bookRepository.findById(createBookDTO.getISBN()).orElseThrow();
        book.setName(createBookDTO.getName());
        book.setGenre(createBookDTO.getGenre());
        book.setBookCategory(createBookDTO.getBookCategory());
        bookRepository.save(book);
    }

    public void addAuthorToBook(PutAuthorDTO putAuthorDTO){
        Book book = bookRepository.findById(putAuthorDTO.getISBN()).orElseThrow();
        Author author = authorRepository.findById(putAuthorDTO.getAuthor_id()).orElseThrow();
        book.getAuthors().add(author);
        bookRepository.save(book);
    }

    public void removeAuthorToBook(PutAuthorDTO putAuthorDTO){
        Book book = bookRepository.findById(putAuthorDTO.getISBN()).orElseThrow();
        Author author = authorRepository.findById(putAuthorDTO.getAuthor_id()).orElseThrow();
        book.getAuthors().remove(author);
        bookRepository.save(book);
    }

    public List<BookDTO> getBookByID(Long ISBN){
        Book book = bookRepository.findById(ISBN).orElseThrow();
        return bookRepository.getBooksWithAuthors();
    }
}
