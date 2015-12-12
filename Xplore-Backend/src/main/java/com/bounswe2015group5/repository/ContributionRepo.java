package com.bounswe2015group5.repository;

import com.bounswe2015group5.models.Contribution;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionRepo extends PagingAndSortingRepository<Contribution,Integer>{

}