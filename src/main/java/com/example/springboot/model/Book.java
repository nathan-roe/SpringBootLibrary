package com.example.springboot.model;

import com.example.springboot.model.Librarian;
import com.example.springboot.model.Library;
import com.example.springboot.model.Reader;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    private String title;
    private float price;
    private enum Status {RENTED, AVAILABLE};
    private Status status = Status.AVAILABLE;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reader")
    private Reader reader;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "library")
    private Library library;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "librarian")
    private Librarian addedBy;

    public Book() {}

    public Book(String title, float price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Librarian getAddedBy() {
        return addedBy;
    }
    public void setAddedBy(Librarian addedBy) {
        this.addedBy = addedBy;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Book && this.id.equals(((Book) o).getId());
    }
}
