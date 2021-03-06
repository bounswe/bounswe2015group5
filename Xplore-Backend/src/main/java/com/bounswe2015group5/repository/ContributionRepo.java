package com.bounswe2015group5.repository;

import com.bounswe2015group5.model.Contribution;
import com.bounswe2015group5.model.Relation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for contributions
 * Repository tag indicates that the class is a "Repository" indeed.
 * PagingAndSortingRepository provides additional methods to retrieve entities using the pagination and sorting abstraction.
 */
@Repository
public interface ContributionRepo extends PagingAndSortingRepository<Contribution,Integer>{
    List<Contribution> findByCreator_Username(@Param("username")String username);

}