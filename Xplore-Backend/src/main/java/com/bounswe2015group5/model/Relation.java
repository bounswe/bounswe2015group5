package com.bounswe2015group5.model;

import org.springframework.data.annotation.Reference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
public class Relation implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RelationID id;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "tagId", nullable = false, insertable = false, updatable = false)
    private Tag tag;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "contributionId", nullable = false, insertable = false, updatable = false)
    private Contribution contribution;

    @ManyToOne
    @JoinColumn(name = "creatorUser", referencedColumnName = "username")
    private User creator;

    @Column(name = "created_at")
    public Date createdAt;

    @Column(name = "updated_at")
    public Date updatedAt;

    @PrePersist
    void createdAt() {
        this.createdAt = this.updatedAt = new Date();
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = new Date();
    }

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

    @Embeddable
    public static class RelationID implements Serializable {
        private static final long serialVersionUID = 1L;

        @GeneratedValue
        protected Integer tagId;
        @GeneratedValue
        protected Integer contributionId;

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