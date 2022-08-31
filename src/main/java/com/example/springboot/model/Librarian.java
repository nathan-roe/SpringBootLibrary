package com.example.springboot.model;

import com.example.springboot.model.Library;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Librarian {

//    TODO: Removed until authentication is built out.
//    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//    @Column(name = "librarian_id", updatable = false, nullable = false)
//    private UUID id;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "librarian_id", updatable = false, nullable = false)
    private Long id;

    private String firstName, lastName, email, password;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "library")
    @JsonIgnore
    private Library library;

    public Librarian() {}

    public Librarian(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.setPassword(password);
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

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
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
}
