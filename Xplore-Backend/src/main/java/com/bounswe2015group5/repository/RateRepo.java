package com.bounswe2015group5.repository;

import com.bounswe2015group5.model.UserRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for rates
 * Repository tag indicates that the class is a "Repository" indeed.
 * CrudRepository provides additional methods to retrieve entities using the pagination and sorting abstraction.
 */
@Repository
public interface RateRepo extends CrudRepository<UserRate,UserRate.RateID>{
    List<UserRate> findByContributionId(@Param("id")Integer id);
    List<UserRate> findByUser_username(@Param("user")String username);
    List<UserRate>findByUser_usernameAndContributionId(@Param("user")String username,@Param("id")Integer id);
}
