package com.bounswe2015group5.controller;

import com.bounswe2015group5.model.Contribution;
import com.bounswe2015group5.model.Relation;
import com.bounswe2015group5.model.Tag;
import com.bounswe2015group5.service.ContributionService;
import com.bounswe2015group5.service.RelationService;
import org.jsondoc.core.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping(value = "/contributions", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "The contributions controller", name = "Contribution Services")
public class ContributionController {

    @Autowired
    ContributionService contributionService;

    @Autowired
    RelationService relationService;

    @ApiMethod(description = "returns one contribution with given id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ApiResponseObject Contribution findOne(@ApiPathParam(name = "id") @PathVariable int id) {
        return contributionService.getContributionById(id);
    }

    @ApiMethod
    @RequestMapping(method = RequestMethod.GET)
    public @ApiResponseObject Iterable<Contribution> findAll() {
        return contributionService.listAllContributions();
    }

    @ApiMethod
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ApiResponseObject ResponseEntity<Void> save(@ApiBodyObject @RequestBody Contribution contribution, UriComponentsBuilder uriComponentsBuilder) {
        contributionService.saveContribution(contribution);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/contributions/{id}").buildAndExpand(contribution.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @ApiMethod
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@ApiPathParam(name = "id") @PathVariable int id) {
        contributionService.deleteContribution(id);
    }

    @ApiMethod(description = "returns all relations of given contribution id")
    @RequestMapping(value = "/{id}/relations", method = RequestMethod.GET)
    public @ApiResponseObject
    Iterable<Relation> findRelationsByTagID(@ApiPathParam(name = "id") @PathVariable int id, Pageable pageable) {
        return relationService.getRelationsByTagId(id);
    }

    @ApiMethod(description = "returns all tags related to given contribution id")
    @RequestMapping(value = "/{id}/tags", method = RequestMethod.GET)
    public @ApiResponseObject
    Iterable<Tag> findTagsByContributionID(@ApiPathParam(name = "id") @PathVariable int id) {
        return relationService.getTagsByContributionId(id);
    }
}
