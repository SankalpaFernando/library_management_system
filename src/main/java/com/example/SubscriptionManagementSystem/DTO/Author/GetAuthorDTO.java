package com.example.SubscriptionManagementSystem.DTO.Author;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GetAuthorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer book_count;
}
