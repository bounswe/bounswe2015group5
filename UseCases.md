# Use Case UML Diagram #
Version 1.1

![http://i.imgur.com/MSA5D6c.png](http://i.imgur.com/MSA5D6c.png)
_Created with ArgoUML_



# Use Cases #
Version 1.0

## Use Case 1 ##
### Name: Registration ###

**Actors:** Guests

**Goal:** Creating an active account in the system

**Preconditions:**
  * User have an e-mail address and an username that is not registered before.

**Steps:**
  * The System prompts the user for a username and password or register new account.
  * User selects register new account option.
  * System prompts the user a registration form that contains fields for username, email address, password, and location.
  * User fills the fields.
  * System checks the provided information and verifies.
  * System sends an activation email to the user.
  * User verifies the email address.

**Postconditions:**
  * The user provided valid information and is returned to the home page as a logged in user.
  * User is unable to register for some reason that system reports to user.

## Use Case 2 ##
### Name: Login ###

**Actors:** Registered Users

**Goal:** User to access his/her account.

**Preconditions:**
  * User have an account, username and password.

**Steps:**
  * The system prompts the user for a username and password or register new account.
  * The user enters his/her username and password.
  * System checks that the provided username is a valid username and provided password is for the provided username.

**Postconditions:**
  * The User is authenticated and the system displays a his/her home page.
  * User is unable to login for some reason that system reports to user.

## Use Case 3 ##
### Name: Search ###

**Actor:** Guest/Registered Users

**Goal:** Search for a Case in the System

**Preconditions:**

  * There must be at least one case in the system.

**Steps:**
  * User types a query about the case that he/she searches for into search bar of the system.
  * The query can contain keywords, tags, location or dates.
  * The system brings up the cases related to searching query.
  * The system can relate a case with searching queries by looking title, tags and content of the case.

**Postconditions:**
  * User sees  a list of cases that matches the given searching query.
  * If there is no match for the given searching query, then the system shows a message (No Results Found!) to the user and recommends some popular cases.

## Use Case 4 ##
### Name: Case Creation ###

**Actors:** Registered users

**Purpose:** Creating and sending a case

**Preconditions:**
  * User must have an account on the system.
  * User must log in to the system.

**Steps:**
  * First, user will click on create a case button and then start to write.
  * Users will add tags for their cases such as time and location.
  * Finally, user will click on send a case button.

**Postconditions:**
  * Both guests and registered users shall be able to see the cases.
  * Registered users can vote cases by efficiency and accuracy.
  * Registered users can post limitless comment to the cases.
  * Case can be searched via search engine or in the system by typing related words.
  * The newly created case is also treated as a **contribution**.

**Exception-conditions:**
  * Users can not vote their cases.
  * Users can not remove their cases.

**Non-behavioural Requirements:**
  * System contains a tutorial part about how to create a case.
  * System encourages users to create a case with its user friendly interface.

## Use Case 5 ##
### Name: Contribution ###

**Actors:** Registered user

**Goal:** Contributing to an already existing case

**Preconditions:**
  * Registered user is logged in, and on the page of the case to which he wants to contribute.

**Steps:**
  * User enters his/her contribution to the text area on the case's page.
  * If s/he finds necessary, adds new tags, keywords, locations and dates other than the default ones (ones already added to case).
  * Then by pressing return or clicking on a Send button, s/he finishes its contribution.

Postconditions:
  * Users contribution will be shown on the case page.

## Use Case 6 ##
### Name: Rating ###

**Actors:** Registered user

**Goal:** Rating a contribution of another user

**Precondition:**
  * Registered user must be logged in.
  * Registered user must be on the same page with the contribution.

**Steps:**

  * User finds the case he is interested in, and reads the case, and also the contributions of that case.

  * User finds the rating section and rates the case and/or contribution.

  * If the user finds the contribution to be misleading and wrong the user should give a bad rating.

  * If the user finds the contribution to be useful and informative the user should give a good rating.

**Postconditions:**
  * Users rating will be visible on the contribution.

## Use Case 7 ##
### Name: Following User ###

**Actors:** Registered users

**Purpose:** Following or unfollowing another user

**Pre-conditions:**
  * User must have active registered account on the system.
  * User must log in to the system.
  * User must find the people who will be followed.

**Steps:**
  * User will click follow button to get notifications from the relevant user.
  * User will click unfollow button (which will be seen instead of unfollow button if the user is already followed) to quit following.

**Post-conditions:**
  * System will notify the user when the followed user enters a new case.

**Exception-conditions:**
  * User cannot follow himself/herself.

**Non-behavioural Requirements:**
  * System gives helping tips to user.
  * System adds information to database.
  * System provides easy interface to user.

## Use Case 8 ##
### Name: Commenting ###

**Actors:** Registered users

**Purpose:** Adding a comment to a case

**Preconditions:**
  * User must have an account on the system.
  * User must be logged in to the system.

**Steps:**
  * First, user will find the case, and read it.
  * User will scroll down to comments section.
  * User will enter a new comment, and submit it.

**Postconditions:**
  * Both guests and registered users shall be able to see the comments.
  * Registered users can vote the entered comments by efficiency and accuracy.


**Exception-conditions:**
  * Users can not vote their comments.

**Non-behavioural Requirements:**
  * System contains a tutorial part about how to make a comment.
  * System encourages users to comment a case with its user friendly interface.

## Use Case 9 ##
### Name: Rating Comments ###
**Actors:** Registered user

**Goal:** Rating a comment of another user, so that it will be evaluated in terms of accuracy/efficiency.

**Precondition:**
  * Registered user must be logged in.
  * Registered user must be on the same page with the contribution.

**Steps:**

  * User finds the comments section and reads the comment.

  * User rates the comment according to his own opinions, in terms of accuracy and/or efficiency and/or informativeness.


**Postconditions:**
  * Users rating will be visible on the comment.


## Use Case 10 ##
### Name: Tagging ###

**Actors:** Registered users

**Purpose:** Adding a tag to an existing case, so that the related topics are revealed.

**Preconditions:**
  * User must have an account on the system.
  * User must be logged in to the system.

**Steps:**
  * First, user will find the case, and read it.
  * User will then find the interface for adding tags.
  * User will add a new tag, which is related to topic.

**Postconditions:**
  * Both guests and registered users shall be able to see the tags.
  * Registered users can vote the entered tags by efficiency and accuracy.
  * The system will then find the case in occurance of a search, using the added tags.

**Non-behavioural Requirements:**
  * System contains a tutorial part about how to add tags.
  * System encourages users to tag a case with its user friendly interface.

## Use Case 11 ##
### Name: Rating Tags ###

**Actors:** Registered user

**Goal:** Rating a tag of a case added by another user, so that it will be evaluated in terms of accuracy/efficiency, and the relatedness of the case to a tag will be revealed.

**Precondition:**
  * Registered user must be logged in.
  * Registered user must be on the same page with the contribution.

**Steps:**

  * User finds the tags of the case.
  * User rates the tag(s) according to his own opinions, in terms of relatedness of that tag to that case.


**Postconditions:**
  * Users rating will be visible on the tag(s).



## Use Case 12 ##
### Name: Asking Questions ###

**Actors:** Registered user

**Goal:** Requesting additional and spesific information about a case.

**Precondition:**
  * Registered user must be logged in.
  * Registered user must be on the same page with the contribution.

**Steps:**

  * User finds the Questions section of the case.
  * User reads the previously asked questions and sees if the question is asked before, and provided the answer.
  * If not, the user posts a question.


**Postconditions:**
  * User's question will be listed in the questions part of the case.

## Use Case 13 ##
### Name: Rating Questions ###

**Actors:** Registered user

**Goal:** Rating a question asked for a case by another user, so that it will be a frequently asked one, instead of asking the same question again.

**Precondition:**
  * Registered user must be logged in.
  * Registered user must be on the same page with the contribution.

**Steps:**

  * User finds the questions section of the case.
  * User reads the questions.
  * User rates a question in terms of goodness and his curiosity about the answer of that question.


**Postconditions:**
  * Users rating will be visible on the questions section.


## Use Case 14 ##
### Name: Answering Questions ###

**Actors:** Registered user

**Goal:** Providing additional and spesific information as an answer to a question about a case.

**Precondition:**
  * Registered user must be logged in.
  * Registered user must be on the same page with the contribution.

**Steps:**

  * User finds the Questions section of the case.
  * User reads the previously asked questions and sees if there is a question he could answer.
  * If so, the user posts an answer to question.


**Postconditions:**
  * User's answer will be listed in the questions part of the case.

## Use Case 15 ##
### Name: Rating Answers ###

**Actors:** Registered user

**Goal:** Rating an answer of a question in terms of correctness/helpfulness.

**Precondition:**
  * Registered user must be logged in.
  * Registered user must be on the same page with the contribution.

**Steps:**

  * User finds the questions section of the case.
  * User reads the questions, and the answers.
  * User rates an answer in terms of in terms of correctness/helpfulness.


**Postconditions:**
  * Users rate will be visible on the answer.


## Use Case 16 ##
### Name: Reporting User ###

**Actors:** Registered user

**Goal:** Reporting a bad behaving user for vandalizm/false information/bad intentions.

**Precondition:**
  * Registered user must be logged in.

**Steps:**

  * User finds out malicious activities of the target user.
  * User chooses "Report User" from the UI.
  * User enters report reason as a short description, and submits.

**Postconditions:**
  * Reported user will be informed to administrator(s).

## Use Case 17 ##
### Name: Reporting Contribution ###

**Actors:** Registered user

**Goal:** Reporting a case and/or contribution for vandalizm, or false information, or violation of rights.

**Precondition:**
  * Registered user must be logged in.

**Steps:**

  * User finds out the malicious contribution.
  * User chooses "Report Contribution" from the UI.
  * User enters report reason as a short description, and submits.

**Postconditions:**
  * Reported contribution will be informed to administrator(s).

## Use Case 18 ##
### Name: Banning User ###

**Actors:** Administrator

**Goal:** Suspending a user's account for a certain amount of time.

**Precondition:**
  * Administrator user must be logged in with admin privileges.

**Steps:**

  * Administrator finds out a user is carrying out bad behavior, from the reports.
  * Chooses to ban the user's account.

**Postconditions:**
  * Banned user isn't able to login for a certain amount of time.

## Use Case 19 ##
### Name: Case Removal ###

**Actors:** Administrator

**Goal:** Removing a case from the system permanently.

**Precondition:**
  * Administrator user must be logged in with admin privileges.

**Steps:**

  * Administrator finds out a Case is falsely informative or violating rights, or carrying out harmful activity from the reports.
  * Chooses to remove the case.

**Postconditions:**
  * Removed case won't be shown on the system anymore.

## Use Case 20 ##
### Name: Case Merge ###

**Actors:** Administrator

**Goal:** Merging two cases into one, which represents the same purpose and information.

**Precondition:**
  * Administrator user must be logged in with admin privileges.

**Steps:**

  * Administrator finds out two certain cases representing the same.
  * Chooses to merge these two cases into one.

**Postconditions:**
  * These two cases will become one case.
  * Contents of the cases will be concatenated in the new one.

## Use Case 21 ##
### Name: Adding Relation ###

**Actors:** Registered User

**Goal:** Pointing out two cases are related to each other.

**Precondition:**
  * User must be logged in.

**Steps:**

  * User finds out or thinks that two cases he read are related to each other.
  * On the case page, chooses "add relation".
  * From the upcoming screen, chooses the other case.
  * Enters the relation tags and/or relation type.
  * Submits the relation information.

**Postconditions:**
  * These two cases will become related in a sense which user states.

## Use Case 22 ##
### Name: Rating a Relation ###

**Actors:** Registered user

**Goal:** Rating a relation of a pair of two cases added by another user, so that it will be evaluated in terms of accuracy/efficiency.

**Precondition:**
  * Registered user must be logged in.
  * Registered user must be on the same page with the case.

**Steps:**

  * User finds the relations to other cases on the page of the case.
  * User rates the relation according to his own opinions, in terms of accuracy of that relation of these two cases.

**Postconditions:**
  * The rated relation will be stronger, or weaker while gathering search results or recommendations according to its average rate.
  * The rate will be shown on the relation.

## Use Case 23 ##
### Name: Semantic Search ###

**Actors:** Registered user

**Goal:** Getting search results not only by word match, but also by the contextual meaning of the words in the search query.

**Precondition:**
  * Registered user must be logged in.

**Steps:**

  * User enters keywords for searching, just like he does for the regular search.
  * System finds the cases having tags which are semantically related to the keywords the user are searching.

**Postconditions:**
  * The found semantically related cases will be shown on the search results page along with the direct results (found by word matching).

## Use Case 24 ##
### Name: Getting recommendations ###

**Actors:** Registered user

**Goal:** Getting the possible most interested cases according to preferences, and latest case views.

**Precondition:**
  * Registered user must be logged in.

**Steps:**

  * On the main page, user sees the recommended cases for him, as he is logged in, on his news feed.

**Postconditions:**
  * none.