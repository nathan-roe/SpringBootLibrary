package com.example.springboot.service;

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
public class LibrarianService {
    Logger logger = LoggerFactory.getLogger(LibraryService.class);
    private final LibraryRepository libraryRepository;
    private final LibrarianRepository librarianRepository;


    public LibrarianService(LibraryRepository libraryRepository, LibrarianRepository librarianRepository) {
        this.libraryRepository = libraryRepository;
        this.librarianRepository = librarianRepository;
    }

    public List<Librarian> addLibrarian(Long libraryId, Librarian librarian) throws ClassNotFoundException {
        Library library = libraryRepository.findById(libraryId).orElseThrow(ClassNotFoundException::new);
        List<Librarian> librarians = library.getLibrarians();
        librarians.add(librarian);
        library.setLibrarians(librarians);
        librarian.setLibrary(library);
        libraryRepository.save(library);
        librarianRepository.save(librarian);
        return library.getLibrarians();
    }

}