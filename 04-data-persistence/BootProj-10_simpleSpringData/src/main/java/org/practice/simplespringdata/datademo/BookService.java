package org.practice.simplespringdata.datademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> findBooksByPublishedYear(Integer year) {
        return bookRepository.findByPublicationYearGreaterThan(year);
    }
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Book not found for this id" + id)
        );
    }
    public Book updateBookById(Book books,Long id) {
        Book book = findBookById(id);
        book.setTitle(books.getTitle());
        book.setAuthor(books.getAuthor());
        book.setPublicationYear(books.getPublicationYear());
        return bookRepository.save(book);
    }
}
