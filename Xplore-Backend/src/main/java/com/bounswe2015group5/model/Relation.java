package com.bounswe2015group5.model;

import org.jsondoc.core.annotation.ApiObjectField;
import org.springframework.data.annotation.Reference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Defining class for relation.
 * Implements Serializable interface for ease
 * Entity annotation used to ensure JPA will include any class annotated with it in the persistence management setup.
 */
@Entity
public class Relation implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RelationID id;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "tagId", nullable = false, insertable = false, updatable = false)
    @ApiObjectField(description = "the tag that is stored in the relation", required = true)
    private Tag tag;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "contributionId", nullable = false, insertable = false, updatable = false)
    @ApiObjectField(description = "the contribution that is stored in the relation", required = true)
    private Contribution contribution;

    @ManyToOne
    @JoinColumn(name = "creatorUser", referencedColumnName = "username")
    @ApiObjectField(description = "The User that created this relation", required = true)
    private User creator;

    @Column(name = "created_at")
    @ApiObjectField(description = "The Timestamp when relation is created", required = true)
    public Date createdAt;

    @Column(name = "updated_at")
    @ApiObjectField(description = "The Timestamp when relation is updated", required = true)
    public Date updatedAt;

    /**
     * Assigns createdAt variable the date
     */
    @PrePersist
    void createdAt() {
        this.createdAt = this.updatedAt = new Date();
    }

    /**
     * Assigns updatedAt variable the date
     */
    @PreUpdate
    void updatedAt() {
        this.updatedAt = new Date();
    }

    /**
     * Constructor for Relation
     * Assigns the parameters to fields
     * @param tag tag
     * @param contribution contribution
     * @param creator creator
     */
    public Relation(Tag tag, Contribution contribution, User creator) {
        this.id = new RelationID(tag.getId(),contribution.getId());
        this.tag = tag;
        this.contribution = contribution;
        this.creator = creator;
    }

    public Relation() {
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Contribution getContribution() {
        return contribution;
    }

    public void setContribution(Contribution contribution) {
        this.contribution = contribution;
    }

    public RelationID getId() {
        return id;
    }

    public void setId(RelationID id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Relation{" +
                "id=" + id +
                '}';
    }

    /**
     * Defining class for RelationID
     * Embeddable annotation defines a class whose instances are stored as an intrinsic part of an owning entity and share the identity of the entity.
     */
    @Embeddable
    public static class RelationID implements Serializable {
        private static final long serialVersionUID = 1L;

        @GeneratedValue
        protected Integer tagId;
        @GeneratedValue
        protected Integer contributionId;

        /**
         * Constructor for RelationID
         * @param tagId tagID
         * @param contributionId contributionID
         */
        public RelationID(Integer tagId, Integer contributionId) {
            this.tagId = tagId;
            this.contributionId = contributionId;
        }

        public RelationID() {
        }

        public Integer getContributionId() {
            return contributionId;
        }

        public void setContributionId(Integer contributionId) {
            this.contributionId = contributionId;
        }

        public Integer getTagId() {
            return tagId;
        }

        public void setTagId(Integer tagId) {
            this.tagId = tagId;
        }

        @Override
        public String toString() {
            return tagId + "_" + contributionId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof RelationID)) return false;

            RelationID that = (RelationID) o;

            return getTagId().equals(that.getTagId()) && getContributionId().equals(that.getContributionId());

        }

        @Override
        public int hashCode() {
            int result = tagId.hashCode();
            result = 31 * result + contributionId.hashCode();
            return result;
        }
    }
}