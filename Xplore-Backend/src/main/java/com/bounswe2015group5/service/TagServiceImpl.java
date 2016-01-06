package com.bounswe2015group5.service;

import com.bounswe2015group5.model.Tag;
import com.bounswe2015group5.repository.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagRepo tagRepo;

    @Override
    public Iterable<Tag> listAllTags() {
        return tagRepo.findAll();
    }

    @Override
    public Tag getTagById(int id) {
        return tagRepo.findOne(id);
    }

    @Override
    public Tag saveTag(Tag tag) {
        return tagRepo.save(tag);
    }

    @Override
    public void deleteTag(int id) {
        tagRepo.delete(id);
    }

}
