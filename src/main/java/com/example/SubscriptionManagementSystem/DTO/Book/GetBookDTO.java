package com.example.SubscriptionManagementSystem.DTO.Book;

import com.example.SubscriptionManagementSystem.Entity.Author;
import com.example.SubscriptionManagementSystem.Entity.BookCopy;
import com.example.SubscriptionManagementSystem.Enum.BookCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GetBookDTO {
     private Long ISBN;
     private String name;
     private Integer publishedYear;
     private String genre;
     private Long copiesAvailable;
     private Integer totalCopies;
     private BookCategory bookCategory;
     private Map<Long,String> authors;
}
