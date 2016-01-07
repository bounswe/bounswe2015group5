package com.bounswe2015group5.service;

import com.bounswe2015group5.model.Contribution;
import com.bounswe2015group5.model.Relation;
import com.bounswe2015group5.model.Tag;
import com.bounswe2015group5.repository.RelationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RelationServiceImpl implements  RelationService {

    @Autowired
    RelationRepo relationRepo;

    /**
     *
     * @param contributionId contribution id
     * @return list of tags related with contribution
     */
    @Override
    public Iterable<Tag> getTagsByContributionId(int contributionId) {
        return relationRepo.findByContributionId(contributionId).stream().map(Relation::getTag).collect(Collectors.toList());
    }

    /**
     *
     * @param tagId tag id
     * @return list of contributions related with tag
     */
    @Override
    public Iterable<Contribution> getContributionsByTagId(int tagId) {
        return relationRepo.findByTagId(tagId).stream().map(Relation::getContribution).collect(Collectors.toList());
    }

    /**
     *
     * @param tagId tag id
     * @return list of all relations of tag
     */
    @Override
    public Iterable<Relation> getRelationsByTagId(int tagId) {
        return relationRepo.findByTagId(tagId);
    }
    /**
     *
     * @param contributionId tag id
     * @return list of all relations of contribution
     */
    @Override
    public Iterable<Relation> getRelationsByContributionId(int contributionId) {
        return relationRepo.findByContributionId(contributionId);
    }

    /**
     *
     * @param tagId tag id
     * @return map of most related tags given tag id
     */
    @Override
    public Map<Integer,Integer> findMostRelatedTagsWithTag(int tagId) {
        List<Contribution> contributions = relationRepo.findByTagId(tagId).
                stream().map(Relation::getContribution).collect(Collectors.toList());
        Map<Integer,Integer> counts = new HashMap<>();

        contributions.forEach(contribution -> {
            relationRepo.findByContributionId(contribution.getId()).stream().map(Relation::getTag).
                    forEach(tag -> {
                        counts.putIfAbsent(tag.getId(),0);
                        counts.put(tag.getId(),counts.get(tag.getId())+1);
                    });
        });

        Map<Integer, Integer> sortedMap =
                counts.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));
        return sortedMap;
    }

    /**
     * Saves relation
     * @param relation relation to be saved
     * @return relation is saved
     */
    @Override
    public Relation saveRelation(Relation relation) {
        return relationRepo.save(relation);
    }

    /**
     * deletes relation
     * @param relation relation to be deleted
     */
    @Override
    public void deleteRelation(Relation relation) {
        relationRepo.delete(relation);
    }

    /**
     * Deletes relations in a list
     * @param relations list of relations
     */
    @Override
    public void deleteRelations(Iterable<Relation> relations) {
        relations.forEach(relation -> {
            relationRepo.delete(relation);
            System.out.println("attempted to delete " + relation);
        });
    }
}