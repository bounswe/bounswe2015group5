package com.bounswe2015group5.repositories;

import com.bounswe2015group5.configuration.RepositoryConfiguration;
import com.bounswe2015group5.domain.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class TagRepositoryTest {

    private TagRepository tagRepository;

    @Autowired
    public void setTagRepository(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Test
    public void testSaveTag() {
        //setup tag
        Tag tag = new Tag();
        tag.setName("Spring Framework");

        //save tag, verify has ID value after save
        assertNull(tag.getId()); //null before save
        tagRepository.save(tag);
        assertNotNull(tag.getId()); //not null after save

        //fetch from DB
        Tag fetchedTag = tagRepository.findOne(tag.getId());

        //should not be null
        assertNotNull(fetchedTag);

        //should equal
        assertEquals(tag.getId(), fetchedTag.getId());
        assertEquals(tag.getName(), fetchedTag.getName());

        //update description and save
        fetchedTag.setName("New Name");
        tagRepository.save(fetchedTag);

        //get from DB, should be updated
        Tag fetchedUpdatedTag = tagRepository.findOne(fetchedTag.getId());
        assertEquals(fetchedTag.getName(), fetchedUpdatedTag.getName());

        //verify count of tags in DB
        long tagCount = tagRepository.count();
        assertEquals(tagCount, 1);

        //get all tags, list should only have one
        Iterable<Tag> tags = tagRepository.findAll();

        int count = 0;

        for (Tag t : tags) {
            count++;
        }

        assertEquals(count, 1);
    }
}
