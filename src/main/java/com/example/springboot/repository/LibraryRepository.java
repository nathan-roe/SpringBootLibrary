package com.example.springboot.repository;

import com.example.springboot.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {
}
