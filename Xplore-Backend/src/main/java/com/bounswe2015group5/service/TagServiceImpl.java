package com.bounswe2015group5.service;

import com.bounswe2015group5.model.Tag;
import com.bounswe2015group5.repository.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagRepo tagRepo;

    /**
     *
     * @return List of all tags in database
     */
    @Override
    public Iterable<Tag> listAllTags() {
        return tagRepo.findAll();
    }

    /**
     *
     * @param id tag id
     * @return tag of given id
     */
    @Override
    public Tag getTagById(int id) {
        return tagRepo.findOne(id);
    }

    /**
     * Saves tag
     * @param tag tag will be saved
     * @return returns the object that is saved
     */
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepo.save(tag);
    }

    /**
     * deletes tag from database with given id
     * @param id
     */
    @Override
    public void deleteTag(int id) {
        tagRepo.delete(id);
    }

}
