package com.example.springboot.utility.specifications;

import com.example.springboot.model.Library;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

public class LibrarySpecification extends FilterSpecification implements Specification<Library> {

    public LibrarySpecification(SearchCriteria searchCriteria) {
        super(searchCriteria);
    }

    @Override
    public Predicate toPredicate(Root<Library> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return super.toPredicate(root, query, builder);
    }
}
