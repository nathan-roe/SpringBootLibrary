package com.example.springboot.repository;

import com.example.springboot.model.Librarian;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LibrarianRepository extends PagingAndSortingRepository<Librarian, Long> {
}
