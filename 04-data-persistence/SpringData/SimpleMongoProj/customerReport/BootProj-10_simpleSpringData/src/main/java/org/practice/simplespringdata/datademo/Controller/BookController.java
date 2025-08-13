package org.practice.simplespringdata.datademo.Controller;


import org.practice.simplespringdata.datademo.Book;
import org.practice.simplespringdata.datademo.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }
    @GetMapping
    public List<Book> getAllBooks(){
        return this.bookService.findAllBooks();
    }
    @GetMapping("/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author){
        return this.bookService.findByAuthor(author);
    }

    @GetMapping("/published-after/{year}")
    public List<Book> getBooksByPublishedAfter(@PathVariable int year){
        return bookService.findBooksByPublishedYear(year);
    }

    @GetMapping("/searchBook{id}")
    public Book getBookById(@PathVariable Long id){
        return bookService.findBookById(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBookById(id);
    }
    @PutMapping("/updateBook/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id){
        return bookService.updateBookById(book, id);
    }
}
