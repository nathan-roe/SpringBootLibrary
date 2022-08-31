package com.example.springboot;


import com.example.springboot.model.Book;
import com.example.springboot.model.Librarian;
import com.example.springboot.repository.BookRepository;
import com.example.springboot.repository.LibrarianRepository;
import com.example.springboot.model.Library;
import com.example.springboot.repository.LibraryRepository;
import com.example.springboot.model.Reader;
import com.example.springboot.repository.ReaderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
public class DatabaseLoader implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(DatabaseLoader.class);
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final LibrarianRepository librarianRepository;
    private final LibraryRepository libraryRepository;

    @Autowired
    public DatabaseLoader(BookRepository bookRepository, ReaderRepository readerRepository,
                          LibrarianRepository librarianRepository, LibraryRepository libraryRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
        this.librarianRepository = librarianRepository;
        this.libraryRepository = libraryRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

//        Customer greg = this.customerRepository.save(new Customer("greg1", "turnquist",
//                "email1@example.com", "topSecret1!", "ROLE_MANAGER"));
//        Customer oliver = this.customerRepository.save(new Customer("oliver", "gierke",
//                "email2@example.com", "topSecret1!", "ROLE_MANAGER"));
//
//        SecurityContextHolder.getContext().setAuthentication(
//                new UsernamePasswordAuthenticationToken("greg", "doesn't matter",
//                        AuthorityUtils.createAuthorityList("ROLE_MANAGER")));
//
//        SecurityContextHolder.getContext().setAuthentication(
//                new UsernamePasswordAuthenticationToken("oliver", "doesn't matter",
//                        AuthorityUtils.createAuthorityList("ROLE_MANAGER")));
//        this.bookRepository.save(new Book("name", 12F, new ArrayList<Customer>() {{ add(greg); add(oliver); }}) );
//        Book book1 = new Book("This is a title 1", 12F);
//        Book book2 = new Book("This is a title 2", 25F);
//        this.bookRepository.save(book1);
//        this.bookRepository.save(book2);
//
//        Reader reader = new Reader("FirstName","LastName","test1@example.com",
//                Arrays.asList(book1, book2));
//        this.readerRepository.save(reader);
//
//        Library library = new Library("State", "City", "Address1", "Address2", "Name");
//        Library library1 = new Library("State1", "City1", "Address11", "Address21", "Name1");
//        Library library2 = new Library("Sta2te", "Cit2y", "Address12", "Address22", "Name2");
//        this.libraryRepository.save(library);
//        this.libraryRepository.save(library1);
//        this.libraryRepository.save(library2);
//
//
//        Library library3 = new Library("S3tate", "Ci3ty", "Address13", "Address23", "Name3");
//        Librarian librarian = new Librarian("FirstName", "LastName",
//                "library@example.com", "topSecret1!");
//        List<Librarian> librarians = library3.getLibrarians();
//        librarians.add(librarian);
//        library3.setLibrarians(librarians);
//        this.libraryRepository.save(library3);
//        this.librarianRepository.save(librarian);
//
//        logger.info("Library: ", library);

//        librarianRepository.save(new Librarian("FirstName", "LastName",
//                "library@example.com", "topSecret1!"));

//        logger.info("Librarians: ", librarianRepository.findAll());

//        librarian.setLibrary(library);
//        librarianRepository.save(librarian);

//        book1.setLibrary(library);
//        book2.setLibrary(library);
//
//        this.bookRepository.save(book1);
//        this.bookRepository.save(book2);

        SecurityContextHolder.clearContext();
    }
}