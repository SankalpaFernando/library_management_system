package com.example.SubscriptionManagementSystem.Service;

import com.example.SubscriptionManagementSystem.DTO.Author.AuthorDTO;
import com.example.SubscriptionManagementSystem.DTO.Author.CreateAuthorDTO;
import com.example.SubscriptionManagementSystem.DTO.Author.GetAuthorDTO;
import com.example.SubscriptionManagementSystem.DTO.PageResponse;
import com.example.SubscriptionManagementSystem.Entity.Author;
import com.example.SubscriptionManagementSystem.Exception.ResourceNotFoundException;
import com.example.SubscriptionManagementSystem.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public PageResponse<GetAuthorDTO> getAuthors(String search,int page ,int size){
        Pageable pageable = PageRequest.of(page,size);
        return new PageResponse<>(authorRepository.searchAuthors(search,pageable).map(author->new GetAuthorDTO(author.getId(),author.getFirstName(),author.getLastName(),author.getBooks().size())));
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
        author.removeBooks();
        authorRepository.delete(author);
        return authorID;
    }

    public List<AuthorDTO> listAuthors(){
        return authorRepository.findAll().stream().map(author -> new AuthorDTO(author.getId(),author.getFirstName(),author.getLastName())).toList();
    }

}
