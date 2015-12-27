package com.bounswe2015group5.model;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@ApiObject
public class Comment implements Serializable{
    @EmbeddedId
    private CommentId id;

    @Column(columnDefinition = "TEXT")
    @ApiObjectField(description = "content of the comment", required = true)
    private String content;

    @ManyToOne
    @MapsId("username")
    @JoinColumn(name = "username", nullable = false, insertable = false, updatable = false)
    @ApiObjectField(description = "the user that created the comment", required = true)
    private User user;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "contributionId", nullable = false, insertable = false, updatable = false)
    @ApiObjectField(description = "the contribution that this comment is about", required = true)
    private Contribution contribution;

    @Column(name = "created_at")
//    @ApiObjectField(description = "The Timestamp when comment is created", required = true)
    public Date createdAt;

    @Column(name = "updated_at")
//    @ApiObjectField(description = "The Timestamp when comment is updated", required = true)
    public Date updatedAt;

    @PrePersist
    void createdAt() {
        this.createdAt = this.updatedAt = new Date();
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = new Date();
    }

    public Comment() {
    }

    public Comment(String content, User user, Contribution contribution) {
        this.id = new CommentId(user.getUsername(),contribution.getId());
        this.content = content;
        this.user = user;
        this.contribution = contribution;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static class CommentId implements Serializable{

        @GeneratedValue
        protected String username;

        @GeneratedValue
        protected  Integer contributionId;

        public CommentId() {
        }

        public CommentId(String username, Integer contributionId) {
            this.username = username;
            this.contributionId = contributionId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Integer getContributionId() {
            return contributionId;
        }

        public void setContributionId(Integer contributionId) {
            this.contributionId = contributionId;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CommentId commentId = (CommentId) o;

            if (!username.equals(commentId.username)) return false;
            return contributionId.equals(commentId.contributionId);

        }

        @Override
        public int hashCode() {
            int result = username.hashCode();
            result = 31 * result + contributionId.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return username + '_' + contributionId;
        }
    }
}
