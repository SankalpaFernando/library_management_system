package com.example.SubscriptionManagementSystem.DTO.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO {
    private String name;
    private String firstName;

    public BookDTO(String name,String firstname){
        this.name = name;
        this.firstName = firstname;

    }
}
