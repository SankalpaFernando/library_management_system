package com.example.SubscriptionManagementSystem.DTO.Author;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAuthorDTO {
    @NotBlank(message = "First Name Can't Be Blank")
    private String firstName;
    @NotBlank(message = "Last Name Can't Be Blank")
    private String lastName;
}
