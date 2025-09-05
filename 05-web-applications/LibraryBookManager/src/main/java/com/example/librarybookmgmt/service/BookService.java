package com.example.librarybookmgmt.service;

import com.example.librarybookmgmt.model.Book;
import com.example.librarybookmgmt.model.Status;
import com.example.librarybookmgmt.repo.BookRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void init() {
        bookRepository.save(new Book(null,"Effective Java", "Joshua Bloch", "978-0134685991", Status.Available));
        bookRepository.save(new Book(null,"Clean Code", "Robert C. Martin", "978-0132350884", Status.Borrowed));
        bookRepository.save(new Book(null,"Spring in Action", "Craig Walls", "978-1617294945", Status.Available));
        bookRepository.save(new Book(null,"Java Concurrency in Practice", "Brian Goetz", "978-0321349606", Status.Returned));
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
    public Book save(Book book) {
        return bookRepository.save(book);
    }
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public Optional<Book> borrow(Long id){
        Optional<Book> b = bookRepository.findById(id);
        b.ifPresent(book -> {
            if (book.getStatus()== Status.Available) book.setStatus(Status.Borrowed);
        });
        return b;
    }

    public Optional<Book> returnBook(Long id){
        Optional<Book> b = bookRepository.findById(id);
        b.ifPresent(book -> {
            if (book.getStatus()== Status.Available) book.setStatus(Status.Returned);
        });
        return b;
    }

    public boolean isbnExist(String isbn){
        return bookRepository.findByIsbn(isbn).isPresent();
    }

/*    public List<Book> findByStatus(String status){
        return bookRepository.findByTitleContainingIgnoreCase(status);
    }*/

    public List<Book> searchByTitle(String title){
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
}
