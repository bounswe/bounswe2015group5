package com.bounswe2015group5.repository;

import com.bounswe2015group5.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for comments
 * Repository tag indicates that the class is a "Repository" indeed.
 * PagingAndSortingRepository provides additional methods to retrieve entities using the pagination and sorting abstraction.
 */
@Repository
@SuppressWarnings("unused")
public interface CommentRepo extends PagingAndSortingRepository<Comment,Integer>{
    Page<Comment> findByContributionId(@Param("id")Integer id, Pageable pageable);
    Page<Comment> findByUser(@Param("username")String username, Pageable pageable);

    List<Comment> findByContributionId(@Param("id")Integer id);
    List<Comment> findByUser(@Param("username")String username);
}
