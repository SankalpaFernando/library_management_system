package com.example.SubscriptionManagementSystem.Entity;

import com.example.SubscriptionManagementSystem.Enum.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.beans.ConstructorProperties;
import java.time.Instant;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private String mobileNo;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String password;
    @CreatedDate
    private Instant createdAt;

    public User(String firstName, String lastName, String email,String password,String mobileNo, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.mobileNo = mobileNo;
        this.role = role;
    }
}
