package com.bounswe2015group5.controller;

import com.bounswe2015group5.model.Contribution;
import com.bounswe2015group5.model.Relation;
import com.bounswe2015group5.model.Tag;
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

import java.util.Map;

@RestController
@RequestMapping(value = "/tags", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "The tags controller", name = "Tag Services")
public class TagController {

    @Autowired
    TagService tagService;

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
    ResponseEntity<Void> save(@ApiBodyObject @RequestBody Tag tag, UriComponentsBuilder uriComponentsBuilder) {
        log.info("tag post request : " + tag.getId());
        tagService.saveTag(tag);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/tags/{id}").buildAndExpand(tag.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @ApiMethod
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@ApiPathParam(name = "id") @PathVariable int id) {
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

}
