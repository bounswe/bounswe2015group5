package com.bounswe2015group5.models;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Relation implements Serializable {

    private static final long serialVersionUID = 1L;

    public RelationPK getId() {
        return id;
    }

    public void setId(RelationPK id) {
        this.id = id;
    }

    @EmbeddedId
    private RelationPK id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Relation)) return false;

        Relation relation = (Relation) o;

        return id.equals(relation.id);

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
    public static class RelationPK implements Serializable {
        private static final long serialVersionUID = 1L;

        @ManyToOne
        @Column(name = "tagId", columnDefinition = "tagId")
        private Tag tag;

        @ManyToOne
        @Column(name = "contributionId", columnDefinition = "contributionId")
        private Contribution contribution;

        public RelationPK(Tag tag, Contribution contribution) {
            this.tag = tag;
            this.contribution = contribution;
        }

        public RelationPK(){
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof RelationPK)) return false;

            RelationPK that = (RelationPK) o;

            if (getTag() != null ? !getTag().equals(that.getTag()) : that.getTag() != null) return false;
            return !(getContribution() != null ? !getContribution().equals(that.getContribution()) : that.getContribution() != null);

        }

        @Override
        public int hashCode() {
            int result = getTag() != null ? getTag().hashCode() : 0;
            result = 31 * result + (getContribution() != null ? getContribution().hashCode() : 0);
            return result;
        }
    }
}