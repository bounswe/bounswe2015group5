#Requirements of the project

# Requirements (Version 1.1) #

## Summary ##

The **Xplore** is a social web platform where users can reach and explore tons of information available on the world wide web, and on earth, without having to suffer from the unstructured nature of the human knowledge. Using this system, users will be able to explore any case about a topic from different perspectives, and infer and exploit relationships about the cases and topics. The cases will be automatically and intuitively related with the people involved, the time and country which it takes place, and the other aspects by the system. And of course, all the mentioned "structured" and "tied" information and their connections will be harvested from the people.


## Glossary (Dictionary) ##

  * **System:** This software project. The "Xplore", the social web platform we plan to realize.

  * **Guest (Unregistered) User:** The user who visits the system to see content without registering.

  * **Registered User (Contributor):**  A user of the system who is registered in the database. He/she may login to the system and post content.

  * **Case:** This is the main type of content posted to the system by the registered users.

  * **Post:**  A contribution by a user. This may be a case, a comment, a rate, a tag, or a question.

  * **Comment:** The posts under a case, stating opinions.

  * **Rate:** Evaluation of a case, a comment, or a tag by a user.

  * **Tag:** Short labels attached to content stating what topic/field is it about.

  * **Question:** Another type of comment, requesting additional information.

  * **Profile:** All the information about a user.

  * **Semantic Search:** Searching a query by its contextual meaning. Considering what user means, rather than what he writes.




# 1. User Requirements #
## 1.1. Functional Requirements ##
  * ### 1.1.1. Functional Requirements for Registered Users ###
    * #### 1.1.1.1. Sign In ####
      * 1.1.1.1.1 Users shall login to the system via their username and password.
      * 1.1.1.1.2 Users shall be able to determine a nickname during the login session.
      * 1.1.1.1.3 Users shall be able to store their password on the system.
      * 1.1.1.1.4 Users shall be able to reset their password in case they forget it since there will be an option such as sending an email with a default password.
    * #### 1.1.1.2. Profile ####
      * 1.1.1.2.1 Users shall indicate their occupation on their profile.
      * 1.1.1.2.2 Users shall be able to edit their profile details such as email address and password, but not be able to change name and surname.
      * 1.1.1.2.3 Users shall be able to add a photograph on the profile.
      * 1.1.1.2.4 Users shall be able to add additional information about themselves except obligatory ones.
      * 1.1.1.2.5 Users shall be able to specify their interests using TAGs.
    * #### 1.1.1.3. Invitation ####
      * 1.1.1.3.1 Users shall be able to invite their friends via their email addresses.
    * #### 1.1.1.4. Case Studies ####
      * 1.1.1.4.1 Users shall be able to create new cases.
      * 1.1.1.4.2 Users shall be able to add time and location to their cases.
      * 1.1.1.4.3 Users shall be able to edit their own cases and the other ones.
      * 1.1.1.4.4 Users shall be able to post a comment on cases.
      * 1.1.1.4.5 Users shall be able to rate cases according to their efficiency and accuracy.
      * 1.1.1.4.6 Users shall be able to search cases according to date,location,and occupation of the person who created the case.
    * #### 1.1.1.5. Tutorial ####
      * 1.1.1.5.1 Users shall be able to use a tutorial which is about "how to use this system".


  * ### 1.1.2. Functional Requirements for Administrator ###
    * #### 1.1.2.1. Privileges ####
      * 1.1.2.1.1 System shall grant users some privileges (create, read, update, delete, open, comment) according to their role(administer, registered user, guest, owner of content).
      * 1.1.2.1.2 Administrator shall have full privileges.
      * 1.1.2.1.3 Administrator shall be able to assign privileges to specific users for specific cases.
      * 1.1.2.1.4 Administrator shall be able to withdraw privileges of specific users for specific cases.
    * #### 1.1.2.2. Regulations ####
      * 1.1.2.2.1 System shall allow administrators to create, update, and delete user profiles.
      * 1.1.2.2.2 Administrator shall be able to combine relevant contents.
    * #### 1.1.2.3. Resign ####
      * 1.1.2.3.1 Administrators shall be able to resign and use system as registered user.
      * 1.1.2.3.2 Administrators shall be able to transfer its duty to another registered user.
  * ### 1.1.3. Functional Requirements for Guest Users ###
    * #### 1.1.3.1. Register ####
      * 1.1.3.1.1 Guest users shall register by writing a valid e-mail address,a username and a desired password.
    * #### 1.1.3.2. Permissions ####
      * 1.1.3.2.1 Guest users shall be able to view all contributions.
      * 1.1.3.2.2 Guest users shall be able to search content by words,tags or usernames.
      * 1.1.3.2.3 Guest users shall be able to search by tags.
      * 1.1.3.2.4 Guest users shall not be able to edit a content.
      * 1.1.3.2.5 Guest users shall not be able to comment on a case.
      * 1.1.3.2.6 Guest users shall not be able to rate the content in terms of its efficiency and accuracy.
    * #### 1.1.3.3. Tutorial ####
      * 1.1.3.3.1 Guest users shall be able to read the tutorial which is about how to use the system.

# 2. System Requirements #
## 2.1. Functional Requirements ##
  * ### 2.1.1. Functional Requirements for Contributions ###
    * #### 2.1.1.1. General ####
      * 2.1.1.1.1 Contributions shall have some contents formatted as URLs, plaintext and/or media.
      * 2.1.1.1.2 Contributions shall be made by only registered users.
      * 2.1.1.1.3 Every contribution shall have a few tags related to its topic.
      * 2.1.1.1.4 Contributions shall be searchable by their tags and contributors.
      * 2.1.1.1.5 Contents shall be voted by registered users.
      * 2.1.1.1.6 Contents shall be linked to each other.
      * 2.1.1.1.7 Some contents shall be categorised as main topic with sub-topics.
    * #### 2.1.1.2. Re-Organization ####
      * 2.1.1.2.1 Contents shall be deleted by their contributors or administrator in case of being irrelevant to the main topic or including misinformation about the main topic.
      * 2.1.1.2.2 Contents shall be combined by administrator in case of being repetition of some other topics.
      * 2.1.1.2.3 Contents shall be editable by their contributors and other registered users.
      * 2.1.1.2.4 A versioning system shall exist in order to make contents editable.
      * 2.1.1.2.5 Original and additional contents shall be seen and voted separately.
    * #### 2.1.1.3. Comments ####
      * 2.1.1.3.1 Contributions shall be commented by registered users.
      * 2.1.1.3.2 Comments of contributions shall be voted.
    * #### 2.1.1.4. Notifications ####
      * 2.1.1.4.1 Contributors shall be informed about additions or comments for their contributions.
  * ### 2.1.2. Functional Requirements for Connection/Interaction ###
    * 2.1.2.1 Contributors shall be able to follow/unfollow other contributors.
    * 2.1.2.2 A contributor shall be informed when a contribution is made by the contributors that he/she follows.
    * 2.1.2.3 Contributors shall be able to block/unblock other contributors.
    * 2.1.2.4 Users shall be able to access the information of how many follower a contributor has.
    * 2.1.2.5 A contributor shall be informed when he/she is followed by a contributor.
  * ### 2.1.3 Functional Requirements for Recommendations ###
    * 2.1.3.1 System shall recommend cases to registered users using the interest TAGs on their profile.
  * ### 2.1.4 Functional Requirements for Semantic Searching ###
    * 2.1.4.1 System shall bring on results in case of a search, not only by word matching, but also considering the contextual meaning of the query.
    * 2.1.4.2 System shall relate the cases and make recommendations through cases not only by tag matching and according to the same topic, but also bring on contextually and semantically related cases as recommendations.

## 2.2 Nonfunctional Requirements ##
  * #### 2.2.1 General ####
    * 2.2.1.1 The system language shall be English
  * #### 2.2.2 Availability ####
    * 2.2.2.1 The system should be implemented as both Android application and web application.
    * 2.2.2.2 The web application shall be supported by Google Chrome,Mozilla Firefox,Safari and Internet Explorer.
    * 2.2.2.3 The system shall work 7 days 24 hours except the maintenance periods.
  * #### 2.2.3 Usability ####
    * 2.2.3.1 There shall be tips for new users to make them integrate the system easily.
    * 2.2.3.2 Applications should have a simple user interface to maintain ease of using.
  * #### 2.2.4 Security ####
    * 2.2.4.1 The user information shall not be shared with any person or third parties.
    * 2.2.4.2 User passwords shall be stored as encrypted.