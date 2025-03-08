package com.example.SubscriptionManagementSystem.DTO.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@Getter
@Setter
public class UpdateMemberDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Date joinedDate;
    private String address;
    private String phoneNumber;
}
