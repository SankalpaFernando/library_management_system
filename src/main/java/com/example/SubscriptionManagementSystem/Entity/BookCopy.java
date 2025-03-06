package com.example.SubscriptionManagementSystem.Entity;

import com.example.SubscriptionManagementSystem.Enum.BookStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class BookCopy {
    @Id
    @GeneratedValue
    private UUID id;
    @Enumerated(EnumType.STRING)
    private BookStatus status;
    @ManyToOne
    @JoinColumn(nullable = false,name = "isbn_id")
    private Book book;

}
