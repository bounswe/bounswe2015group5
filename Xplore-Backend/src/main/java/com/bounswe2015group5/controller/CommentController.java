package com.bounswe2015group5.controller;

import com.bounswe2015group5.repository.CommentRepo;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/comments", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "The comment controller", name = "Comment Services")
public class CommentController {


    @Qualifier("commentRepo")
    @Autowired
    private CommentRepo commentRepo;

    @ApiMethod
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@ApiPathParam(name = "id") @PathVariable int id) {
        commentRepo.delete(id);
    }
}
