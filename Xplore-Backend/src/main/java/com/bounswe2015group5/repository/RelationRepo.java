package com.bounswe2015group5.repository;

import com.bounswe2015group5.model.Contribution;
import com.bounswe2015group5.model.Relation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("unused")
public interface RelationRepo extends PagingAndSortingRepository<Relation,Relation.RelationID>{
    Page<Relation> findByContributionId(@Param("id")Integer id, Pageable pageable);
    Page<Relation> findByTagId(@Param("id")Integer id, Pageable pageable);
}