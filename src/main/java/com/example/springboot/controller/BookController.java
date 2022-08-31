package com.example.springboot.controller;

import com.example.springboot.model.Book;
import com.example.springboot.repository.BookRepository;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id).get();
    }

}
