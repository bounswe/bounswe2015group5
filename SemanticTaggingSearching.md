# Semantic Search #



## Introduction ##

Semantic search is searching a query by its contextual meaning. Rather than ordinary searching where the query is searched within the web pages regardless of the meaning semantic search aims to increase search accuracy through including various parameters in the search. These parameters varies among different search engines but still some of them are found in most of them. These parameters include: context of search, location, intent, variation of words, synonyms. Some of these parameters will be explained below.

### Context of Search ###

Context of search is an important parameters in semantic searching. World wide web content is huge and content about almost anything can be found on web. This huge capacity comes with certain drawbacks. Every field has its own terminology thus meaning of certain words vary greatly in different contexts. This is where context of search comes in handy. By finding out the context of the search we can narrow the search and make it more accurate.

### Location ###

The vastness of web content again is the main issue that the location parameter is aimed to prevent. When dealing with such enormous data the search engine developers' main goal is to narrow the data to a reasonable amount. The data must still be kept relevant to be accurate. So the assumption that the searched content is close to the current location in which the search query is made. So the search engine assumes that the search is about the data that is relatively close to the city or country from which the query is made. This helps to increase the accuracy of the search.

### Synonyms ###

Synonyms occur in every language and they are quite abundant. Different data may represent the same content with different headings or names. So the search must consider the synonyms of the query in order to be more accurate.

## Usage Areas of Semantic Searching and Tagging ##
I've come up with a list of eleven approaches that join semantics to search which includes applications using semantic tagging and searching.
### 1. Related searches/queries ###
The engine proposes searches that are in some way similar to the entered search. For example, in response to a search on "carrots," Hakia offers, "You can also search for: Zucchini, Eggplant." Yup, they're all vegetables.
I'd place query correction in this same category, the "Did you mean:" response that leading search engines provide if they detect a likely search-term misspelling.
### 2.Reference results. ###
Here the search engine is responding with materials that define the search terms, simply, via a dictionary look-up, or elaborately, pulling Wikipedia pages (as Bing does) or the like. Note that this isn't search in a traditional sense, is it? It's question-answering, the presumption that the user is probably searching for practical information (like maps, movie reviews and show times, or stock quotes) rather than document hit lists.
### 3.Semantically annotated results ###
Here you're returned pages or documents with highlighting of text features, especially named or pattern-defined entities, that are semantically related to the search terms. This capability is only just emerging for non-textual media... on the Web. Digital cameras can do it: When in portrait mode, they will detect and outline faces.
### 4.Full-text similarity search ###
A block of text ranging from a phrase to a full document, rather than a few keywords, is submitted. While the matching techniques rely on statistical or vector-space similarity measures rather than conventional meaning, the results do fit the semantic label.
### 5.Search on semantic/syntactic annotations ###
The user would tag a search term to indicate the syntactic role the term plays -- for instance, the part-of-speech (noun, verb, etc.) -- or its semantic meaning -- whether it's a company name, location, or event. An IBM page shows how this works:

A key-word search on "center" would likely produce way too many documents because "center" is a common and ambiguous term. Our semantic search engine supports a query language called XML Fragments. This query language is designed to exploit UIMA’s CAS annotations entered in the search engine’s index.
The XML Fragment query, for example,
```
<organization>
    center
 </organization>
```
will produce first only documents that contain "center" where it appears as part of a phrase annotated as an organization by a named-entity recognizer.
This capability extends the search on document-level metadata and tags you can do with mainstream systems such as Google, where you can currently enter filetype:pdf (for example) or would enter terms in a fielded search interface such as the one offered by Google patent advanced search.
### 6.Concept search ###
I enter "Ford films" and among the hits I get are documents that contain the word "movies" even if not the word "films." Conceptual relationships could be specified by a taxonomy or they could be less deterministically inferred by statistical co-occurrence or other, similar techniques.
### 7.Ontology-based search ###
Here the engine not only understands hierarchical relationships of entities and concepts as in a taxonomy, but also more complex inter-entity relationships. "What does a dog chase?" Ontology-based search would bring up results about cars, cats, and tails, as they relate to dogs of course.
### 8.Semantic Web search ###
The Semantic Web seeks to capture data relationships and make the resulting "Web of data" queryable. This lofty and worthy goal is years from practical usability, but you can get a feel for it via Sindice Data Web services.
### 9.Faceted search ###
Faceted search provides a means of exploring results according to a set of predefined, high-level categories called facets. While I will send you to Daniel Tunkelang's The Noisy Channel blog to learn more, I will observe that faceted search is often verticalized, that is, limited or targeted to a particular information domain. Epicurious, a site "for people who love to eat," provides a great example. Try a search on "brownies" and observe the facets listed under "refine this search by..." Semidico, a search tool for biomedical literature, uses facets in its query-completion suggestions and for results delivery, where it offers three tabs of facets on the left side the results screen. In these examples, inferred meaning is the basis for assigning search results to facets.
### 10.Clustered search ###
Clustered search is like faceted search, but without the predefined categories. Check out Carrot2, which organizes search results into topics, as does Clusty from Vivisimo. Here, meaning is inferred from topics extracted from the content of search results.
### 11.Natural language search ###
I first tried out a natural-language query tool around 20 years ago. The goal was to translate a question such as "How much did our inventory of widgets cost us?" into an SQL query against a conventional relational database. The technology, available from companies such as EasyAsk, creates a semantic representation of the user's query, but it has yet to catch on. Noting that we're now habituated to two-to-three word searches, I wonder if it ever will?
These eleven approaches are the types of semantic searching and tagging with examples. I tried to explain semantic searching and tagging by using examples of systems/applications such as Google, natural language processing.

## Useful links ##

http://www.hedden-information.com/SemanticTagging.pdf

http://www.informationweek.com/software/information-management/breakthrough-analysis-two-+-nine-types-of-semantic-search/d/d-id/1086310?

http://www.w3.org/2001/sw/sweo/public/UseCases/Faviki/

## References ##
http://en.wikipedia.org/wiki/Semantic_search