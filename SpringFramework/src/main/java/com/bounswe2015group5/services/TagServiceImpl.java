package com.bounswe2015group5.services;

import com.bounswe2015group5.domain.Tag;
import com.bounswe2015group5.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {
    private TagRepository tagRepository;

    @Autowired
    public void setTagRepository(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }


    @Override
    public Iterable<Tag> listAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag getTagById(Integer id) {
        return tagRepository.findOne(id);
    }

    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void deleteTag(Integer id) {
        tagRepository.delete(id);
    }
}
