package com.example.SubscriptionManagementSystem.DTO.User;

import com.example.SubscriptionManagementSystem.Enum.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CreateUserDTO {
    @NotBlank(message = "First Name Can't Be Blank")
    String firstName;
    @NotBlank(message = "Last Name Can't Be Blank")
    String lastName;
    @NotBlank(message = "Email Can't Be Blank")
    @Email(message = "Invalid Email")
    String email;
    @NotNull(message = "Role Can't Be Blank")
    Role role;
    @NotBlank(message = "Mobile Number Can't Be Blank")
    String mobileNo;
    @NotBlank(message = "Password can't be blank")
    @Size(min=6,message = "Password must be at least 6 characters")
    String password;
}

