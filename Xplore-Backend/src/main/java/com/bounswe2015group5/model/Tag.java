package com.bounswe2015group5.model;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Tag implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiObjectField(description = "id of the tag", required = true)
    private Integer id;

    @ApiObjectField(description = "name of the tag", required = true)
    private String name;

    @ApiObjectField(description = "concept of the tag", required = true)
    private String concept;

    @Column(name = "created_at")
    @ApiObjectField(description = "The Timestamp when tag is created", required = true)
    public Date createdAt;

    @Column(name = "updated_at")
    @ApiObjectField(description = "The Timestamp when tag is updated", required = true)
    public Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "creatorUser", referencedColumnName = "username")
    @ApiObjectField(description = "The User that created this tag", required = true)
    private User creator;

    public Tag() {
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Tag(String name, String concept, User creator) {
        this.concept = concept;
        this.name = name;
        this.creator = creator;
    }

    @PrePersist
    void createdAt() {
        this.createdAt = this.updatedAt = new Date();
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;

        Tag tag = (Tag) o;

        if (getId() != null ? !getId().equals(tag.getId()) : tag.getId() != null) return false;
        return !(getName() != null ? !getName().equals(tag.getName()) : tag.getName() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
