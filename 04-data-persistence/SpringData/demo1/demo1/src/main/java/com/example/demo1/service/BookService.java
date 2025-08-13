package com.example.demo1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo1.Repo.BookRepo;
import com.example.demo1.entity.Book;

@Service
public class BookService {

    private final BookRepo repo;
    @Autowired
    public BookService(BookRepo repo) {
        this.repo = repo;
    }

    public Book findBookById(Long id) {
        return repo.findById(id).orElseThrow(()-> new IllegalArgumentException("Book With Id "+id+"Not Found "));
    }

    public Book uploadBook(Book book) {
        return repo.save(book);
    }

    public List<Book> findBookByAuthor(String author) {
        return repo.findByAuthor(author);
    }

    public List<Book> findBooksPublishedAfter(Integer year) {
        return repo.findByPublicationYearGreaterThan(year);
    }

    public List<Book> findAllBooks() {
        return repo.findAll();
    }
}
