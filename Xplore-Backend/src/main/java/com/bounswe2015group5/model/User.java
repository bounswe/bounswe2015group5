package com.bounswe2015group5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@ApiObject
public class User implements Serializable{

    @Id
    @ApiObjectField(description = "username of the user", required = true)
    private String username;

//    @NotNull
//    @JsonIgnore
    @ApiObjectField(description = "password of the user", required = true)
    private String password;

//    @NotNull
    @ApiObjectField(description = "email of the user", required = true)
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
