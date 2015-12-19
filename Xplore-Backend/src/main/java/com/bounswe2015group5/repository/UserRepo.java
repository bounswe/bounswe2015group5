package com.bounswe2015group5.repository;

import com.bounswe2015group5.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends PagingAndSortingRepository<User,String> {
    User findByUserName(@Param("username") String username);
}
