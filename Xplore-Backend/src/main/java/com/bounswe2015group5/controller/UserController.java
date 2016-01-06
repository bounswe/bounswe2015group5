package com.bounswe2015group5.controller;

import com.bounswe2015group5.model.Contribution;
import com.bounswe2015group5.model.User;
import com.bounswe2015group5.repository.UserRepo;
import com.bounswe2015group5.service.ContributionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.jsondoc.core.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "The users controller", name = "User Services")
public class UserController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ContributionService contributionService;

    @ApiMethod(description = "returns the user details")
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public @ApiResponseObject User userDetails(@ApiPathParam(name = "username") @PathVariable String username ){
        return userRepo.findOne(username);
    }

    @ApiMethod(description = "returns the user details")
    @RequestMapping(value = "/{username}/contributions", method = RequestMethod.GET)
    public @ApiResponseObject Iterable<Contribution> getContributions(@ApiPathParam(name = "username") @PathVariable String username ){
        User user = userRepo.findOne(username);
        return contributionService.listAllContributionsOfUser(user);
    }

    @ApiMethod(description = "logs out")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public @ApiResponseObject void logOut(HttpServletRequest request) {
        request.getSession().setAttribute("username", null);
    }

    @ApiMethod(description = "logs-in and returns the principal")
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ApiResponseObject User login(@ApiBodyObject @RequestBody UserContext userContext,
                                          HttpServletRequest request)
            throws JsonProcessingException {

        User candidate = userRepo.findOne(userContext.getUsername());
        System.out.println(candidate);

        if (candidate != null && candidate.getPassword().equals(userContext.getPassword()))
        {
            request.getSession().setAttribute("username", candidate);
            return candidate;
        } else
        {
            return null;
        }
    }

    @ApiMethod(description = "returns the current principal")
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public static User currentUser(HttpServletRequest request){
        return (User) request.getSession().getAttribute("username");
    }

    @ApiMethod
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public User save(@ApiBodyObject @RequestBody User user, HttpServletRequest request) {
        userRepo.save(user);
        request.getSession().setAttribute("username", user);
        return user;
    }

    public static class UserContext {
        @ApiObjectField(description = "username", required = true)
        private String username;

        @ApiObjectField(description = "password", required = true)
        private String password;

        public UserContext() {
        }

        public UserContext(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
