package com.example.librarybookmgmt.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Title is Required")
    @Size(max = 200)
    private String title;

    @NotBlank(message = "Author is Required")
    @Size(max = 120)
    private String author;

    @Column(unique = true)
    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "^[0-9\\-]+$",message = "ISBN must contain only numbers and hyphens")
    private String isbn;

    @Enumerated(EnumType.STRING)
    private Status status=Status.Available;

    private LocalDateTime creationDate = LocalDateTime.now();

    public Book(Long id, String title, String author, String isbn, Status status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.status = status;
    }

    public Book() {

    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", status=" + status +
                ", creationDate=" + creationDate +
                '}';
    }
}
