package com.bounswe2015group5.controller;

import com.bounswe2015group5.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.hp.hpl.jena.vocabulary.DB;
import org.jsondoc.core.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.hp.hpl.jena.query.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/semanticTag", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "The semantic tag Controller", name = "Semantic Tags")
public class SemanticTagController {

    @ApiMethod(description = "returns related semantic tags")
    @RequestMapping(value = "/getTags", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ApiResponseObject
    List<SemanticTagContext> getTags(@ApiBodyObject @RequestBody TagnameWrapper tagname)
            throws JsonProcessingException {
        // search tagName
        List<SemanticTagContext> tagContextList = SemanticTagRequester.exactHyponymOfQuery(tagname.getName());
        return tagContextList;
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

    public static class SemanticTagRequester {
        public static final String PREFIX_NS_WN = "PREFIX id:   <http://wordnet.rkbexplorer.com/id/>\n" +
                "PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX akt:  <http://www.aktors.org/ontology/portal#>\n" +
                "PREFIX owl:  <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX akt:  <http://www.aktors.org/ontology/portal#>\n" +
                "PREFIX akts: <http://www.aktors.org/ontology/support#>\n" +
                "PREFIX wordnet: <http://www.w3.org/2006/03/wn/wn20/schema/>\n";

        public static final String PREFIX_NS_DB = "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
                "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" +
                "PREFIX : <http://dbpedia.org/resource/>\n" +
                "PREFIX dbp: <http://dbpedia.org/property/>\n" +
                "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
                "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n";

        public static final String WORDNET_EP = "http://wordnet.rkbexplorer.com/sparql";
        public static final String DBPEDIA_EP = "http://dbpedia.org/sparql";

        public static final int LIMIT = 15;

        static {
            org.apache.log4j.BasicConfigurator.configure();
        }

        public static List<SemanticTagContext> makeQuery(String queryString,String endpoint){
            ArrayList<SemanticTagContext> result = new ArrayList<>();
            Query query = QueryFactory.create(queryString);
            QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);

            try {
                ResultSet results = qexec.execSelect();
                while(results.hasNext()) {
                    QuerySolution soln = results.nextSolution();
                    SemanticTagContext stg = new SemanticTagContext(soln.get("?lbl").toString(),soln.get("?concept").toString());
                    result.add(stg);
                }
            }
            finally {
                qexec.close();
            }
            return result;
        }

        public static List<SemanticTagContext> hyponymOfQuery(String name){
            String queryString = PREFIX_NS_WN + "SELECT DISTINCT ?lbl,?concept WHERE { " +
                    "                     ?hp wordnet:hyponymOf ?s .\n" +
                    "                     ?hp rdfs:label ?lbl.\n" +
                    "                     ?s rdfs:label ?concept.\n" +
                    "                     filter regex(?lbl,\"" + name + "\",\"i\")\n" +
                    "                     } LIMIT " + LIMIT;
            return makeQuery(queryString,WORDNET_EP);
        }

        public static List<SemanticTagContext> classifiedByQuery(String name){
            String queryString = PREFIX_NS_WN + "SELECT DISTINCT ?lbl,?concept WHERE { " +
                    "                     ?hp wordnet:classifiedByTopic ?s.\n" +
                    "                     ?hp rdfs:label ?lbl.\n" +
                    "                     ?s rdfs:label ?concept.\n" +
                    "                     filter regex(?lbl,\""+name+"\",\"i\")\n" +
                    "                     } LIMIT " + LIMIT;
            return makeQuery(queryString,WORDNET_EP);
        }

        public static List<SemanticTagContext> subClassOfQuery(String name){
            String queryString = PREFIX_NS_DB + "SELECT DISTINCT ?lbl,?concept WHERE {?conceptUri rdf:type owl:Class.\n" +
                    "                ?sub rdfs:subClassOf ?conceptUri .\n" +
                    "                ?conceptUri rdfs:label ?concept .\n" +
                    "                ?sub rdfs:label ?lbl\n" +
                    "            filter regex(?lbl,\"" + name + "\",\"i\")\n" +
                    "            filter (lang(?concept)=\"en\" && lang(?lbl)=\"en\")\n" +
                    "        } LIMIT " + LIMIT;
            return makeQuery(queryString, DBPEDIA_EP);
        }

        public static List<SemanticTagContext> typeOfQuery(String name){
            String queryString = PREFIX_NS_DB + "SELECT DISTINCT ?lbl,?concept {\n" +
                    "   ?name rdf:type ?class .\n" +
                    "?name rdfs:label ?lbl .\n" +
                    "   ?class rdfs:label ?concept.\n" +
                    "filter regex(?lbl,\""+ name +"\",\"i\")\n" +
                    "filter (lang(?concept)=\"en\" && lang(?lbl)=\"en\")\n" +
                    "}LIMIT " + LIMIT;
            return makeQuery(queryString, DBPEDIA_EP);
        }

        public static List<SemanticTagContext> categoryOfQuery(String name){
            String queryString = PREFIX_NS_DB + "SELECT DISTINCT ?lbl,?concept WHERE { ?category rdf:type skos:Concept .\n" +
                    "?category rdfs:label  ?lbl .\n" +
                    "filter regex(?lbl,\""+name+"\",\"i\")\n" +
                    "filter (lang(?lbl)=\"en\")\n" +
                    "} LIMIT " + LIMIT;
            return makeQuery(queryString, DBPEDIA_EP);
        }

        public static List<SemanticTagContext> broaderOfQuery(String name){
            String queryString = PREFIX_NS_DB + "SELECT DISTINCT ?lbl,?concept WHERE { ?category rdf:type skos:Concept .\n" +
                    "?category rdfs:label ?lbl .\n" +
                    "?category skos:broader ?broad .\n" +
                    "?broad rdfs:label ?concept\n" +
                    "filter regex(?lbl,\""+name+"\",\"i\")\n" +
                    "filter (lang(?concept)=\"en\" && lang(?lbl)=\"en\")\n" +
                    "} LIMIT " + LIMIT;
            return makeQuery(queryString, DBPEDIA_EP);
        }

        public static List<SemanticTagContext> exactHyponymOfQuery(String name){
            String queryString = PREFIX_NS_WN + "SELECT DISTINCT ?lbl,?concept WHERE { " +
                    "                     ?hp wordnet:hyponymOf ?s .\n" +
                    "                     ?hp rdfs:label ?lbl.\n" +
                    "                     ?s rdfs:label ?concept.\n" +
                    "                     filter regex(?lbl,\"^" + name + "$\",\"i\")\n" +
                    "                     } LIMIT " + LIMIT;
            return makeQuery(queryString,WORDNET_EP);
        }

        public static List<SemanticTagContext> exactClassifiedByQuery(String name){
            String queryString = PREFIX_NS_WN + "SELECT DISTINCT ?lbl,?concept WHERE { " +
                    "                     ?hp wordnet:classifiedByTopic ?s.\n" +
                    "                     ?hp rdfs:label ?lbl.\n" +
                    "                     ?s rdfs:label ?concept.\n" +
                    "                     filter regex(?lbl,\"^"+name+"$\",\"i\")\n" +
                    "                     } LIMIT " + LIMIT;
            return makeQuery(queryString,WORDNET_EP);
        }

        public static List<SemanticTagContext> exactSubClassOfQuery(String name){
            String queryString = PREFIX_NS_DB + "SELECT DISTINCT ?lbl,?concept WHERE {?conceptUri rdf:type owl:Class.\n" +
                    "                ?sub rdfs:subClassOf ?conceptUri .\n" +
                    "                ?conceptUri rdfs:label ?concept .\n" +
                    "                ?sub rdfs:label ?lbl\n" +
                    "            filter regex(?lbl,\"^" + name + "$\",\"i\")\n" +
                    "            filter (lang(?concept)=\"en\" && lang(?lbl)=\"en\")\n" +
                    "        } LIMIT " + LIMIT;
            return makeQuery(queryString, DBPEDIA_EP);
        }

        public static List<SemanticTagContext> exactTypeOfQuery(String name){
            String queryString = PREFIX_NS_DB + "SELECT DISTINCT ?lbl,?concept {\n" +
                    "   ?name rdf:type ?class .\n" +
                    "   ?name rdfs:label ?lbl .\n" +
                    "   ?class rdfs:label ?concept.\n" +
                    "filter regex(?lbl,\"^"+ name +"$\",\"i\")\n" +
                    "filter (lang(?concept)=\"en\" && lang(?lbl)=\"en\")\n" +
                    "}LIMIT " + LIMIT;
            return makeQuery(queryString, DBPEDIA_EP);
        }

        public static List<SemanticTagContext> exactCategoryOfQuery(String name){
            String queryString = PREFIX_NS_DB + "SELECT DISTINCT ?lbl,?concept WHERE { ?category rdf:type skos:Concept .\n" +
                    "?category rdfs:label  ?lbl .\n" +
                    "filter regex(?lbl,\"^"+name+"$\",\"i\")\n" +
                    "filter (lang(?lbl)=\"en\")\n" +
                    "} LIMIT " + LIMIT;
            return makeQuery(queryString, DBPEDIA_EP);
        }

        public static List<SemanticTagContext> exactBroaderOfQuery(String name){
            String queryString = PREFIX_NS_DB + "SELECT DISTINCT ?lbl,?concept WHERE { ?category rdf:type skos:Concept .\n" +
                    "?category rdfs:label ?lbl .\n" +
                    "?category skos:broader ?broad .\n" +
                    "?broad rdfs:label ?concept\n" +
                    "filter regex(?lbl,\"^"+name+"$\",\"i\")\n" +
                    "filter (lang(?concept)=\"en\" && lang(?lbl)=\"en\")\n" +
                    "} LIMIT " + LIMIT;
            return makeQuery(queryString, DBPEDIA_EP);
        }


    }

}
