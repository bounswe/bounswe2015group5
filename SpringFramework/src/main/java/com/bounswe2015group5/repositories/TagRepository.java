package com.bounswe2015group5.repositories;

import com.bounswe2015group5.domain.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends PagingAndSortingRepository<Tag, Integer> {
//    List<Tag> findByDescription(@Param("description") String description);
//    @EntityGraph(value = "Tag.detail", type = EntityGraph.EntityGraphType.LOAD)
//    Tag getByContributionId(Integer id);
}
