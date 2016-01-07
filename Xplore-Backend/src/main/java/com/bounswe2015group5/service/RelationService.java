package com.bounswe2015group5.service;

import com.bounswe2015group5.model.Contribution;
import com.bounswe2015group5.model.Relation;
import com.bounswe2015group5.model.Tag;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface RelationService {

    /**
     *
     * @param contributionId contribution id
     * @return list of tags related with contribution
     */
    Iterable<Tag> getTagsByContributionId(int contributionId);

    /**
     *
     * @param tagId tag id
     * @return list of contributions related with tag
     */
    Iterable<Contribution> getContributionsByTagId(int tagId);

    /**
     *
     * @param tagId tag id
     * @return list of all relations of tag
     */
    Iterable<Relation> getRelationsByTagId(int tagId);

    /**
     *
     * @param contributionId tag id
     * @return list of all relations of contribution
     */
    Iterable<Relation> getRelationsByContributionId(int contributionId);

    /**
     *
     * @param tagId tag id
     * @return map of most related tags given tag id
     */
    Map<Integer,Integer> findMostRelatedTagsWithTag(int tagId);

    /**
     * Saves relation
     * @param relation relation to be saved
     * @return relation is saved
     */
    Relation saveRelation(Relation relation);

    /**
     * deletes relation
     * @param relation relation to be deleted
     */
    void deleteRelation(Relation relation);

    /**
     * Deletes relations in a list
     * @param relations list of relations
     */
    void deleteRelations(Iterable<Relation> relations);
}
