package com.bounswe2015group5.model;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Defining class for contribution.
 * Implements Serializable interface for ease
 * Entity annotation used to ensure JPA will include any class annotated with it in the persistence management setup.
 */
@Entity
public class Contribution implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiObjectField(description = "id of the contribution", required = true)
    private Integer id;

    @ApiObjectField(description = "title of the contribution", required = true)
    private String title;

    @Column(columnDefinition = "TEXT")
    @ApiObjectField(description = "content of the contribution", required = true)
    private String content;

    @ApiObjectField(description = "referenseList of the contribution", required = false)
    private String referenseList;

    @ManyToOne
    @JoinColumn(name = "creatorUser", referencedColumnName = "username")
    @ApiObjectField(description = "The User that created this contribution", required = true)
    private User creator;

    @Column(name = "created_at")
    @ApiObjectField(description = "The Timestamp when contribution is created", required = true)
    public Date createdAt;

    @Column(name = "updated_at")
    @ApiObjectField(description = "The Timestamp when contribution is updated", required = true)
    public Date updatedAt;

    /**
     * Returns the reference list
     * @return reference list
     */
    public String getReferenseList() {
        return referenseList;
    }

    public void setReferenseList(String referenseList) {
        this.referenseList = referenseList;
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

//    public Contribution(String title, String content, User creator) {
//        this.title = title;
//        this.content = content;
//        this.creator = creator;
//        this.referenseList = "asd";
//    }

    /**
     * Constructor for contribution
     * @param title title
     * @param content content
     * @param referenseList referenseList
     * @param creator creator
     */
    public Contribution(String title, String content, String referenseList, User creator) {
        this.title = title;
        this.content = content;
        this.referenseList = referenseList;
        this.creator = creator;
    }

    public Contribution() {
        createdAt = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    /**
     * Checks for equality
     * @param o object o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contribution)) return false;

        Contribution that = (Contribution) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null)
            return false;
        if (getTitle() != null ? !getTitle().equals(that.getTitle()) : that.getTitle() != null) return false;
        return !(getContent() != null ? !getContent().equals(that.getContent()) : that.getContent() != null);

    }

    /**
     * Returns hash code
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        return result;
    }
}
