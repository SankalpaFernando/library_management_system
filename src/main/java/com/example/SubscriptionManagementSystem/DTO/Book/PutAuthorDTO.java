package com.example.SubscriptionManagementSystem.DTO.Book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutAuthorDTO {
    private Long ISBN;
    private Long author_id;
}
