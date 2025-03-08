package com.example.SubscriptionManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Date joinedDate;
    private String address;
    private String phoneNumber;

    public Member(String firstName,String lastName,Date joinedDate,String address,String phoneNumber){
        this.firstName=firstName;
        this.lastName=lastName;
        this.joinedDate=joinedDate;
        this.address=address;
        this.phoneNumber=phoneNumber;
    }

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Loan> loan;


}
