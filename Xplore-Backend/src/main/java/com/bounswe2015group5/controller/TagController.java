package com.bounswe2015group5.controller;

import com.bounswe2015group5.model.Contribution;
import com.bounswe2015group5.model.Relation;
import com.bounswe2015group5.model.Tag;
import com.bounswe2015group5.repository.RelationRepo;
import com.bounswe2015group5.repository.TagRepo;
import com.sun.javafx.tools.packager.Log;
import org.apache.log4j.Logger;
import org.jsondoc.core.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/tags", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "The tags controller", name = "Tag Services")
public class TagController {

    @Autowired
    TagRepo tagRepo;

    @Autowired
    RelationRepo relationRepo;

    private Logger log = Logger.getLogger(TagController.class);

    @ApiMethod
    @RequestMapping(method = RequestMethod.GET)
    public @ApiResponseObject Iterable<Tag> findAll() {
        return tagRepo.findAll();
    }

    @ApiMethod(description = "returns one tag with given id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ApiResponseObject
    Tag findOne(@ApiPathParam(name = "id") @PathVariable int id) {
        return tagRepo.findOne(id);
    }

    @ApiMethod
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ApiResponseObject
    ResponseEntity<Void> save(@ApiBodyObject @RequestBody Tag tag, UriComponentsBuilder uriComponentsBuilder) {
        log.info("tag post request : " + tag.getId());
        tagRepo.save(tag);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/tags/{id}").buildAndExpand(tag.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @ApiMethod
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@ApiPathParam(name = "id") @PathVariable int id) {
        Tag tag = tagRepo.findOne(id);
        tagRepo.delete(tag);
    }

    @ApiMethod(description = "returns all relations of given tag id")
    @RequestMapping(value = "/{id}/relations", method = RequestMethod.GET)
    public @ApiResponseObject
    Page<Relation> findRelationsByTagID(@ApiPathParam(name = "id") @PathVariable int id, Pageable pageable) {
        return relationRepo.findByTagId(id,pageable);
    }

    @ApiMethod(description = "returns all contributions related to given tag id")
    @RequestMapping(value = "/{id}/contributions", method = RequestMethod.GET)
    public @ApiResponseObject
    Page<Contribution> findContributionsByTagID(@ApiPathParam(name = "id") @PathVariable int id, Pageable pageable) {
        return relationRepo.findByContributionId(id,pageable).map(Relation::getContribution);
    }

}
