package com.example.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
public class Reader {

//    TODO: Removed until authentication is added.
//    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//    @Column(name = "id", updatable = false, nullable = false)
//    private UUID id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String firstName, lastName, password;
    @Email(message = "Email is not valid")
    @NotEmpty(message = "Email is required")
    @Column(name = "email", unique = true)
    private String email;
    @OneToMany(mappedBy = "reader")
    private List<Book> books;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "readers_libraries",
            joinColumns = @JoinColumn(name = "library_id"),
            inverseJoinColumns = @JoinColumn(name = "reader_id")
    )
    private List<Library> libraries;

    public Reader() {}

    public Reader(String firstName, String lastName, String email, List<Book> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.books =  books;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getPassword() {
        return password;
    }

//    TODO: Move to controller
    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }


    public Long getId() {
        return id;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        else if (!(o instanceof Reader)) return false;
        Reader reader = (Reader) o;
        return Objects.equals(this.id,reader.id) && Objects.equals(this.email, reader.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.email);
    }

    @Override
    public String toString() {
        return String.format("Reader: ID: %s, Email: %s", this.id.toString(), this.email);
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public void setLibraries(List<Library> libraries) {
        this.libraries = libraries;
    }
}
