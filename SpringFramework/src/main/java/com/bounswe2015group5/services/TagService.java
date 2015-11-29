package com.bounswe2015group5.services;

import com.bounswe2015group5.domain.Tag;

/**
 * Created by abdul on 3.12.2015.
 */
public interface TagService {
    Iterable<Tag> listAllTags();

    Tag getTagById(Integer id);

    Tag saveTag(Tag tag);

    void deleteTag(Integer id);
}
