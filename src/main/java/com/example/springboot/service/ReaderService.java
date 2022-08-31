package com.example.springboot.service;

import com.example.springboot.model.Book;
import com.example.springboot.model.Librarian;
import com.example.springboot.model.Library;
import com.example.springboot.model.Reader;
import com.example.springboot.repository.BookRepository;
import com.example.springboot.repository.LibrarianRepository;
import com.example.springboot.repository.LibraryRepository;
import com.example.springboot.repository.ReaderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReaderService {
    Logger logger = LoggerFactory.getLogger(ReaderService.class);
    private final ReaderRepository readerRepository;
    private final LibraryRepository libraryRepository;


    public ReaderService(ReaderRepository readerRepository, LibraryRepository libraryRepository) {
        this.readerRepository = readerRepository;
        this.libraryRepository = libraryRepository;
    }

    public void addReaderToLibrary(Long readerId, Long libraryId) throws ClassNotFoundException {
        Reader reader = readerRepository.findById(readerId).orElseThrow(ClassNotFoundException::new);
        Library library = libraryRepository.findById(libraryId).orElseThrow(ClassNotFoundException::new);
//      Add library to reader libraries
        reader.setLibraries(
                Stream.concat(reader.getLibraries().stream(),
                Arrays.asList(library).stream()
        ).collect(Collectors.toList()));
//      Add reader to library readers
        library.setReaders(Stream.concat(
                library.getReaders().stream(),
                Arrays.asList(reader).stream()
        ).collect(Collectors.toList()));

        libraryRepository.save(library);
        readerRepository.save(reader);
    }
}
