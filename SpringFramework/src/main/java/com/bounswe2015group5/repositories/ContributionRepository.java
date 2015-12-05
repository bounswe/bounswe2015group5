package com.bounswe2015group5.repositories;

import com.bounswe2015group5.domain.Contribution;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionRepository extends PagingAndSortingRepository<Contribution, Integer> {

}
