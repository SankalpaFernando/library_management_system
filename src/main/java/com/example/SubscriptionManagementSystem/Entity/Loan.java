package com.example.SubscriptionManagementSystem.Entity;

import com.example.SubscriptionManagementSystem.Enum.LoanStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date issued_date;
    private Date due_date;
    private Date return_date;
    @NotNull
    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;

    @ManyToOne
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    @OneToOne
    @JoinColumn(name = "book_copy_id",nullable = false)
    private BookCopy bookCopy;
}
