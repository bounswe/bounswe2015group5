package com.bounswe2015group5.loader;

import com.bounswe2015group5.model.*;
import com.bounswe2015group5.repository.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Loader implements ApplicationListener<ContextRefreshedEvent>{
    @Autowired
    private TagRepo tagRepo;
    @Autowired
    private ContributionRepo contributionRepo;
    @Autowired
    private RelationRepo relationRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CommentRepo commentRepo;

    private Logger log = Logger.getLogger(Loader.class);

    //sample data
    private Contribution cont1,cont2;
    private Tag justice,freedom;
    private User hanefi;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadUsers();
        loadTags();
        loadContributions();
        loadRelations();
        loadComments();
    }

    private void loadTags() {
        justice = new Tag();

        justice.setName("Justice System");
        tagRepo.save(justice);

        log.info("Saved TAG Justice - id: " + justice.getId());

        freedom = new Tag();

        freedom.setName("Freedom of Speech");
        tagRepo.save(freedom);

        log.info("Saved TAG Freedom of speech- id: " + freedom.getId());

        for (int i=3;i<11;i++){
            Tag t = new Tag();
            t.setName("dummy tag : " + i);
            tagRepo.save(t);
        }
    }

    private void loadContributions(){
        cont1 = new Contribution();
        cont1.setTitle("Cumhuriyet daily’s Dundar, Gul arrested over report on Syria arms transfer");
        cont1.setContent("The editor-in-chief of the Cumhuriyet daily, Can Dundar, and the paper's Ankara representative Erdem Gul have been arrested on charges of being members of a terror organization, espionage and revealing confidential documents -- charges that could see them spend life in prison.");
        cont1.setCreator(hanefi);
        contributionRepo.save(cont1);

        log.info("Saved CONTRIB Cumhuriyet daily’s Dundar - id: " + cont1.getId());

        cont2 = new Contribution();
        cont2.setTitle("Istanbul court rejects appeal against arrests of Cumhuriyet daily's Can Dündar, Erdem Gül");
        cont2.setContent("The Istanbul 7th Court of Peace said that that there was \"no unlawful aspect\" regarding the decision of their arrest. The court has then transferred the appeal demand to a higher court of the Istanbul 8th Court of Peace for further evaluation.");
        cont2.setCreator(hanefi);

        contributionRepo.save(cont2);

        log.info("Saved CONTRIB Istanbul court - id: " + cont2.getId());

        for (int i=3;i<11;i++){
            Contribution c = new Contribution("cont title" + i,"cont content " + i,hanefi);
            contributionRepo.save(c);
        }
    }

    private void loadRelations() {
        Relation rel1 = new Relation(justice,cont1,hanefi);

        log.info("try to save relation : " + rel1);

        relationRepo.save(rel1);

        log.info("Saved relation : " + rel1.getId());

        Iterable<Relation> dummyRelations = new ArrayList<>();
        int rels[][] = {
                {3,3},
                {3,4},
                {3,10},
                {4,3},
                {4,9},
                {5,5},
                {5,6},
                {6,6},
                {6,7},
                {6,8},
                {7,5},
                {8,3},
                {8,9},
                {8,10},
                {9,4},
                {10,7},
                {10,8}
        };

        for (int i=0; i<17;i++){
            Tag t = tagRepo.findOne(rels[i][0]);
            Contribution c = contributionRepo.findOne(rels[i][1]);
            Relation r = new Relation(t,c,hanefi);
            relationRepo.save(r);
        }
//        for (int i=3;i<=tagRepo.count();i++){
//            for (int j=3;j<=contributionRepo.count();j++) {
//                if (gcd(i, j) == 1) {
//                    Tag t = tagRepo.findOne(i);
//                    Contribution c = contributionRepo.findOne(j);
//                    Relation r = new Relation(t,c,hanefi);
//                    relationRepo.save(r);
//                }
//            }
//        }

    }

    private int gcd(int i, int j) {
        return (j!=0) ? gcd(j,i%j) : i ;
    }

    private void loadUsers() {
        hanefi = new User("hanefi","hanefi","hanefi@hanefi.com");

        log.info("trying to save user : " + hanefi.getUsername());

        userRepo.save(hanefi);

        log.info("Saved USER Hanefi - id : " + hanefi.getUsername());
    }

    private void loadComments() {
        contributionRepo.findAll().forEach(contribution -> {
            Comment c = new Comment("dummy comment for contrib " + contribution.getId(),hanefi,contribution);
            if (contribution.getId()%2 == 0)
                commentRepo.save(c);
        });
    }

}
