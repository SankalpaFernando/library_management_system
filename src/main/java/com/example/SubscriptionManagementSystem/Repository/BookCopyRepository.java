package com.example.SubscriptionManagementSystem.Repository;

import com.example.SubscriptionManagementSystem.Entity.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookCopyRepository extends JpaRepository<BookCopy, UUID> {
}
