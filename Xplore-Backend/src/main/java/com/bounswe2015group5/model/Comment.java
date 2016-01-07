package com.bounswe2015group5.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Defining class for comment.
 * Implements Serializable interface for ease
 * Entity annotation used to ensure JPA will include any class annotated with it in the persistence management setup.
 */
@Entity
@ApiObject
public class Comment implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiObjectField(description = "id of the comment", required = true)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    @ApiObjectField(description = "content of the comment", required = true)
    private String content;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ApiObjectField(description = "the user that created the comment", required = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "contributionId",referencedColumnName = "id")
    @JsonIgnore
    @ApiObjectField(description = "the contribution that this comment is about", required = true)
    private Contribution contribution;

    @Column(name = "created_at")
//    @ApiObjectField(description = "The Timestamp when comment is created", required = true)
    public Date createdAt;

    @Column(name = "updated_at")
//    @ApiObjectField(description = "The Timestamp when comment is updated", required = true)
    public Date updatedAt;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Contribution getContribution() {
        return contribution;
    }

    public void setContribution(Contribution contribution) {
        this.contribution = contribution;
    }

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

    public Comment() {
    }

    /**
     * Constructor for comment class
     * @param content content
     * @param user user
     * @param contribution contribution
     */
    public Comment(String content, User user, Contribution contribution) {
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
}
