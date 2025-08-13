package com.example.demo1.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.entity.Book;
import com.example.demo1.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/findBook/{id}")
    public Book getBooksById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        return bookService.uploadBook(book);
    }

    @GetMapping("/findBook/author")
    public List<Book> findBookByAuthor(@RequestParam String author) {
        return bookService.findBookByAuthor(author);
    }

    @GetMapping("/findBook/publishedYear/{year}")
    public List<Book> findBooksByPublishedYear(@PathVariable Integer year) {
        return bookService.findBooksPublishedAfter(year);
    }
}
