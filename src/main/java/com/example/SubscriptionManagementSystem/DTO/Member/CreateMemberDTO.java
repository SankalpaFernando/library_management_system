package com.example.SubscriptionManagementSystem.DTO.Member;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateMemberDTO {
    private String firstName;
    private String lastName;
    private Date joinedDate;
    private String address;
    private String phoneNumber;
}
