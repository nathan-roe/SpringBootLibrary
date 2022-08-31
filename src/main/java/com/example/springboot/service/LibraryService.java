package com.example.springboot.service;

import com.example.springboot.model.Book;
import com.example.springboot.model.Librarian;
import com.example.springboot.model.Library;
import com.example.springboot.repository.BookRepository;
import com.example.springboot.repository.LibrarianRepository;
import com.example.springboot.repository.LibraryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LibraryService {
    Logger logger = LoggerFactory.getLogger(LibraryService.class);
    private final LibraryRepository libraryRepository;
    private final BookRepository bookRepository;
    private final LibrarianRepository librarianRepository;


    public LibraryService(LibraryRepository libraryRepository, BookRepository bookRepository,
                          LibrarianRepository librarianRepository) {
        this.libraryRepository = libraryRepository;
        this.bookRepository = bookRepository;
        this.librarianRepository = librarianRepository;
    }

    public List<Book> addBook(Long id, Book book) {
        Library library = libraryRepository.findById(id).get();
        List<Book> books = library.getBooks();
        books.add(book);
        library.setBooks(books);
        book.setLibrary(library);
        libraryRepository.save(library);
        bookRepository.save(book);
        return library.getBooks();
    }

    public void removeBook(Long librarianId, Long bookId) throws IllegalAccessException {
        Librarian librarian = librarianRepository.findById(librarianId).get();
        Book book = bookRepository.findById(bookId).get();
        if(librarian.getLibrary().equals(book.getLibrary())) {
            bookRepository.delete(book);
        } else {
            throw new IllegalAccessException();
        }
    }
}
