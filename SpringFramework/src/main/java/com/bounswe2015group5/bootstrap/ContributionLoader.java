package com.bounswe2015group5.bootstrap;

import com.bounswe2015group5.domain.Contribution;
import com.bounswe2015group5.repositories.ContributionRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContributionLoader implements ApplicationListener<ContextRefreshedEvent> {

    private ContributionRepository contributionRepository;

    private Logger log = Logger.getLogger(ContributionLoader.class);

    @Autowired
    public void setContributionRepository(ContributionRepository contributionRepository) {
        this.contributionRepository = contributionRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Contribution cont1 = new Contribution();

        cont1.setTitle("Cumhuriyet daily’s Dundar, Gul arrested over report on Syria arms transfer");
        cont1.setContent("The editor-in-chief of the Cumhuriyet daily, Can Dundar, and the paper's Ankara representative Erdem Gul have been arrested on charges of being members of a terror organization, espionage and revealing confidential documents -- charges that could see them spend life in prison.");
        contributionRepository.save(cont1);

        log.info("Saved CONTRIB Cumhuriyet daily’s Dundar - id: " + cont1.getId());

        Contribution cont2 = new Contribution();

        cont2.setTitle("Istanbul court rejects appeal against arrests of Cumhuriyet daily's Can Dündar, Erdem Gül");
        cont2.setContent("The Istanbul 7th Court of Peace said that that there was \"no unlawful aspect\" regarding the decision of their arrest. The court has then transferred the appeal demand to a higher court of the Istanbul 8th Court of Peace for further evaluation.");
        contributionRepository.save(cont2);

        log.info("Saved CONTRIB Istanbul court - id: " + cont2.getId());

    }
}
