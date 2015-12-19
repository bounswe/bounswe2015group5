package com.bounswe2015group5.loader;

import com.bounswe2015group5.model.Contribution;
import com.bounswe2015group5.model.Relation;
import com.bounswe2015group5.model.Tag;
import com.bounswe2015group5.model.User;
import com.bounswe2015group5.repository.ContributionRepo;
import com.bounswe2015group5.repository.RelationRepo;
import com.bounswe2015group5.repository.TagRepo;
import com.bounswe2015group5.repository.UserRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

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

    private Logger log = Logger.getLogger(Loader.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadTags();
        loadContributions();
        loadRelations();
        loadUsers();
    }

    private void loadTags() {
        Tag justice = new Tag();

        justice.setName("Justice System");
        tagRepo.save(justice);

        log.info("Saved TAG Justice - id: " + justice.getId());

        Tag freedom = new Tag();

        freedom.setName("Freedom of Speech");
        tagRepo.save(freedom);

        log.info("Saved TAG Freedom of speech- id: " + freedom.getId());
    }

    private void loadContributions(){
        Contribution cont1 = new Contribution();

        cont1.setTitle("Cumhuriyet daily’s Dundar, Gul arrested over report on Syria arms transfer");
        cont1.setContent("The editor-in-chief of the Cumhuriyet daily, Can Dundar, and the paper's Ankara representative Erdem Gul have been arrested on charges of being members of a terror organization, espionage and revealing confidential documents -- charges that could see them spend life in prison.");
        contributionRepo.save(cont1);

        log.info("Saved CONTRIB Cumhuriyet daily’s Dundar - id: " + cont1.getId());

        Contribution cont2 = new Contribution();

        cont2.setTitle("Istanbul court rejects appeal against arrests of Cumhuriyet daily's Can Dündar, Erdem Gül");
        cont2.setContent("The Istanbul 7th Court of Peace said that that there was \"no unlawful aspect\" regarding the decision of their arrest. The court has then transferred the appeal demand to a higher court of the Istanbul 8th Court of Peace for further evaluation.");
        contributionRepo.save(cont2);

        log.info("Saved CONTRIB Istanbul court - id: " + cont2.getId());
    }

    private void loadRelations() {
        Tag t1 = tagRepo.findOne(1);
        Contribution c1 = contributionRepo.findOne(1);

        Relation rel1 = new Relation(t1,c1);

        log.info("try to save relation : " + t1.getName() + " - " + c1.getTitle());

        relationRepo.save(rel1);

        log.info("Saved relation : " + rel1.getId());
    }

    private void loadUsers() {
        User hanefi = new User("hanefi","hanefi","hanefi@hanefi.com");

        log.info("trying to save user : " + hanefi.getUsername());

        userRepo.save(hanefi);

        log.info("Saved USER Hanefi - id : " + hanefi.getUsername());
    }

}
