package com.bounswe2015group5.controller;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "The users controller", name = "User Services")
public class UserController {
    @ApiMethod(description = "returns the principal")
    @RequestMapping(value = "/principal", method = RequestMethod.GET)
    public @ApiResponseObject String currentPrincipalUserName(Principal principal) {
        return principal.getName();
    }

    @ApiMethod(description = "returns the principal")
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public @ApiResponseObject String currentUserName(Authentication authentication) {
        return authentication.getName();
    }
}
