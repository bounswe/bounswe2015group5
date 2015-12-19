package com.bounswe2015group5.service;

import com.bounswe2015group5.model.Tag;

public interface TagService {
    Iterable<Tag> listAllTags();
    Tag getTagById(int id);
    Tag saveTag(Tag tag);
    void deleteTag(int id);
}
