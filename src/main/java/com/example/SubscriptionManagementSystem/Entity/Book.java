package com.example.SubscriptionManagementSystem.Entity;

import com.example.SubscriptionManagementSystem.Enum.BookCategory;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "isbn")
public class Book {
    @Id
    private Long ISBN;
    private String name;
    private Integer publishedYear;
    private String genre;
    private Integer copiesAvailable;
    @Enumerated(EnumType.STRING)
    private BookCategory bookCategory;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "isbn_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private Set<BookCopy> bookCopies = new HashSet<>();

    public Book(Long ISBN,String name,Integer publishedYear,String genre,Integer copiesAvailable,BookCategory bookCategory){
        this.ISBN = ISBN;
        this.name = name;
        this.publishedYear = publishedYear;
        this.genre = genre;
        this.copiesAvailable = copiesAvailable;
        this.bookCategory = bookCategory;
    }
}
