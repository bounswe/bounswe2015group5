//package com.bounswe2015group5.service;
//
//import com.bounswe2015group5.model.Relation;
//import com.bounswe2015group5.repository.RelationRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * Created by abdul on 12.12.2015.
// */
//public class RelationServiceImpl implements RelationService {
//
//    private RelationRepo relationRepo;
//
//    @Autowired
//    public void setRelationRepo(RelationRepo relationRepo) {
//        this.relationRepo = relationRepo;
//    }
//
//    @Override
//    public Iterable<Relation> listAllTags() {
//        return relationRepo.findAll();
//    }
//
//    @Override
//    public Relation getRelationByRelationID(Relation.RelationID relationID) {
//        return relationRepo.findOne()
//    }
//
//    @Override
//    public Relation saveRelation(Relation relation) {
//        return null;
//    }
//
//    @Override
//    public void deleteRelation(Relation relation) {
//
//    }
//}
