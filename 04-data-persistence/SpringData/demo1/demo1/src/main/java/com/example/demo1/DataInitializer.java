package com.example.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo1.Repo.BookRepo;
import com.example.demo1.entity.Book;

@Component
public class DataInitializer implements CommandLineRunner {
    private final BookRepo bookRepo;

    @Autowired
    public DataInitializer(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Book book1 = new Book();
        book1.setTitle("Healing Health");
        book1.setAuthor("Ramdev");
        book1.setPublicationYear(2005);
        bookRepo.save(book1);
        Book book2 = new Book();
        book2.setTitle("Java Application management");
        book2.setAuthor("Natrajan");
        book2.setPublicationYear(2015);
        bookRepo.save(book2);

        Book book3 = new Book();
        book3.setTitle("Introduction to Networking");
        book3.setAuthor("Girdhari");
        book3.setPublicationYear(1990);
        bookRepo.save(book3);

        Book book4 = new Book();
        book4.setTitle("Application Deployement");
        book4.setAuthor("Lilawani");
        book4.setPublicationYear(2018);
        bookRepo.save(book4);
    }

}
