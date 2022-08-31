package com.example.springboot.controller;

import com.example.springboot.model.Book;
import com.example.springboot.model.Library;
import com.example.springboot.repository.BookRepository;
import com.example.springboot.model.Librarian;
import com.example.springboot.repository.LibrarianRepository;
import com.example.springboot.repository.LibraryRepository;
import com.example.springboot.service.LibrarianService;
import com.example.springboot.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/librarians")
public class LibrarianController {

    private final Logger logger = LoggerFactory.getLogger(LibrarianController.class);
    private final LibrarianRepository librarianRepository;
    private final LibraryRepository libraryRepository;
    private final BookRepository bookRepository;
    private final LibraryService libraryService;
    private final LibrarianService librarianService;

    public LibrarianController(LibrarianRepository librarianRepository, BookRepository bookRepository,
                               LibraryRepository libraryRepository, LibraryService libraryService,
                               LibrarianService librarianService) {
        this.librarianRepository = librarianRepository;
        this.bookRepository = bookRepository;
        this.libraryRepository = libraryRepository;
        this.libraryService = libraryService;
        this.librarianService = librarianService;
    }

    @PostMapping("/{librarianId}/book")
    public ResponseEntity<?> addBook(@PathVariable Long librarianId, @RequestBody Book book) {
        try {
            Librarian librarian = librarianRepository.findById(librarianId).get();
            book.setAddedBy(librarian);
            bookRepository.save(book);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch(Exception e) {
            logger.info("Error: " + e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping ("/{librarianId}/book/{bookId}/delete")
    public ResponseEntity<?> deleteBook(@PathVariable Long librarianId, @PathVariable Long bookId)
    throws IllegalAccessException {
        libraryService.removeBook(librarianId, bookId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{libraryId}")
    public ResponseEntity<?> createLibrarian(@RequestBody Librarian librarian,
                                             @PathVariable Long libraryId) throws ClassNotFoundException {
        List<Librarian> librarians = librarianService.addLibrarian(libraryId, librarian);
        logger.info("Librarians: " + String.join(", ", librarians.stream().map(l ->
                l.getEmail()).collect(Collectors.toList())));
        return ResponseEntity.status(HttpStatus.CREATED).body(librarians);
    }
}
