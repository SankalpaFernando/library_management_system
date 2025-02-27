package com.example.SubscriptionManagementSystem.DTO.Book;

import com.example.SubscriptionManagementSystem.Entity.Author;
import com.example.SubscriptionManagementSystem.Enum.BookCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateBookDTO {
    private Long ISBN;
    private String name;
    private Integer publishedYear;
    private String genre;
    private Integer copiesAvailable;
    private BookCategory bookCategory;
    private List<Long> authorIDs;
}
