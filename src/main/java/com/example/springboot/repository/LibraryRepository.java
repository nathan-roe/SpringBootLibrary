package com.example.springboot.repository;

import com.example.springboot.model.Library;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LibraryRepository extends PagingAndSortingRepository<Library, Long>,
                                            JpaSpecificationExecutor<Library> {}
