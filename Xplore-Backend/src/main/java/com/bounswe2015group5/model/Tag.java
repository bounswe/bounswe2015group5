package com.bounswe2015group5.model;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Tag implements Serializable{
    /**
     * id of the tag
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiObjectField(description = "id of the tag", required = true)
    private Integer id;

    /**
     * name of the tag
     */
    @ApiObjectField(description = "name of the tag", required = true)
    private String name;

    /**
     * concept of the tag
     */
    @ApiObjectField(description = "concept of the tag", required = true)
    private String concept;

    /**
     * creation time of the tag
     */
    @Column(name = "created_at")
    @ApiObjectField(description = "The Timestamp when tag is created", required = true)
    public Date createdAt;

    /**
     * last update time of the tag
     */
    @Column(name = "updated_at")
    @ApiObjectField(description = "The Timestamp when tag is updated", required = true)
    public Date updatedAt;

    /**
     * creator user of the tag
     */
    @ManyToOne
    @JoinColumn(name = "creatorUser", referencedColumnName = "username")
    @ApiObjectField(description = "The User that created this tag", required = true)
    private User creator;

    public Tag() {
    }

    /**
     *
     * @return concept of the tag
     */
    public String getConcept() {
        return concept;
    }

    /**
     * Sets concept of the tag
     * @param concept
     */
    public void setConcept(String concept) {
        this.concept = concept;
    }

    /**
     * creates tag with given name, concept and user
     * @param name name of the tag
     * @param concept concept of the tag
     * @param creator creating user of the tag
     */
    public Tag(String name, String concept, User creator) {
        this.concept = concept;
        this.name = name;
        this.creator = creator;
    }

    /**
     * sets creation time
     */
    @PrePersist
    void createdAt() {
        this.createdAt = this.updatedAt = new Date();
    }

    /**
     * sets update time
     */
    @PreUpdate
    void updatedAt() {
        this.updatedAt = new Date();
    }

    /**
     *
     * @return name of tag
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of the tag
     * @param name name of the tag
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return tag id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id of the tag
     * @param id tag id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return creator user of tag
     */
    public User getCreator() {
        return creator;
    }

    /**
     * Sets creator user of the tag
     * @param creator creator user
     */
    public void setCreator(User creator) {
        this.creator = creator;
    }

    /**
     *
     * @param o
     * @return true if given object equals to this rtag
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;

        Tag tag = (Tag) o;

        if (getId() != null ? !getId().equals(tag.getId()) : tag.getId() != null) return false;
        return !(getName() != null ? !getName().equals(tag.getName()) : tag.getName() != null);

    }

    /**
     *
     * @return hashcode of tag
     */
    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
