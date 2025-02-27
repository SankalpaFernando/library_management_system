package com.example.SubscriptionManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    public Author(String firstName,String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();
}
