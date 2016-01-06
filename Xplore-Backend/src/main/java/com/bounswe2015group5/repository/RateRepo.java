package com.bounswe2015group5.repository;

import com.bounswe2015group5.model.UserRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepo extends CrudRepository<UserRate,UserRate.RateID>{
}
