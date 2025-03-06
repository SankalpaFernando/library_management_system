package com.example.SubscriptionManagementSystem.Service;

import com.example.SubscriptionManagementSystem.DTO.Author.UpdateAuthorDTO;
import com.example.SubscriptionManagementSystem.DTO.Book.*;
import com.example.SubscriptionManagementSystem.DTO.BookCopy.UpdateBookCopyDTO;
import com.example.SubscriptionManagementSystem.DTO.PageResponse;
import com.example.SubscriptionManagementSystem.Entity.Author;
import com.example.SubscriptionManagementSystem.Entity.Book;
import com.example.SubscriptionManagementSystem.Entity.BookCopy;
import com.example.SubscriptionManagementSystem.Enum.BookStatus;
import com.example.SubscriptionManagementSystem.Repository.AuthorRepository;
import com.example.SubscriptionManagementSystem.Repository.BookCopyRepository;
import com.example.SubscriptionManagementSystem.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookCopyRepository bookCopyRepository;

    public void addBook(CreateBookDTO createBookDTO){
        System.out.println(createBookDTO.getBookCategory());
        Book book = new Book(createBookDTO.getISBN(),createBookDTO.getName(),createBookDTO.getPublishedYear(),createBookDTO.getGenre(),createBookDTO.getCopiesAvailable(),createBookDTO.getBookCategory());
        createBookDTO.getAuthorIDs().forEach(id->{
            Author author = authorRepository.findById(id).orElseThrow();
            book.getAuthors().add(author);
        });
        bookRepository.save(book);
    }

    public void updateBook(CreateBookDTO createBookDTO){
        Book book = bookRepository.findById(createBookDTO.getISBN()).orElseThrow();
        book.setName(createBookDTO.getName());
        book.setGenre(createBookDTO.getGenre());
        book.setBookCategory(createBookDTO.getBookCategory());
        bookRepository.save(book);
    }

    public void addAuthorToBook(UpdateAuthorDTO updateAuthorDTO){
        Book book = bookRepository.findById(updateAuthorDTO.getISBN()).orElseThrow();
        Author author = authorRepository.findById(updateAuthorDTO.getAuthor_id()).orElseThrow();
        book.getAuthors().add(author);
        bookRepository.save(book);
    }

    public void removeAuthorToBook(UpdateAuthorDTO updateAuthorDTO){
        Book book = bookRepository.findById(updateAuthorDTO.getISBN()).orElseThrow();
        Author author = authorRepository.findById(updateAuthorDTO.getAuthor_id()).orElseThrow();
        book.getAuthors().remove(author);
        bookRepository.save(book);
    }

    public List<BookDTO> getBookByID(Long ISBN){
        Book book = bookRepository.findById(ISBN).orElseThrow();
        return bookRepository.getBooksWithAuthors();
    }

    public PageResponse<GetBookDTO> getBooksByName(String search,int page,int size){
        Pageable pageable = PageRequest.of(page,size);
        return new PageResponse<>(bookRepository.searchBooks(search,pageable).map(book -> new GetBookDTO(book.getISBN(),book.getName(),book.getPublishedYear(),book.getGenre(),book.getBookCopies().stream().filter(b->b.getStatus()==BookStatus.AVAILABLE).count(),book.getBookCopies().size(),book.getBookCategory(),
                book.getAuthors().stream().collect(Collectors.toMap(
                        author -> author.getId(),
                        author -> author.getFirstName() + " " + author.getLastName()  // Key: Full Name

                ))

                )));

    }

    public void deleteBook(Long ISBN){
        Book book = bookRepository.findById(ISBN).orElseThrow();
        bookRepository.delete(book);
    }

    public void createBookCopy(Long ISBN){
        Book book = bookRepository.findById(ISBN).orElseThrow();
        BookCopy bookCopy = new BookCopy();
        bookCopy.setBook(book);
        bookCopy.setStatus(BookStatus.AVAILABLE);
        bookCopyRepository.save(bookCopy);
    }

    public PageResponse<GetBookCopyDTO> getBookCopies(String search, int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return new PageResponse<>(bookCopyRepository.searchBooks(search,pageable).map(bc -> new GetBookCopyDTO(bc.getId(),bc.getBook().getISBN(),bc.getBook().getName(),bc.getStatus())));
    }

    public List<Book> getBookList(){
        return  bookRepository.findAll();
    }

    public void updateBookStatus(UpdateBookCopyDTO updateBookCopyDTO){
        BookCopy bookCopy = bookCopyRepository.findById(updateBookCopyDTO.getId()).orElseThrow();
        bookCopy.setStatus(updateBookCopyDTO.getStatus());
        bookCopyRepository.save(bookCopy);

    }

    public void deleteBookCopy(UUID id){
        bookCopyRepository.deleteById(id);

    }
}
