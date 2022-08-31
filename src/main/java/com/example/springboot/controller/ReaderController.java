package com.example.springboot.controller;

import com.example.springboot.model.Reader;
import com.example.springboot.repository.ReaderRepository;

import com.example.springboot.service.ReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/readers")
public class ReaderController {

    private final ReaderRepository readerRepository;
    private final ReaderService readerService;

    public ReaderController(ReaderRepository readerRepository, ReaderService readerService) {
        this.readerRepository = readerRepository;
        this.readerService = readerService;
    }

    @GetMapping
    public Iterable<Reader> getReaders() {
        return readerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reader getReaderById(@PathVariable Long id) {
        return readerRepository.findById(id).get();
    }

    @PostMapping
    public ResponseEntity<?> createReader(@Validated @RequestBody Reader reader) {
        Reader r = this.readerRepository.save(reader);
        return ResponseEntity.status(HttpStatus.CREATED).body(r);
    }

    @PostMapping("/{readerId}/library/{libraryId}")
    public ResponseEntity<?> addReaderToLibrary(@PathVariable Long readerId, @PathVariable Long libraryId) {
        try {
            readerService.addReaderToLibrary(readerId, libraryId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch(ClassNotFoundException notFoundException) {
            return ResponseEntity.badRequest().build();
        }
    }

}