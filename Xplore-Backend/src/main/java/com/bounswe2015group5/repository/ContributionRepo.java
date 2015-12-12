package com.bounswe2015group5.repository;

import com.bounswe2015group5.model.Contribution;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionRepo extends PagingAndSortingRepository<Contribution,Integer>{

}