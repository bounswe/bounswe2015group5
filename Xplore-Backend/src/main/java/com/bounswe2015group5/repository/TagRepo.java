package com.bounswe2015group5.repository;

import com.bounswe2015group5.models.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepo extends PagingAndSortingRepository<Tag, Integer> {
}
