package com.example.SubscriptionManagementSystem.Controller;

import com.example.SubscriptionManagementSystem.DTO.Author.AuthorDTO;
import com.example.SubscriptionManagementSystem.DTO.Author.CreateAuthorDTO;
import com.example.SubscriptionManagementSystem.DTO.Author.GetAuthorDTO;
import com.example.SubscriptionManagementSystem.DTO.PageResponse;
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

    @PostMapping
    public ResponseEntity<Void> createAuthor(@Valid @RequestBody CreateAuthorDTO createAuthorDTO){
        authorService.createAuthor(createAuthorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public PageResponse<GetAuthorDTO> getAuthors(
            @RequestParam String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return authorService.getAuthors(search,page,size);
    }

    @PutMapping
    public ResponseEntity<AuthorDTO> updateAuthor(@Valid @RequestBody AuthorDTO authorDTO){
        AuthorDTO updatedAuthorDTO = authorService.updateAuthor(authorDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedAuthorDTO);
    }

    @DeleteMapping("{author_id}")
    public Long deleteAuthor(@PathVariable("author_id") Long authorID){
        System.out.println(authorID);
        return authorService.deleteAuthor(authorID);
    }

    @GetMapping("list/")
    public List<AuthorDTO> listAuthors(){
        return authorService.listAuthors();
    }

}

