package com.example.springboot.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Library {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;

    private String state, city, address1, address2, name;


    @OneToMany(mappedBy = "library")
    private List<Book> books = new ArrayList<>();;

    @OneToMany(mappedBy = "library")
    private List<Librarian> librarians = new ArrayList<>();
    @ManyToMany(mappedBy = "libraries")
    private List<Reader> readers;

    public Library() {}

    public Library(String state, String city, String address1, String address2, String name) {
        this.state = state;
        this.city = city;
        this.address1 = address1;
        this.address2 = address2;
        this.name = name;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Librarian> getLibrarians() {
        return librarians;
    }

    @JsonManagedReference
    public void setLibrarians(List<Librarian> librarians) {
        this.librarians = librarians;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Reader> getReaders() {
        return readers;
    }

    public void setReaders(List<Reader> readers) {
        this.readers = readers;
    }

    public Long getId() {
        return id;
    }
}
