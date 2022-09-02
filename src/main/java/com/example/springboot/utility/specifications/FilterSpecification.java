package com.example.springboot.utility.specifications;

import com.example.springboot.model.Library;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

abstract public class FilterSpecification {

    private SearchCriteria searchCriteria;

    public FilterSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public Predicate toPredicate(Root<Library> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (searchCriteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(root.get(searchCriteria.getKey()),
                    searchCriteria.getValue().toString());
        }
        else if (searchCriteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(root.get(searchCriteria.getKey()),
                    searchCriteria.getValue().toString());
        }
        else if (searchCriteria.getOperation().equalsIgnoreCase(":")) {
            if(root.get(searchCriteria.getKey()).getJavaType() == String.class) {
                return builder.like(root.get(searchCriteria.getKey()),
                        "%" + searchCriteria.getValue().toString() + "%");
            } else {
                return builder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue());
            }
        }
        return null;
    }
}
