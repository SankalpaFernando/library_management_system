package com.example.SubscriptionManagementSystem.Configuration;

import com.example.SubscriptionManagementSystem.Entity.Book;
import com.example.SubscriptionManagementSystem.Enum.BookCategory;
import com.example.SubscriptionManagementSystem.Repository.BookRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;
    private final Faker faker = new Faker();

    public void run(String... args){
        System.out.println(bookRepository.count());
        if(bookRepository.count()<50){
            for(int i=0;i<50;i++){
                Book book = new Book();
                book.setISBN((faker.number().randomNumber(10, true)));
                book.setName(faker.book().title());
                book.setPublishedYear(faker.number().numberBetween(1900, 2025));
                book.setGenre(faker.book().genre());
                book.setCopiesAvailable(faker.number().numberBetween(1, 20));
                BookCategory[] categories = BookCategory.values();
                book.setBookCategory(categories[faker.random().nextInt(0,categories.length-1)]);

                bookRepository.save(book);
            }
        }
    }
}
