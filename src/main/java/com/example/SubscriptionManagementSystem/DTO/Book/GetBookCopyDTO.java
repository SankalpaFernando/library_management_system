package com.example.SubscriptionManagementSystem.DTO.Book;

import com.example.SubscriptionManagementSystem.Enum.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GetBookCopyDTO {
    private UUID id;
    private Long isbn;
    private String name;
    private BookStatus status;
}