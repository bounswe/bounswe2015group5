package com.bounswe2015group5.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Tag implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tagId;

    @Version
    private Integer version;

    private String name;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;

        Tag tag = (Tag) o;

        if (getTagId() != null ? !getTagId().equals(tag.getTagId()) : tag.getTagId() != null) return false;
        if (getVersion() != null ? !getVersion().equals(tag.getVersion()) : tag.getVersion() != null) return false;
        return !(getName() != null ? !getName().equals(tag.getName()) : tag.getName() != null);

    }

    @Override
    public int hashCode() {
        int result = getTagId() != null ? getTagId().hashCode() : 0;
        result = 31 * result + (getVersion() != null ? getVersion().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
