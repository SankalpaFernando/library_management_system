package com.example.SubscriptionManagementSystem.DTO.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserLoginDTO {
    private String email;
    private String password;
}
