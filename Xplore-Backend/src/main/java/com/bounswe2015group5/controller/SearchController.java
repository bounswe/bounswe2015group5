package com.bounswe2015group5.controller;

import com.bounswe2015group5.model.*;
import com.bounswe2015group5.repository.RelationRepo;
import com.bounswe2015group5.repository.UserRepo;
import com.bounswe2015group5.service.ContributionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.jsondoc.core.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@RestController
@RequestMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "The search controller", name = "Search Services")
public class SearchController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RelationRepo relationRepo;
    @Autowired
    private ContributionService contributionService;

    @ApiMethod(description = "logs-in and returns the principal")
    @RequestMapping(value = "/advanced", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ApiResponseObject List<Contribution> advanced(@ApiBodyObject @RequestBody SearchContext searchContext,
                                         HttpServletRequest request)
            throws JsonProcessingException {
        LinkedList<Contribution> resp = new LinkedList<>();
        String username = searchContext.getUsername();
        Date beforeDate = searchContext.getBeforeDate();
        Date afterDate = searchContext.getAfterDate();
        String title = searchContext.getTitle();
        String content = searchContext.getContent();
        List<TagController.TagContext> tags=searchContext.getTags();
        Iterable<Contribution> conts = contributionService.listAllContributions();
        for(Contribution cont:conts){
            if(username != null || !username.equals("")){
                if(!cont.getCreator().getUsername().equalsIgnoreCase(username)){
                    continue;
                }
            }
            if(beforeDate != null){
                if(beforeDate.compareTo(cont.createdAt)<0){
                    continue;
                }
            }
            if(afterDate != null){
                if(afterDate.compareTo(cont.createdAt)>0){
                    continue;
                }
            }
            if((title == null || title.equals("")) && (content==null || content.equals("")) && (tags==null || tags.isEmpty())){
                resp.add(cont);
                continue;
            }
            if(title != null){
                if(cont.getTitle().toLowerCase().contains(title.toLowerCase()) && !title.equals("")){
                    resp.add(cont);
                    continue;
                }
            }
            if(content != null){
                if(cont.getContent().toLowerCase().contains(content.toLowerCase()) && !content.equals("")){
                    resp.add(cont);
                    continue;
                }
            }
            if(tags != null){
                List<Relation> rels = relationRepo.findByContributionId(cont.getId());
                if(rels != null){
                    boolean match = false;
                    for (Relation r:rels){
                        Tag rt = r.getTag();
                        for(TagController.TagContext t:tags){
                            if(rt.getConcept().equals(t.getConcept()) || rt.getName().equals(t.getName())){
                                resp.add(cont);
                                match = true;
                                break;
                            }
                        }
                        if (match){
                            break;
                        }
                    }
                }
            }
        }
        return resp;
    }

    public static class SearchContext {
        @ApiObjectField(description = "username", required = false)
        private String username;

        @ApiObjectField(description = "title", required = false)
        private String title;

        @ApiObjectField(description = "after", required = false)
        private Date afterDate;

        @ApiObjectField(description = "before", required = false)
        private Date beforeDate;

        @ApiObjectField(description = "tags", required = true)
        private List<TagController.TagContext> tags;

        @ApiObjectField(description = "tags", required = true)
        private String content;

        public SearchContext(String username, String title, Date afterDate, Date beforeDate, List<TagController.TagContext> tags, String content) {
            this.username = username;
            this.title = title;
            this.afterDate = afterDate;
            this.beforeDate = beforeDate;
            this.tags = tags;
            this.content = content;
        }

        public SearchContext() {

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

        public Date getAfterDate() {
            return afterDate;
        }

        public void setAfterDate(Date afterDate) {
            this.afterDate = afterDate;
        }

        public Date getBeforeDate() {
            return beforeDate;
        }

        public void setBeforeDate(Date beforeDate) {
            this.beforeDate = beforeDate;
        }

        public List<TagController.TagContext> getTags() {
            return tags;
        }

        public void setTags(List<TagController.TagContext> tags) {
            this.tags = tags;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
