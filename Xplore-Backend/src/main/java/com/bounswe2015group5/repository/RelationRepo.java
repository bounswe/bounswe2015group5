package com.bounswe2015group5.repository;

import com.bounswe2015group5.models.Contribution;
import com.bounswe2015group5.models.Relation;
import com.bounswe2015group5.models.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationRepo extends PagingAndSortingRepository<Relation,Integer> {
    List<Tag> findByContributionContributionId(Integer id);
}