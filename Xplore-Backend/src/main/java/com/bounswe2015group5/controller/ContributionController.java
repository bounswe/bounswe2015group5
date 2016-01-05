package com.bounswe2015group5.controller;

import com.bounswe2015group5.model.*;
import com.bounswe2015group5.repository.CommentRepo;
import com.bounswe2015group5.repository.UserRepo;
import com.bounswe2015group5.service.ContributionService;
import com.bounswe2015group5.service.RelationService;
import com.bounswe2015group5.service.TagService;
import org.jsondoc.core.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping(value = "/contributions", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "The contributions controller", name = "Contribution Services")
public class ContributionController {

    @Autowired
    ContributionService contributionService;
    @Autowired
    TagService tagService;
    @Autowired
    RelationService relationService;
    @Autowired
    CommentRepo commentRepo;
    @Autowired
    UserRepo userRepo;

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
    public @ApiResponseObject Contribution save(
            @ApiBodyObject @RequestBody ContributionContext contributionContext, UriComponentsBuilder uriComponentsBuilder) {

        User user = userRepo.findOne(contributionContext.getUsername());
        Contribution contribution = new Contribution(contributionContext.getTitle(),
                contributionContext.getContent(),contributionContext.getReferenseList(),user);
        contributionService.saveContribution(contribution);

        return contribution;
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(uriComponentsBuilder.path("/contributions/{id}").buildAndExpand(contribution.getId()).toUri());
//        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @ApiObject
    public static class ContributionContext{
        @ApiObjectField(description = "username of creator of the contribution", required = true)
        private String username;

        @ApiObjectField(description = "Contribution title", required = true)
        private String title;

        @ApiObjectField(description = "Contribution content", required = true)
        private String content;

        @ApiObjectField(description = "References of the contribution", required = true)
        private String referenseList;

        public ContributionContext() {
        }


        public ContributionContext(String username, String title, String content, String referenseList) {
            this.username = username;
            this.title = title;
            this.content = content;
            this.referenseList = referenseList;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getReferenseList() {
            return referenseList;
        }

        public void setReferenseList(String referenseList) {
            this.referenseList = referenseList;
        }
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

    @ApiMethod(description = "returns all comments related to given contribution id")
    @RequestMapping(value = "/{id}/comments", method = RequestMethod.GET)
    public @ApiResponseObject
    java.util.List<Comment> findCommentsByContributionID(@ApiPathParam(name = "id") @PathVariable int id) {
        return commentRepo.findByContributionId(id);
    }

    @ApiMethod(description = "Adds a tag to given contribution")
    @RequestMapping(value = "/{id}/addTag/{tagId}", method = RequestMethod.GET)
    public @ApiResponseObject
    Iterable<Tag> addTagToContribution(@ApiPathParam(name = "id") @PathVariable int id,
                                                         @ApiPathParam(name = "tagId") @PathVariable int tagId) {
        Tag tag = tagService.getTagById(tagId);
        Contribution contribution = contributionService.getContributionById(id);
        User user = userRepo.findOne("hanefi");
        Relation relation = new Relation(tag,contribution,user);

        relationService.saveRelation(relation);

        return relationService.getTagsByContributionId(id);
    }

    @ApiMethod
    @RequestMapping(value = "/{id}/comments", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ApiResponseObject java.util.List<Comment>
    saveComment(@ApiPathParam(name = "id") @PathVariable int id,
                @ApiBodyObject @RequestBody CommentContext commentContext) {
        User user = userRepo.findOne(commentContext.getUsername());
        Contribution contribution = contributionService.getContributionById(id);
        String commentBody = commentContext.getCommentBody();

        Comment comment = new Comment(commentBody,user,contribution);

        commentRepo.save(comment);
        return  commentRepo.findByContributionId(id);
    }

    @ApiObject
    public static class CommentContext {
        @ApiObjectField(description = "username of creator of the comment", required = true)
        private String username;

        //private int contributionId;
        @ApiObjectField(description = "Comment content", required = true)
        private String commentBody;

        public CommentContext() {
        }

        public CommentContext(String username, String commentBody) {

            this.username = username;
            this.commentBody = commentBody;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }


        public String getCommentBody() {
            return commentBody;
        }

        public void setCommentBody(String commentBody) {
            this.commentBody = commentBody;
        }
    }
}
