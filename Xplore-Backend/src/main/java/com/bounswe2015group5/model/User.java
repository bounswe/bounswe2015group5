package com.bounswe2015group5.model;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@ApiObject
public class User implements Serializable{

    /**
     * Username of user
     */
    @Id
    @ApiObjectField(description = "username of the user", required = true)
    private String username;

    /**
     * password of user
     */
//    @NotNull
//    @JsonIgnore
    @ApiObjectField(description = "password of the user", required = true)
    private String password;

    /**
     * email of user
     */
//    @NotNull
    @ApiObjectField(description = "email of the user", required = true)
    private String email;

    /**
     * Creates user with given username,password and email
     * @param username
     * @param password
     * @param email
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     *
     * @return username of user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets user name of user
     * @param username of user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public User() {
    }

    /**
     *
     * @return password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password of the user
     * @param password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email of the user
     * @param email email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
