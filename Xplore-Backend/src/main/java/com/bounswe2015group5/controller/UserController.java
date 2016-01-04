package com.bounswe2015group5.controller;

import com.bounswe2015group5.model.Contribution;
import com.bounswe2015group5.model.User;
import com.bounswe2015group5.repository.UserRepo;
import com.bounswe2015group5.service.ContributionService;
import com.bounswe2015group5.service.RelationService;
import org.jsondoc.core.annotation.*;
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
    @Autowired
    private ContributionService contributionService;

    @ApiMethod(description = "returns the principal")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public @ApiResponseObject User currentUserDetails(Authentication authentication)  {
        String name = authentication.getName();
        return userRepo.findOne(name);
    }

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
//
//    @ApiMethod(description = "logs-in and returns the principal")
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public @ApiResponseObject User login(Authentication authentication) throws JsonProcessingException {
//        return currentUserName(authentication);
//    }

//    @ApiMethod(description = "logs out")
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public @ApiResponseObject void logOut(HttpServletRequest request) {
////        request.removeHeaderValue( "Authorization" );
////        request.removeAttribute("Authorization");
////        request.removeAttribute("Cookie");
//        request.getSession().getAttributeNames()
//    }
//

//
//    @ApiMethod(description = "logs-in and returns true")
//    @RequestMapping(value = "/log", method = RequestMethod.POST)
//    public @ApiResponseObject Boolean log(@RequestParam(value = "username") String username,
//                                          @RequestParam(value = "password") String password,
//                                          HttpServletRequest request) {
//        // Needs to check the credentials
//        System.out.println(username);
//        request.getSession().setAttribute("username", username);
//        return true;
//    }
//
//    @ApiMethod(description = "returns the username of logged-in user")
//    @RequestMapping(value = "/islog", method = RequestMethod.GET)
//    public @ApiResponseObject String isLog(HttpServletRequest request) {
//        return (String) request.getSession().getAttribute("username");
//    }

//    @ApiMethod(description = "logs out")
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public @ApiResponseObject void logOut(HttpServletRequest request) {
//        request.getSession().setAttribute("username", null);
//    }

    /*
    		    @ApiMethod(description = "logs-in and returns the principal")
    @RequestMapping(value = "/loglogin", method = RequestMethod.POST)
    public @ApiResponseObject void loglogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request)
            throws JsonProcessingException {
        request.getSession().setAttribute("username", username);
    }
    @ApiMethod(description = "logs-in and returns the principal")
    @RequestMapping(value = "/islogin", method = RequestMethod.GET)
    public @ApiResponseObject User islogin(HttpServletRequest request)
            throws JsonProcessingException {
        String username = (String) request.getSession().getAttribute("username");
        User u = new User();
        u.setEmail(username);
        u.setUsername(username);
        return u;
     */

    @ApiMethod
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public User save(@ApiBodyObject @RequestBody User user) {
        userRepo.save(user);
        return user;
    }
}
