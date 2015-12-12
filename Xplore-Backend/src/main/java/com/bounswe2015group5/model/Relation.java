package com.bounswe2015group5.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Relation implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RelationID id;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "tagId")
    private Tag tag;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "contributionId")
    private Contribution contribution;

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

        protected Integer tagId;

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

        protected Integer contributionId;

        public RelationID(Integer tagId, Integer contributionId) {
            this.tagId = tagId;
            this.contributionId = contributionId;
        }

        public RelationID() {
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

            if (!tagId.equals(that.tagId)) return false;
            return contributionId.equals(that.contributionId);

        }

        @Override
        public int hashCode() {
            int result = tagId.hashCode();
            result = 31 * result + contributionId.hashCode();
            return result;
        }
    }
}