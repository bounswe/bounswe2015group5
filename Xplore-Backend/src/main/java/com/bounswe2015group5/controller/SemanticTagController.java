package com.bounswe2015group5.controller;

import com.bounswe2015group5.model.Contribution;
import com.bounswe2015group5.model.User;
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
import java.util.List;

@RestController
@RequestMapping(value = "/semanticTag", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "The semantic tag Controller", name = "Semantic Tags")
public class SemanticTagController {

    @ApiMethod(description = "returns related semantic tags")
    @RequestMapping(value = "/getTag", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ApiResponseObject
    List<SemanticTagContext> getTag(@ApiBodyObject @RequestBody TagnameWrapper tagname)
            throws JsonProcessingException {
        // search tagName
        List<SemanticTagContext> tagContextList = new ArrayList<SemanticTagContext>();
        tagContextList.add(new SemanticTagContext(tagname.getName(),"person"));
        tagContextList.add(new SemanticTagContext(tagname.getName(),"person"));
        return tagContextList;
    }

    @ApiMethod(description = "returns the current principal")
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public User currentUser(HttpServletRequest request){
        return (User) request.getSession().getAttribute("username");
    }

    public static class SemanticTagContext {
        @ApiObjectField(description = "name", required = true)
        private String name;

        @ApiObjectField(description = "concept", required = true)
        private String concept;

        public SemanticTagContext() {
        }

        public SemanticTagContext(String name, String concept) {
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

    public static class TagnameWrapper {
        @ApiObjectField(description = "name", required = true)
        private String name;

        public TagnameWrapper(){
        }

        public TagnameWrapper(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
