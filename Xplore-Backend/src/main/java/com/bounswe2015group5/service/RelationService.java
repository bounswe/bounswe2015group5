package com.bounswe2015group5.service;

import com.bounswe2015group5.model.Contribution;
import com.bounswe2015group5.model.Relation;
import com.bounswe2015group5.model.Tag;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface RelationService {
    Iterable<Tag> getTagsByContributionId(int contributionId);
    Iterable<Contribution> getContributionsByTagId(int tagId);


    Iterable<Relation> getRelationsByTagId(int tagId);
    Iterable<Relation> getRelationsByContributionId(int contributionId);

    Map<Integer,Integer> findMostRelatedTagsWithTag(int tagId);
}
