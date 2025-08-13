package com.example.demo1.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.demo1.entity.Book;

public interface BookRepo extends JpaRepository<Book, Long> {
    // custom methods
    List<Book> findByAuthor(String Author);
    List<Book> findByPublicationYearGreaterThan(Integer year);
}