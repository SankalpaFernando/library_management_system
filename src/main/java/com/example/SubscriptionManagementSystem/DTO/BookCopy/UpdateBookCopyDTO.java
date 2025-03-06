package com.example.SubscriptionManagementSystem.DTO.BookCopy;

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
public class UpdateBookCopyDTO {
    private UUID id;
    private Long isbn;
    private BookStatus status;
}
