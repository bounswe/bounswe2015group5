package com.bounswe2015group5.controller;

import com.bounswe2015group5.model.Contribution;
import com.bounswe2015group5.model.Relation;
import com.bounswe2015group5.model.Tag;
import com.bounswe2015group5.model.User;
import com.bounswe2015group5.repository.UserRepo;
import com.bounswe2015group5.service.RelationService;
import com.bounswe2015group5.service.TagService;
import org.apache.log4j.Logger;
import org.jsondoc.core.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/tags", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "The tags controller", name = "Tag Services")
public class TagController {

    @Autowired
    TagService tagService;

    @Autowired
    UserRepo userRepo;

    @Autowired
    RelationService relationService;

    private Logger log = Logger.getLogger(TagController.class);

    @ApiMethod
    @RequestMapping(method = RequestMethod.GET)
    public @ApiResponseObject Iterable<Tag> findAll() {
        return tagService.listAllTags();
    }

    @ApiMethod(description = "returns one tag with given id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ApiResponseObject
    Tag findOne(@ApiPathParam(name = "id") @PathVariable int id) {
        return tagService.getTagById(id);
    }

    @ApiMethod
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ApiResponseObject
    Tag save(@ApiBodyObject @RequestBody TagContext tagContext, UriComponentsBuilder uriComponentsBuilder,  HttpServletRequest request) {
        User user = (User)request.getSession(false).getAttribute("username");
        if(user == null){
            return null;
        }
        Tag tag = new Tag(tagContext.getName(),
                tagContext.getConcept(),user);
        Iterable<Tag> allTags = tagService.listAllTags();
        Iterator<Tag> it = allTags.iterator();
        while(it.hasNext()){
            Tag tmp = it.next();
            if(tmp.getName().equals(tag.getName())){
                return null;
            }
        }
        tagService.saveTag(tag);

        /*HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/tags/{id}").buildAndExpand(tag.getId()).toUri());*/
        return tag;
    }

    @ApiMethod
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@ApiPathParam(name = "id") @PathVariable int id) {
        relationService.deleteRelations(relationService.getRelationsByTagId(id));
        tagService.deleteTag(id);
    }

    @ApiMethod(description = "returns all relations of given tag id")
    @RequestMapping(value = "/{id}/relations", method = RequestMethod.GET)
    public @ApiResponseObject
    Iterable<Relation> findRelationsByTagID(@ApiPathParam(name = "id") @PathVariable int id, Pageable pageable) {
        return relationService.getRelationsByTagId(id);
    }

    @ApiMethod(description = "returns all contributions related to given tag id")
    @RequestMapping(value = "/{id}/contributions", method = RequestMethod.GET)
    public @ApiResponseObject
    Iterable<Contribution> findContributionsByTagID(@ApiPathParam(name = "id") @PathVariable int id) {
        return relationService.getContributionsByTagId(id);
    }

    @ApiMethod(description = "returns other tags related to given tag id")
    @RequestMapping(value = "/{id}/tags", method = RequestMethod.GET)
    public @ApiResponseObject
    Map<Integer,Integer> findMostRelatedTagsWithTag(@ApiPathParam(name = "id") @PathVariable int id) {
        return relationService.findMostRelatedTagsWithTag(id);
    }

    @ApiObject
    public static class TagContext{
        @ApiObjectField(description = "name of the tag", required = true)
        private String name;

        @ApiObjectField(description = "concept of the tag", required = true)
        private String concept;

        public TagContext() {
        }

        public TagContext(String name, String concept) {
            this.name = name;
            this.concept = concept;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getConcept() {
            return concept;
        }

        public void setConcept(String concept) {
            this.concept = concept;
        }

    }
}
