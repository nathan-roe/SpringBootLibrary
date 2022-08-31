package com.example.springboot.repository;

import com.example.springboot.model.Reader;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReaderRepository extends PagingAndSortingRepository<Reader, Long> {
    Reader findByEmail(String email);
}
