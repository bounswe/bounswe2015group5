package com.bounswe2015group5.repository;

import com.bounswe2015group5.model.UserRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepo extends CrudRepository<UserRate,UserRate.RateID>{
    List<UserRate> findByContributionId(@Param("id")Integer id);
//    List<UserRate> findByUserId(@Param("user")String username);
//    List<UserRate>findByUserIdAndContributionId(@Param("user")String username,@Param("id")Integer id);
}
