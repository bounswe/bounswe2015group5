package com.bounswe2015group5.services;

import com.bounswe2015group5.domain.Tag;

public interface TagService {
    Iterable<Tag> listAllTags();

    Tag getTagById(Integer id);

    Tag saveTag(Tag tag);

    void deleteTag(Integer id);
}
