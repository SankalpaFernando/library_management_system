package com.example.SubscriptionManagementSystem.Service;

import com.example.SubscriptionManagementSystem.Configuration.JWTUtil;
import com.example.SubscriptionManagementSystem.DTO.User.CreateUserDTO;
import com.example.SubscriptionManagementSystem.Entity.User;
import com.example.SubscriptionManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        Optional<User> userdetails = userRepository.findByEmail(email);
        User user = userdetails.orElseThrow(()-> new UsernameNotFoundException("No User Found!"));

        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail()).password(user.getPassword()).build();
    }

    public void createUser(CreateUserDTO createUserDTO){
        User user = new User(
                createUserDTO.getFirstName(),
                createUserDTO.getLastName(),
                createUserDTO.getEmail(),
                passwordEncoder.encode(createUserDTO.getPassword()),
                createUserDTO.getMobileNo(),
                createUserDTO.getRole()
        );
        User savedUser = userRepository.save(user);
    }


}
