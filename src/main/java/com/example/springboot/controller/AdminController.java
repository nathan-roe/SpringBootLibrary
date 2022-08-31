package com.example.springboot.controller;

import com.example.springboot.admin.AdminService;
import com.example.springboot.model.Book;
import com.example.springboot.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController("admin")
public class AdminController {

    private final AdminService adminService;
    private final BookRepository bookRepository;

    @Autowired
    AdminController(AdminService adminService, BookRepository bookRepository) {
        this.adminService = adminService;
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books")
    public Collection<Book> getBooks() {
        adminService.ensureAdmin();
        return (Collection<Book>) this.bookRepository.findAll();
    }

}
