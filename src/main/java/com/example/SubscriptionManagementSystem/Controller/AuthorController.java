package com.example.SubscriptionManagementSystem.Controller;

import com.example.SubscriptionManagementSystem.DTO.Author.AuthorDTO;
import com.example.SubscriptionManagementSystem.DTO.Author.CreateAuthorDTO;
import com.example.SubscriptionManagementSystem.Entity.Author;
import com.example.SubscriptionManagementSystem.Service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/")
    public ResponseEntity<Void> createAuthor(@Valid @RequestBody CreateAuthorDTO createAuthorDTO){
        authorService.createAuthor(createAuthorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public List<AuthorDTO> getAuthors(){
        return authorService.getAuthors();
    }

    @PutMapping("/")
    public ResponseEntity<AuthorDTO> updateAuthor(@Valid @RequestBody AuthorDTO authorDTO){
        AuthorDTO updatedAuthorDTO = authorService.updateAuthor(authorDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedAuthorDTO);
    }

    @DeleteMapping("/")
    public Long deleteAuthor(@RequestParam("author_id") Long authorID){
        return authorService.deleteAuthor(authorID);
    }

}

