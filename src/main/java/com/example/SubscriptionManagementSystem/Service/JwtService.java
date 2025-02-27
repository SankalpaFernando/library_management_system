package com.example.SubscriptionManagementSystem.Service;

import com.example.SubscriptionManagementSystem.Configuration.JWTUtil;
import com.example.SubscriptionManagementSystem.DTO.User.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JWTUtil jwtUtil;

    public String loginUser(UserLoginDTO userLoginDTO){
        authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(),userLoginDTO.getPassword()));
        return jwtUtil.generateToken(userLoginDTO.getEmail());
    }


}
