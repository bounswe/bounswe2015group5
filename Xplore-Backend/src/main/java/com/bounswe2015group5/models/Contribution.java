package com.bounswe2015group5.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Contribution implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer contributionId;

    @Version
    private Integer version;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    public Integer getContributionId() {
        return contributionId;
    }

    public void setContributionId(Integer contributionId) {
        this.contributionId = contributionId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contribution)) return false;

        Contribution that = (Contribution) o;

        if (getContributionId() != null ? !getContributionId().equals(that.getContributionId()) : that.getContributionId() != null)
            return false;
        if (getVersion() != null ? !getVersion().equals(that.getVersion()) : that.getVersion() != null) return false;
        if (getTitle() != null ? !getTitle().equals(that.getTitle()) : that.getTitle() != null) return false;
        return !(getContent() != null ? !getContent().equals(that.getContent()) : that.getContent() != null);

    }

    @Override
    public int hashCode() {
        int result = getContributionId() != null ? getContributionId().hashCode() : 0;
        result = 31 * result + (getVersion() != null ? getVersion().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        return result;
    }
}
