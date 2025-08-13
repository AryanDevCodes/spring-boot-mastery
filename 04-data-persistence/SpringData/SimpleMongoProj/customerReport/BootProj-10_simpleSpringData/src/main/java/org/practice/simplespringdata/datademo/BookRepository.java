package org.practice.simplespringdata.datademo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String author);
    List<Book> findByPublicationYearGreaterThan(Integer year);
    List<Book> findBooksById(Long id);
}
