package com.example.springboot.repository;

import com.example.springboot.model.Book;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

}
