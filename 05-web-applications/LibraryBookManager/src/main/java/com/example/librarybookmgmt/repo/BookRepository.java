package com.example.librarybookmgmt.repo;

import com.example.librarybookmgmt.model.Book;
import com.example.librarybookmgmt.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByStatus(Status status);
    List<Book> findByTitleContainingIgnoreCase(String q);
}
