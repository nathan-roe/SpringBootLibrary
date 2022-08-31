package com.example.springboot.controller;

import com.example.springboot.model.Reader;
import com.example.springboot.repository.ReaderRepository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final ReaderRepository readerRepository;

    public AuthController(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @PostMapping("/signup")
    @Transactional(rollbackOn = Exception.class)
    public Reader createReader(@Validated @RequestBody Reader reader) {
        reader.setPassword(reader.getPassword());
        readerRepository.save(reader);
        return reader;
    }

}
