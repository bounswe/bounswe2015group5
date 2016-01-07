package com.bounswe2015group5.repository;

import com.bounswe2015group5.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for users
 * Repository tag indicates that the class is a "Repository" indeed.
 * PagingAndSortingRepository provides additional methods to retrieve entities using the pagination and sorting abstraction.
 */
@Repository
public interface UserRepo extends PagingAndSortingRepository<User,String> {

}
