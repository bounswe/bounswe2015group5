package com.bounswe2015group5.repositories;

import com.bounswe2015group5.domain.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TagRepository extends PagingAndSortingRepository<Tag, Integer> {
//    List<Tag> findByDescription(@Param("description") String description);
}
