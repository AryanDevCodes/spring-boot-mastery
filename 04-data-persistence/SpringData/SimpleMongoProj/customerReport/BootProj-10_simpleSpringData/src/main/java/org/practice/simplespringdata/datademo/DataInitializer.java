package org.practice.simplespringdata.datademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    public final BookRepository bookRepository;
    @Autowired
    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
    Book book1 = new Book();
    book1.setTitle("Spring Data");
    book1.setAuthor("Jack Bauer");
    book1.setPublicationYear(1925);
    bookRepository.save(book1);

    Book book2 = new Book();
    book2.setTitle("Spring Data2");
    book2.setAuthor("Jack Ryan");
    book2.setPublicationYear(1940);
    bookRepository.save(book2);

    Book book3 = new Book();
    book3.setTitle("Spring Data3");
    book3.setAuthor("Jack");
    book3.setPublicationYear(1950);
    bookRepository.save(book3);

    System.out.println("Sample data initialized! ");
    }
}
