package com.example.SubscriptionManagementSystem.Controller;

import com.example.SubscriptionManagementSystem.Configuration.JWTUtil;
import com.example.SubscriptionManagementSystem.DTO.User.CreateUserDTO;
import com.example.SubscriptionManagementSystem.DTO.User.UserLoginDTO;
import com.example.SubscriptionManagementSystem.Service.JwtService;
import com.example.SubscriptionManagementSystem.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;


    @PostMapping("sign_up")
    public ResponseEntity<Void> createUser(@Valid  @RequestBody CreateUserDTO createUserDTO){
        userService.createUser(createUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public String loginUser(@Valid @RequestBody UserLoginDTO userLoginDTO){
       return jwtService.loginUser(userLoginDTO);
    }

    @GetMapping("/")
    public String getHello(){

        return "Hello";
    }

}
