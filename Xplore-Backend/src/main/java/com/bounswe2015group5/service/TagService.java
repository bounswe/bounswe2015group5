package com.bounswe2015group5.service;

import com.bounswe2015group5.model.Tag;

/**
 * Tag service that is serves tag operations
 */
public interface TagService {
    /**
     * @return List of all tags in database
     */
    Iterable<Tag> listAllTags();

    /**
     *
     * @param id tag id
     * @return tag of given id
     */
    Tag getTagById(int id);

    /**
     * Saves tag
     * @param tag tag will be saved
     * @return returns the object that is saved
     */
    Tag saveTag(Tag tag);
    /**
     * deletes tag from database with given id
     * @param id
     */

    void deleteTag(int id);
}
