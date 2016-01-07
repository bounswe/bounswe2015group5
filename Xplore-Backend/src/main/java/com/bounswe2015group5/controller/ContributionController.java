package com.bounswe2015group5.controller;

import com.bounswe2015group5.model.*;
import com.bounswe2015group5.repository.CommentRepo;
import com.bounswe2015group5.repository.RateRepo;
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

import javax.servlet.http.HttpServletRequest;

/**
 * Controller class for contribution functionality.
 * Uses RestController annotation to be treated as a controller.
 * Uses RequestMapping annotation which is treated as ResponseBody semantically.
 */
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
    @Autowired
    RateRepo rateRepo;

    /**
     * Finds a contribution given its id.
     * ContributionService is used to enable this process.
     * @param id id of the contribution
     * @return returns the corresponding contribution.
     */
    @ApiMethod(description = "returns one contribution with given id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ApiResponseObject Contribution findOne(@ApiPathParam(name = "id") @PathVariable int id) {
        return contributionService.getContributionById(id);
    }

    /**
     * Finds and lists all contributions.
     * @return all the contributions
     */
    @ApiMethod
    @RequestMapping(method = RequestMethod.GET)
    public @ApiResponseObject Iterable<Contribution> findAll() {
        return contributionService.listAllContributions();
    }

    /**
     * Saves the contribution using contributionService
     * Creates the contribution object giving its title,content,reference list and user.
     * @param contributionContext context of the contribution
     * @param uriComponentsBuilder Builder for uriComponents
     * @param request Http Servlet Request
     * @return contribution saved
     */
    @ApiMethod
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ApiResponseObject Contribution save(
            @ApiBodyObject @RequestBody ContributionContext contributionContext, UriComponentsBuilder uriComponentsBuilder, HttpServletRequest request) {
        User user = (User)request.getSession(false).getAttribute("username");
        if(user == null){
            return null;
        }
        Contribution contribution = new Contribution(contributionContext.getTitle(),
                contributionContext.getContent(),contributionContext.getReferenseList(),user);
        contributionService.saveContribution(contribution);

        return contribution;
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(uriComponentsBuilder.path("/contributions/{id}").buildAndExpand(contribution.getId()).toUri());
//        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Class that defines the context of the contribution.
     */
    @ApiObject
    public static class ContributionContext{
        @ApiObjectField(description = "Contribution title", required = true)
        private String title;

        @ApiObjectField(description = "Contribution content", required = true)
        private String content;

        @ApiObjectField(description = "References of the contribution", required = true)
        private String referenseList;

        public ContributionContext() {
        }


        /**
         * Constructor for ContributionContext class
         * @param title title of contribution
         * @param content content of contribution
         * @param referenseList reference list of contribution
         */
        public ContributionContext(String title, String content, String referenseList) {
            this.title = title;
            this.content = content;
            this.referenseList = referenseList;
        }

        /**
         * Get functionality for title of contribution
         * @return title
         */
        public String getTitle() {
            return title;
        }

        /**
         * Set functionality for title of contribution
         * @param title title
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * Get functionality for content of contribution
         * @return content
         */
        public String getContent() {
            return content;
        }

        /**
         * Set functionality for content of contribution
         * @param content content
         */
        public void setContent(String content) {
            this.content = content;
        }

        /**
         * Get functionality for reference list of contribution
         * @return reference list
         */
        public String getReferenseList() {
            return referenseList;
        }

        /**
         * Set functionality for reference list of contribution
         * @param referenseList reference list
         */
        public void setReferenseList(String referenseList) {
            this.referenseList = referenseList;
        }
    }

    /**
     * Deletes a contribution from its repository given its id.
     * Deletes its relations also.
     * Uses contributionService to do this.
     * @param id contribution id
     */
    @ApiMethod
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@ApiPathParam(name = "id") @PathVariable int id) {
        relationService.deleteRelations(relationService.getRelationsByContributionId(id));
        commentRepo.delete(commentRepo.findByContributionId(id));
        contributionService.deleteContribution(id);
    }

    /**
     * Finds the relations of a given contribution
     * @param id contribution id
     * @param pageable pageable
     * @return relations of the contribution
     */
    @ApiMethod(description = "returns all relations of given contribution id")
    @RequestMapping(value = "/{id}/relations", method = RequestMethod.GET)
    public @ApiResponseObject
    Iterable<Relation> findRelationsByContributionID(@ApiPathParam(name = "id") @PathVariable int id, Pageable pageable) {
        return relationService.getRelationsByContributionId(id);
    }

    /**
     * Finds the tags that are related with the contribution given its id.
     * @param id contribution id
     * @return tags of the contribution
     */
    @ApiMethod(description = "returns all tags related to given contribution id")
    @RequestMapping(value = "/{id}/tags", method = RequestMethod.GET)
    public @ApiResponseObject
    Iterable<Tag> findTagsByContributionID(@ApiPathParam(name = "id") @PathVariable int id) {
        return relationService.getTagsByContributionId(id);
    }

    /**
     * Rate functionality of the contribution
     * @param id contribution id
     * @param request HttpServletRequest
     * @return RateResponse
     */
    @ApiMethod(description = "returns all rates related to given contribution id")
    @RequestMapping(value = "/{id}/rates", method = RequestMethod.GET)
    public @ApiResponseObject
    RateResponse rateContribution(@ApiPathParam(name = "id") @PathVariable int id, HttpServletRequest request) {
        Integer up=0,down=0,current=0;
        String username = null;
        try {
            username = UserController.currentUser(request).getUsername();
        } catch (Exception e) {
        }

        System.out.println("--------*************username :    " + username);

        for (UserRate userRate : rateRepo.findByContributionId(id)) {
            if(userRate.getValue()==+1)
                up++;
            else if (userRate.getValue()==-1)
                down++;

            if (userRate.getUser().getUsername().equals(username))
                current = userRate.getValue();
        }
        return new RateResponse(up,down,current);
    }

    /**
     * Adds the rating given to a contribution
     * @param id contribution id
     * @param votingContext votingContext
     * @param request HttpServletRequest
     * @return rateContribution
     */
    @ApiMethod(description = "returns all rates related to given contribution id")
    @RequestMapping(value = "/{id}/rates", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
    public @ApiResponseObject
    RateResponse addRate(@ApiPathParam(name = "id") @PathVariable int id,
                         @ApiBodyObject @RequestBody VotingContext votingContext,
                         HttpServletRequest request) {
        int vote = votingContext.getVote();
        if(vote == 0 || vote ==-1 || vote ==1){
            Contribution contribution = contributionService.getContributionById(id);
            String username = null;
            try {
                username = UserController.currentUser(request).getUsername();

                User user = userRepo.findOne(username);
                rateRepo.save(new UserRate(contribution,user,vote));
            } catch (Exception e) {
            }
        }

        return rateContribution(id,request);
    }

    /**
     * Finds all comments related to given contribution id
     * @param id contribution id
     * @return comments
     */
    @ApiMethod(description = "returns all comments related to given contribution id")
    @RequestMapping(value = "/{id}/comments", method = RequestMethod.GET)
    public @ApiResponseObject
    java.util.List<Comment> findCommentsByContributionID(@ApiPathParam(name = "id") @PathVariable int id) {
        return commentRepo.findByContributionId(id);
    }

    /**
     * Adds a tag to a given contribution
     * @param id contribution id
     * @param tagId tag id
     * @return tag
     */
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

    /**
     * Save functionality for comment
     * @param id contribution id
     * @param commentContext context of comment
     * @param request HttpServletRequest
     * @return comment
     */
    @ApiMethod
    @RequestMapping(value = "/{id}/comments", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ApiResponseObject java.util.List<Comment>
    saveComment(@ApiPathParam(name = "id") @PathVariable int id,
                @ApiBodyObject @RequestBody CommentContext commentContext, HttpServletRequest request) {
        User user = (User)request.getSession(false).getAttribute("username");
        if(user == null){
            return null;
        }
        Contribution contribution = contributionService.getContributionById(id);
        String commentBody = commentContext.getCommentBody();

        Comment comment = new Comment(commentBody,user,contribution);

        commentRepo.save(comment);
        return  commentRepo.findByContributionId(id);
    }

    /**
     * Class that defines context for comment
     */
    @ApiObject
    public static class CommentContext {
        @ApiObjectField(description = "username of creator of the comment", required = true)
        private String username;

        //private int contributionId;
        @ApiObjectField(description = "Comment content", required = true)
        private String commentBody;

        public CommentContext() {
        }

        /**
         * Constructor for CommentContext
         * @param username username
         * @param commentBody commentBody
         */
        public CommentContext(String username, String commentBody) {

            this.username = username;
            this.commentBody = commentBody;
        }

        /**
         * Get functionality for username.
         * @return username
         */
        public String getUsername() {
            return username;
        }

        /**
         * Set functionality for username.
         */
        public void setUsername(String username) {
            this.username = username;
        }

        /**
         * Get functionality for commentbody.
         * @return commentbody
         */
        public String getCommentBody() {
            return commentBody;
        }

        /**
         * Set functionality for commentbody
         * @param commentBody commentbody
         */
        public void setCommentBody(String commentBody) {
            this.commentBody = commentBody;
        }
    }

    /**
     * Class that defines RateResponse
     */
    @ApiObject
    public static class RateResponse {
        private Integer up;
        private Integer down;
        private Integer currentUser;

        public RateResponse() {
            up=down=currentUser=0;
        }

        /**
         * Constructor
         * @param up up
         * @param down down
         * @param currentUser currentUser
         */
        public RateResponse(Integer up, Integer down, Integer currentUser) {
            this.up = up;
            this.down = down;
            this.currentUser = currentUser;
        }

        public Integer getUp() {
            return up;
        }

        public void setUp(Integer up) {
            this.up = up;
        }

        public Integer getDown() {
            return down;
        }

        public void setDown(Integer down) {
            this.down = down;
        }

        public Integer getCurrentUser() {
            return currentUser;
        }

        public void setCurrentUser(Integer currentUser) {
            this.currentUser = currentUser;
        }
    }

    /**
     * Class that defines votingContext.
     */
    @ApiObject
    public static class VotingContext {
        @ApiObjectField(description = "Value of the vote", required = true)
        private Integer vote;

        public VotingContext() {
        }

        public VotingContext(Integer vote) {
            this.vote = vote;
        }

        /**
         * Get functionality for vote
         * @return vote
         */
        public Integer getVote() {
            return vote;
        }

        /**
         * Set functionality for vote
         *
         * @param vote vote
         */
        public void setVote(Integer vote) {
            this.vote = vote;
        }
    }
}
