package com.example.SubscriptionManagementSystem.Service;

import com.example.SubscriptionManagementSystem.DTO.Author.AuthorDTO;
import com.example.SubscriptionManagementSystem.DTO.Author.CreateAuthorDTO;
import com.example.SubscriptionManagementSystem.Entity.Author;
import com.example.SubscriptionManagementSystem.Exception.ResourceNotFoundException;
import com.example.SubscriptionManagementSystem.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public void createAuthor(CreateAuthorDTO createAuthorDTO){
        Author author = new Author(
                createAuthorDTO.getFirstName(),
                createAuthorDTO.getLastName()
        );
        authorRepository.save(author);
    }

    public List<AuthorDTO> getAuthors(){
        return authorRepository.findAll().stream().map(author -> new AuthorDTO(author.getId(),author.getFirstName(),author.getLastName())).collect(Collectors.toList());
    }

    public AuthorDTO updateAuthor(AuthorDTO authorDTO){
        Long userID = authorDTO.getId();
        Author author = authorRepository.findById(userID).orElseThrow(()->new ResourceNotFoundException("Author not found"));
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        authorRepository.save(author);
        return authorDTO;
    }

    public Long deleteAuthor(Long authorID){
        Author author = authorRepository.findById(authorID).orElseThrow(()-> new ResourceNotFoundException("Author not found with AuthorID: "+authorID));
        authorRepository.deleteById(authorID);
        return authorID;
    }

}
