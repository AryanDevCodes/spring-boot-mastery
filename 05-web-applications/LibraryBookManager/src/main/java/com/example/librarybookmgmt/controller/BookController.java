package com.example.librarybookmgmt.controller;

import com.example.librarybookmgmt.model.Book;
import com.example.librarybookmgmt.model.Status;
import com.example.librarybookmgmt.service.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String listBooks(@RequestParam(value = "q",required = false) String q, Model model) {
        model.addAttribute("books",(q==null || q.isBlank())?bookService.findAll():bookService.searchByTitle(q));
        model.addAttribute("statusValues", Status.values());
        return "list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book, BindingResult br) {
        if (bookService.isbnExist(book.getIsbn())) {
            br.rejectValue("isbn","isbn.duplicate","ISBN ALREADY EXISTS");
        }
        if (br.hasErrors()) {
            return "add";
        }
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Book b = bookService.findById(id).orElseThrow(()-> new IllegalArgumentException("Book not found or Invalid Book ID "+ id));
        model.addAttribute("book", b);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("book") Book book, BindingResult br) {
        if (br.hasErrors()) return "edit";
        book.setId(id);
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }

    @PostMapping("/borrow/{id}")
    public String borrow(@PathVariable Long id) {
        bookService.borrow(id);
        return "redirect:/books";
    }

    @PostMapping("/return/{id}")
    public String returnBook(@PathVariable Long id) {
        bookService.returnBook(id);
        return "redirect:/books";
    }
}
