package com.example.springboot.controller;

import com.example.springboot.model.Book;
import com.example.springboot.model.Library;
import com.example.springboot.repository.LibraryRepository;
import com.example.springboot.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/libraries")
public class LibraryController {
    Logger logger = LoggerFactory.getLogger(LibraryController.class);
    private final LibraryRepository libraryRepository;
    private final LibraryService libraryService;

    public LibraryController(LibraryRepository libraryRepository, LibraryService libraryService) {
        this.libraryRepository = libraryRepository;
        this.libraryService = libraryService;
    }

    @GetMapping
    public List<Library> getLibraries(@RequestParam(value = "sort", defaultValue="id") String sort,
                                      @RequestParam(value = "order", defaultValue = "asc") String order,
                                      @RequestParam(value = "filter", defaultValue = "") String filter) {
        return libraryService.filterAndSort(sort, order, filter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Library> getLibrary(@PathVariable Long id) {
        return ResponseEntity.ok().body(libraryRepository.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<Library> createLibrary(@Validated @RequestBody Library library) {
        Library l = libraryRepository.save(library);
        return ResponseEntity.status(HttpStatus.CREATED).body(l);
    }

    @PostMapping("/{id}/add")
    public ResponseEntity<List<Book>> addBookToLibrary(@PathVariable Long id, @Validated @RequestBody Book book) {
        List<Book> updatedBooks = libraryService.addBook(id, book);
        return ResponseEntity.ok().body(updatedBooks);
    }

}
