package com.example.springboot.service;

import com.example.springboot.model.Book;
import com.example.springboot.model.Librarian;
import com.example.springboot.model.Library;
import com.example.springboot.repository.BookRepository;
import com.example.springboot.repository.LibrarianRepository;
import com.example.springboot.repository.LibraryRepository;
import com.example.springboot.utility.specifications.LibrarySpecification;
import com.example.springboot.utility.specifications.SearchCriteria;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final BookRepository bookRepository;
    private final LibrarianRepository librarianRepository;


    public LibraryService(LibraryRepository libraryRepository, BookRepository bookRepository,
                          LibrarianRepository librarianRepository) {
        this.libraryRepository = libraryRepository;
        this.bookRepository = bookRepository;
        this.librarianRepository = librarianRepository;
    }

    public List<Library> filterAndSort(String sort, String order, String filter) {
        Sort s = Sort.by(order.equals("asc") ?  Sort.Direction.ASC :  Sort.Direction.DESC, sort);
        Matcher matcher = Pattern.compile("(.*?)(>|<|:)(.*)").matcher(filter);
        if(matcher.find() && matcher.groupCount() == 3) {
            LibrarySpecification ls = new LibrarySpecification(new SearchCriteria(
                    matcher.group(1).trim(), matcher.group(2).trim(), matcher.group(3).trim()
            ));
            return libraryRepository.findAll(ls, s);
        }
        return (List<Library>) libraryRepository.findAll(s);
    }

    public List<Library> filterAndSort(String m, String sort, String order, String filter)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = libraryRepository.getClass().getDeclaredMethod(m);
        Sort s = Sort.by(order.equals("asc") ?  Sort.Direction.ASC :  Sort.Direction.DESC, sort);
        Matcher matcher = Pattern.compile("(.*?)(>|<|:)(.*)").matcher(filter);
        if(matcher.find() && matcher.groupCount() == 3) {
            LibrarySpecification ls = new LibrarySpecification(new SearchCriteria(
                    matcher.group(1).trim(), matcher.group(2).trim(), matcher.group(3).trim()
            ));
            return (List<Library>) method.invoke(ls, s);
        }
        return (List<Library>) method.invoke(s);
    }

    public List<Book> addBook(Long id, Book book) {
        Library library = libraryRepository.findById(id).get();
        List<Book> books = library.getBooks();
        books.add(book);
        library.setBooks(books);
        book.setLibrary(library);
        libraryRepository.save(library);
        bookRepository.save(book);
        return library.getBooks();
    }

    public void removeBook(Long librarianId, Long bookId) throws IllegalAccessException {
        Librarian librarian = librarianRepository.findById(librarianId).get();
        Book book = bookRepository.findById(bookId).get();
        if(librarian.getLibrary().equals(book.getLibrary())) {
            bookRepository.delete(book);
        } else {
            throw new IllegalAccessException();
        }
    }
}
