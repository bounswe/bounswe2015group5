package com.bounswe2015group5.repository;

import com.bounswe2015group5.model.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for tags
 * Repository tag indicates that the class is a "Repository" indeed.
 * PagingAndSortingRepository provides additional methods to retrieve entities using the pagination and sorting abstraction.
 */
@Repository
public interface TagRepo extends PagingAndSortingRepository<Tag, Integer> {
}
