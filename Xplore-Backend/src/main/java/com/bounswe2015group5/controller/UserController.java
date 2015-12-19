package com.bounswe2015group5.controller;

import com.bounswe2015group5.model.User;
import com.bounswe2015group5.repository.UserRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "The users controller", name = "User Services")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @ApiMethod(description = "returns the principal")
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public @ApiResponseObject User currentUserName(Authentication authentication) throws JsonProcessingException {
        String name = authentication.getName();
        return userRepo.findOne(name);

    }

    @ApiMethod(description = "logs-in and returns the principal")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public @ApiResponseObject User login(Authentication authentication) throws JsonProcessingException {
        return currentUserName(authentication);
    }

    @ApiMethod
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void save(@ApiBodyObject @RequestBody User user) {
        userRepo.save(user);
    }
}
