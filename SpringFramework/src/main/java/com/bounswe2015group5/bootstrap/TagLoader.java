package com.bounswe2015group5.bootstrap;

import com.bounswe2015group5.domain.Tag;
import com.bounswe2015group5.repositories.TagRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@DependsOn("contributionLoader")
public class TagLoader implements ApplicationListener<ContextRefreshedEvent> {

    private TagRepository tagRepository;

    private Logger log = Logger.getLogger(TagLoader.class);

    @Autowired
    public void setTagRepository(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Tag justice = new Tag();

        justice.setName("Justice System");
        tagRepository.save(justice);

        log.info("Saved TAG Justice - id: " + justice.getId());

        Tag freedom = new Tag();

        freedom.setName("Freedom of Speech");
        tagRepository.save(freedom);

        log.info("Saved TAG Freedom of speech- id: " + freedom.getId());
    }
}
