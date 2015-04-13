# Test Cases #
These are the test cases corresponding to the use cases in the Use Cases page. Each test case has an id: TC# which shows the use case number it belongs to.


## Project Name: Xplore ##

---


### Test Case ID: TC1 ###

**Test Priority:** Medium

**Module Name:** Registration

**Test Title:** Registration

**Description:** Creating an active account in the system

**Test Designed By:**  [Hanefi Önaldı](HanefiOnaldi.md)

**Test Design Date:** 29.03.2015

**Test Executed By:** N/A

**Test Execution Date:** N/A

**Precondition:**
  * Guest User has an e-mail address
  * Guest User chooses a username that is not registered before
  * Guest User is on the log in page

**Dependencies:** N/A

**Steps**

| **Step** | **Test Steps** | **Test Data** | **Expected Result** | **Actual Result** | **Status (Pass/Fail)** | **Notes** |
|:---------|:---------------|:--------------|:--------------------|:------------------|:-----------------------|:----------|
| 1 | Guest User goes to login page.  | URL | Login page is shown. | N/A | N/A |   |
| 2 | Guest User clicks _Register Now_ button | | A registration form is shown containing fields for username, email address, password, and location  | N/A | N/A |  |
| 3 | Guest User fills in the registration form. | username="cRazYb0i\_93" email="mcyakisikli@hotmail.com" password="123456" location="kasimpasa" | System sends verification email | N/A | N/A |  |
| 4 | Guest User reads verification mail. Verifies |  | Guest user is now registered and redirected to main page | N/A | N/A |  |

**Postconditions:**
  * The Guest User is now a Registered User


### Test Case ID: TC2 ###

**Test Priority:** Medium

**Module Name:** Login

**Test Title:** Login Attempt

**Description:** Already registered user provides credentials to login

**Test Designed By:**  [Hanefi Önaldı](HanefiOnaldi.md)

**Test Design Date:** 29.03.2015

**Test Executed By:** N/A

**Test Execution Date:** N/A

**Precondition:**
  * User has already registered successfully

**Dependencies:** N/A

**Steps**

| **Step** | **Test Steps** | **Test Data** | **Expected Result** | **Actual Result** | **Status (Pass/Fail)** | **Notes** |
|:---------|:---------------|:--------------|:--------------------|:------------------|:-----------------------|:----------|
| 1 | User goes to login page.  | URL | Login page is shown containing fields for username, and password. | N/A | N/A |   |
| 2 | User fills in the login form. | username="cRazYb0i\_93" password="123456" | User is now logged in and redirected to his main page | N/A | N/A |  |

**Postconditions:**
  * The User is authenticated and the system displays a his/her home page.
  * User is unable to login for some reason that system reports to user.


### Test Case ID: TC3 ###

**Test Priority:** Medium

**Module Name:** Search

**Test Title:** Search Attempt

**Description:** Guest or registered user attempts to search for a case.

**Test Designed By:**  [Arda Akdemir](ArdaAkdemir.md)

**Test Design Date:** 29.03.2015

**Test Executed By:** N/A

**Test Execution Date:** N/A

**Precondition:**
  * User is viewing the xplore and able to see the search bar.

**Dependencies:** N/A

**Steps**

| **Step** | **Test Steps** | **Test Data** | **Expected Result** | **Actual Result** | **Status (Pass/Fail)** | **Notes** |
|:---------|:---------------|:--------------|:--------------------|:------------------|:-----------------------|:----------|
| 1 | User types a query into the search bar.  | " how to search in xplore"| Search result regarding the query is shown. | N/A | N/A |   |
| 2 | User selects a topic from the search results. | xplore manual | User is directed to the selected topic. | N/A | N/A |  |

**Postconditions:**
  * User sees a list of cases that matches the given searching query.
  * If there is no match for the given searching query, then the system shows a message (No Results Found!) to the user and recommends some popular cases.

### Test Case ID: TC4 ###

**Test Priority:** Medium

**Module Name:** Case Creation

**Test Title:** Case Creation Attempt

**Description:** Registered user attempts to create a case.

**Test Designed By:**  [Arda Akdemir](ArdaAkdemir.md)

**Test Design Date:** 30.03.2015

**Test Executed By:** N/A

**Test Execution Date:** N/A

**Precondition:**
  * User must have an account in the system.
  * User must log in to the system.

**Dependencies:** N/A

**Steps**

| **Step** | **Test Steps** | **Test Data** | **Expected Result** | **Actual Result** | **Status (Pass/Fail)** | **Notes** |
|:---------|:---------------|:--------------|:--------------------|:------------------|:-----------------------|:----------|
| 1 | User clicks on the case creation button.  | URL | A multilined box will appear on the screen for entering the new case. | N/A | N/A |   |
| 2 | User writes the case title and adds tags | title:" Magnus Carlsen"  tags:"chess prodigy" location tag: "Norway" tag: "Chess" | The added tags and title is viewed on the case creation box. | N/A | N/A |  |
| 3 | User clicks the send a case button. | URL |The case is created and stored among other cases. | N/A | N/A |  |

**Postconditions:**
  * Both guests and registered users shall be able to see the cases.
  * Registered users can vote cases by efficiency and accuracy.
  * Registered users can post limitless comment to the cases.
  * Case can be searched via search engine or in the system by typing related words.
  * The newly created case is also treated as a contribution.

### Test Case ID: TC5 ###

**Test Priority:** Medium

**Module Name:** Adding Contribution

**Test Title:** Add Contribution to the Case

**Description:** Verify registered user is able to add a contribution to the already existing case

**Test Designed By:**  [Buket Yılmazel](BuketYilmazel.md)

**Test Design Date:** 29.03.2015

**Test Executed By:** N/A

**Test Execution Date:** N/A

**Pre-conditions:**
  * Registered user is logged in, and on the page of the case to which he/she wants to contribute.

**Dependencies:** N/A

**Steps**

| **Step** | **Test Steps** | **Test Data** | **Expected Result** | **Actual Result** | **Status (Pass/Fail)** | **Notes** |
|:---------|:---------------|:--------------|:--------------------|:------------------|:-----------------------|:----------|
| 1 |  Click on add contribution button placed on the page of the case |  | Contribution part of the case will be appeared | N/A | N/A |   |
| 2 | Complete contribution and click on Send button | text = " the first station of Hisarüstü-Levent subway is Levent" | Contribution will be shown on the case page | N/A | N/A |
| 3 | Add new tags that are different from already added ones (optional) | tag = Hisarüstü-Levent subway | Contribution will be found according to new tags| N/A | N/A |   |

**Post-conditions:** Users contribution will be shown on the case page.

---


### Test Case ID: TC6 ###

**Test Priority:** Medium

**Module Name:** Rating

**Test Title:** Rating a contribution of another user

**Description:** Verify registered user is able to rate a contribution of another user

**Test Designed By:**  [Buket Yılmazel](BuketYilmazel.md)

**Test Design Date:** 29.03.2015

**Test Executed By:** N/A

**Test Execution Date:** N/A

**Pre-conditions:**
  * Registered user must be logged in.
  * Registered user must be on the same page with the contribution.

**Dependencies:** N/A

**Steps**

| **Step** | **Test Steps** | **Test Data** | **Expected Result** | **Actual Result** | **Status (Pass/Fail)** | **Notes** |
|:---------|:---------------|:--------------|:--------------------|:------------------|:-----------------------|:----------|
| 1 |  User click on rating button which is placed under contribution |  | Rating scale will be appeared | N/A | N/A |   |
| 2 | User choose rating for the contribution | rate = "good"| A notification indicating the status of the rating of contribution appears | N/A | N/A |   |
| 3 | Click on Send button |  | Rating will be shown on the contribution | N/A | N/A |   |

**Post-conditions:** Users rating will be visible on the contribution.

---


### **Test Case ID:** TC7 ###

**Test Priority:** Medium

**Module Name:** Following User

**Test Title:** Verify registered user is able to follow and unfollow another one.

**Description:** Test that a registered user can follow and unfollow another one.

**Test Designed By:** [Can Güler](CanGuler.md)

**Test Designed Date:** 29.03.2015

**Test Executed By:** _N/A_

**Test Executed Date:** _N/A_

**Pre-conditions:**
  * Registered user is logged in.

**Dependencies:** _N/A_

**Steps:**
| **Step** | **Test Steps** | **Test Data** | **Expected Result** |  **Actual Result** | **Status (Pass/Fail)** | **Notes** |
|:---------|:---------------|:--------------|:--------------------|:-------------------|:-----------------------|:----------|
| 1 | Navigate to the profile page of the user that will be followed | URL | Profile page of that user is visible with a follow button on it | _N/A_ | _N/A_ |  |
| 2 | Click on the follow button |  | Contributions of the followed user will be notified to actor user. Instead of follow, an unfollow button is displayed on that user's page. | _N/A_ | _N/A_ |  |
| 3 | Click on the unfollow button |  | Contributions of the unfollowed user won't be notified to actor user anymore | _N/A_ | _N/A_ |  |


---


### **Test Case ID:** TC8 ###

**Test Priority:** Medium

**Module Name:** Commenting

**Test Title:** Verify registered user is able to comment on a contribution.

**Description:** Test that a registered user can add his/her comment for a contribution of a case.

**Test Designed By:** [Can Güler](CanGuler.md)

**Test Designed Date:** 29.03.2015

**Test Executed By:** _N/A_

**Test Executed Date:** _N/A_

**Pre-conditions:**
  * Registered user is logged in.

**Dependencies:** _N/A_

**Steps:**
| **Step** | **Test Steps** | **Test Data** | **Expected Result** |  **Actual Result** | **Status (Pass/Fail)** | **Notes** |
|:---------|:---------------|:--------------|:--------------------|:-------------------|:-----------------------|:----------|
| 1 | Navigate to the page of the case on which the contribution that will be commented sits. | URL | The contribution that will be commented is visible. | _N/A_ | _N/A_ |  |
| 2 | Click on the comment button just below the contribution text. |  | Comment button will disappear, a multilined text box will appear instead just below the contribution. | _N/A_ | _N/A_ |  |
| 3 | Write the comment, and click on the send button. | 'Great contribution if you can support it with an evidence' | Indented comment will be visible under the contribution. | _N/A_ | _N/A_ |  |

**Post-conditions:**
  * Both guests and registered users shall be able to see the comments.

---


### **Test Case ID:** TC9 ###

**Test Priority:** Medium

**Module Name:** Rating Comments

**Test Title:** Verify registered users are able to rate comments of a contribution

**Description:** Test that a registered user can rate a comment of a contribution

**Test Designed By:** [Hakan Şahin](HakanSahin.md)

**Test Design Date:** 29.03.2015

**Test Executed By:** N/A

**Test Execution Date:** N/A

**Pre-conditions:**

  * Registered user is logged in.

**Dependencies:** N/A

**Steps:**

| **Step** | **Test Steps** | **Test Data** | **Expected Result** | **Actual Result** | **Status (Pass/Fail)** | **Notes** |
|:---------|:---------------|:--------------|:--------------------|:------------------|:-----------------------|:----------|
| 1 | Navigate to the page containing the contribution | URL | Contribution and its comments are visible. | N/A | N/A |   |
| 2 | Click on one of the comments of the contribution | text = "Hisarüstü-Levent Subway will be opened in the fisrt quarter of 2015" | A popup box containing 2 slider items (for accuracy and efficiency) appears. | N/A | N/A |
| 3 | Arrange sliders and click on the "Submit" button | efficiency=1, accuracy=1, Menu Item Clicked | A notification indicating the status of the rating comment action appears. | N/A | N/A |   |

**Postconditions:**

  * System added new rating to the comment.
  * User's rating is visible on the comment.

---


### **Test Case ID:** TC10 ###

**Test Priority:** Medium

**Module Name:** Tagging

**Test Title:** Verify registered users are able to add tags to an existing case

**Description:** Test that a registered user can add a tag to an existing case

**Test Designed By:**  [Hakan Şahin](HakanSahin.md)

**Test Design Date:** 29.03.2015

**Test Executed By:** N/A

**Test Execution Date:** N/A

**Pre-conditions:**

  * Registered user is logged in.

**Dependencies:** N/A

**Steps:**

| **Step** | **Test Steps** | **Test Data** | **Expected Result** | **Actual Result** | **Status (Pass/Fail)** | **Notes** |
|:---------|:---------------|:--------------|:--------------------|:------------------|:-----------------------|:----------|
| 1 | Navigate to the page containing the case | URL | Case and its tags are visible. | N/A | N/A |   |
| 2 | Click on 'Add Tag' button | Menu Item Clicked | A popup box containing a text field (for tag) appears. | N/A | N/A |
| 3 | Fill the text box and click on the "Submit" button | tag = "Hisarüstü-Levent Subway"<br /> Menu Item Clicked | A notification indicating the status of the tagging action appears and <br /> tag section is updated to include new tag. | N/A | N/A |   |

**Postconditions:**

  * System added new tag to the case.
  * User's tag is visible on the tag section of the case.

---


### **Test Case ID:** TC11 ###

**Test Priority:** Medium

**Module Name:** Rating Tags

**Test Title:** Verify registered user is able to rate tags of a contribution.

**Description:** Test that a registered user can rate a tag of a contribution added by another registered user in terms of accuracy and efficiency.

**Test Designed By:** [Mehmet Burak Kurutmaz](MehmetBurakKurutmaz.md)

**Test Designed Date:** 29.03.2015

**Test Executed By:** _N/A_

**Test Executed Date:** _N/A_

**Pre-conditions:**
  * Registered user is logged in.

**Dependencies:** _N/A_

**Steps:**
| **Step** | **Test Steps** | **Test Data** | **Expected Result** |  **Actual Result** | **Status (Pass/Fail)** | **Notes** |
|:---------|:---------------|:--------------|:--------------------|:-------------------|:-----------------------|:----------|
| 1 | Navigate to the page containing the contribution | URL | Contribution and its tags are visible | _N/A_ | _N/A_ |  |
| 2 | Click on one of the tags of the contribution | tag = "earthquake" | A popup box containing 2 slider items (for accuracy and efficiency) appears. | _N/A_ | _N/A_ |  |
| 3 | Arrange sliders and click on the "Submit" button | efficiency=10, accuracy=10 | A notification indicating the status of the rating action appears. | _N/A_ | _N/A_ |  |

**Post-conditions:**
  * System saved the rating of the tag for further evaluation.
  * User's rating is visible on the tag.


---


### **Test Case ID:** TC12 ###

**Test Priority:** Medium

**Module Name:** Asking Questions

**Test Title:** Verify registered user is able to ask questions about a case.

**Description:** Test that a registered user can request additional and spesific

information about a case by question type of comments.

**Test Designed By:** [Mehmet Burak Kurutmaz](MehmetBurakKurutmaz.md)

**Test Designed Date:** 29.03.2015

**Test Executed By:** _N/A_

**Test Executed Date:** _N/A_

**Pre-conditions:**
  * Registered user is logged in.

**Dependencies:** _N/A_

**Steps:**
| **Step** | **Test Steps** | **Test Data** | **Expected Result** |  **Actual Result** | **Status (Pass/Fail)** | **Notes** |
|:---------|:---------------|:--------------|:--------------------|:-------------------|:-----------------------|:----------|
| 1 | Navigate to the page containing the case | URL | Case and its questions section are visible | _N/A_ | _N/A_ |  |
| 2 | Click on "Ask Question" button |  | A popup box containing a text field (for question) appears. | _N/A_ | _N/A_ |  |
| 3 | Fill the text box and click on the "Submit" button | text = "Can earthquakes be predicted?" | A notification indicating the status of the  asking question action appears and <br />questions section is updated to include new question. | _N/A_ | _N/A_ |  |

**Post-conditions:**
  * System added question to the database.
  * User's question is listed in the questions section of the case.


---


### **Test Case ID:** TC13 ###

**Test Priority:** Medium

**Module Name:** Rating Questions

**Test Title:** Verify registered user is able to rate questions about a case.

**Description:** Test that a registered user can rate an asked question about a case

**Test Designed By:** Melih Demirören

**Test Designed Date:** 29.03.2015

**Test Executed By:** _N/A_

**Test Executed Date:** _N/A_

**Pre-conditions:**
  * Registered user is logged in.

**Dependencies:** _N/A_

**Steps:**
| **Step** | **Test Steps** | **Test Data** | **Expected Result** |  **Actual Result** | **Status (Pass/Fail)** | **Notes** |
|:---------|:---------------|:--------------|:--------------------|:-------------------|:-----------------------|:----------|
| 1 | Navigate to the page containing the case | URL | Case and its questions section are visible | _N/A_ | _N/A_ |  |
| 2 | Click on one of the asked questions | text = "Is biological immortality possible?" | A popup box containing 2 slider items (for accuracy and efficiency) appears. | _N/A_ | _N/A_ |  |
| 3 | Arrange sliders and click on the "Submit" button | efficiency=10, accuracy=10 | A notification indicating the rating action appears. | _N/A_ | _N/A_ |  |

**Post-conditions:**
  * User's rating is visible on the question.
  * System added new rating to the question.


---


### **Test Case ID:** TC14 ###

**Test Priority:** Medium

**Module Name:** Answering Questions

**Test Title:** Verify registered user is able to answer questions about a case.

**Description:** Test that a registered user can answer a question about a case to provide additional and spesific information.

**Test Designed By:** Melih Demirören

**Test Designed Date:** 29.03.2015

**Test Executed By:** _N/A_

**Test Executed Date:** _N/A_

**Pre-conditions:**
  * Registered user is logged in.

**Dependencies:** _N/A_

**Steps:**
| **Step** | **Test Steps** | **Test Data** | **Expected Result** |  **Actual Result** | **Status (Pass/Fail)** | **Notes** |
|:---------|:---------------|:--------------|:--------------------|:-------------------|:-----------------------|:----------|
| 1 | Navigate to the page containing the case | URL | Case and its questions section are visible | _N/A_ | _N/A_ |  |
| 2 | Click on "Answer Question" button |  | "Answer Question" button will disappear, a multilined text box will appear instead just below the question. | _N/A_ | _N/A_ |  |
| 3 | Type the answer and click on "Send" button. | text="Good question, I found this research on lobsters, you can navigate to the link below..." | Answer will be visible under the question. | _N/A_ | _N/A_ |  |

**Post-conditions:**
  * User's answer is visible under the question.


---


### Test Case ID: TC19 ###

**Test Priority:** Medium

**Module Name:** Admin Panel

**Test Title:** Case Deletion

**Description:** Test that an administrator can delete a spesific case from the system

**Test Designed By:** Mustafa Tuğrul Özşahin

**Test Design Date:** 29.03.2015

**Test Executed By:** N/A

**Test Execution Date:** N/A

**Pre-conditions:**
  * Administrator is logged in with clearance to access case records and with delete permission.
  * There must be at least one case in the system.

**Dependencies:** N/A

**Steps**

| **Step** | **Test Steps** | **Test Data** | **Expected Result** | **Actual Result** | **Status (Pass/Fail)** | **Notes** |
|:---------|:---------------|:--------------|:--------------------|:------------------|:-----------------------|:----------|
| 1 | Navigate to admin panel | URL | Case deletion option visible | N/A | N/A |   |
| 2 | Choose case deletion option | URL | Case deletion page appeared, with list of cases and their report counts, and deletion checkboxes | N/A | N/A |   |
| 3 | Choose case(s) to delete | Checkbox checkings & submit button | List of cases, without the deleted ones | N/A | N/A |   |

**Post-conditions:** The deleted cases won't appear again on the system to any user.

---


### Test Case ID: TC20 ###

**Test Priority:** Medium

**Module Name:** Admin Panel

**Test Title:** Case merge

**Description:** Test that an administrator can merge two cases into one case

**Test Designed By:** Mustafa Tuğrul Özşahin

**Test Design Date:** 29.03.2015

**Test Executed By:** N/A

**Test Execution Date:** N/A

**Pre-conditions:**
  * Administrator is logged in with clearance to access case records and with merge permission.
  * There must be at least two cases in the system.

**Dependencies:** N/A

**Steps**

| **Step** | **Test Steps** | **Test Data** | **Expected Result** | **Actual Result** | **Status (Pass/Fail)** | **Notes** |
|:---------|:---------------|:--------------|:--------------------|:------------------|:-----------------------|:----------|
| 1 | Navigate to admin panel | URL | Case merge option visible | N/A | N/A |   |
| 2 | Choose case merge option | URL | Case merge page appeared, with list of cases, and merge checkboxes | N/A | N/A |   |
| 3 | Choose two cases to merge | Exactly two checkbox checkings & submit button | Textbox for the title of the new (merged) case | N/A | N/A |   |
| 4 | Enter title for the new (merged) case| title (text) & submit button | List of cases, decremented by one, with the new title | N/A | N/A |   |
| 5 | Choose more or less than two cases to merge | more or less than two checkbox checkings & submit button | Failure message: you can only merge two cases in one operation | N/A | N/A |   |

**Post-conditions:** The merged two cases will appear as one case in the system, with the given title.

---


### Test Case ID: TC21 ###

**Test Priority:** Medium

**Module Name:** Admin Panel

**Test Title:** Adding Relation

**Description:** Test that a registered user can add a relation between two cases in the system.

**Test Designed By:** Mustafa Tuğrul Özşahin

**Test Design Date:** 29.03.2015

**Test Executed By:** N/A

**Test Execution Date:** N/A

**Pre-conditions:**
  * User is logged in with an activated account.
  * There must be at least two cases in the system.

**Dependencies:** N/A

**Steps**

| **Step** | **Test Steps** | **Test Data** | **Expected Result** | **Actual Result** | **Status (Pass/Fail)** | **Notes** |
|:---------|:---------------|:--------------|:--------------------|:------------------|:-----------------------|:----------|
| 1 | Go to the case's page | URL | Add relation option visible | N/A | N/A |   |
| 2 | Choose add relation option | Menuitem click | Add relation popup box is appeared with a search bar for the case(second) to add relation, and a textbox for entering relation description (in which sense they are related), and a submit button | N/A | N/A |   |
| 3 | Enter half of the other case's name for relation adding, to the search bar | Text | Dropped-down list of the cases starting with the entered text | N/A | N/A |   |
| 4 | Choose one of the dropped down cases | Menuitem click| Auto-completed case name field | N/A | N/A |   |
| 5 | Enter relation description to the description textbox| text | - | N/A | N/A |   |
| 6 | Click submit | menuitem selection| Success message for relation addition, and the newly added case in the related cases list below the case page| N/A | N/A |   |

**Post-conditions:** The two cases will be related in a sense the user states.

---


### Test Case ID: TC22 ###

**Test Priority:** Medium

**Module Name:** Rating A Relation

**Test Title:** Rate a Relation with respect to accuracy

**Description:** Verify registered user is able to rate a relation of an existing case with another one

**Test Designed By:**  [Ömer Ulusal](OmerUlusal.md)

**Test Design Date:** 29.03.2015

**Test Executed By:** N/A

**Test Execution Date:** N/A

**Preconditions:**
  * Registered user must be logged in.
  * Registered user must be on the same page with the case.

**Dependencies:** N/A

**Steps**

| **Step** | **Test Steps** | **Test Data** | **Expected Result** | **Actual Result** | **Status (Pass/Fail)** | **Notes** |
|:---------|:---------------|:--------------|:--------------------|:------------------|:-----------------------|:----------|
| 1 | Registered user clicks the relations of case.  | Menuitem Click | Relations box is shown. | N/A | N/A |   |
| 2 | User clicks the rating button of the specific relation. | 3 stars | Rating bar of the relation is colored & overall rating should change. | N/A | N/A |  |

**Postconditions:**
  * The rated relation will be stronger, or weaker while gathering search results or recommendations according to its average rate.
  * The rate will be shown on the relation.

---


### Test Case ID: TC23 ###

**Test Priority:** Medium

**Module Name:** Semantic Search

**Test Title:** Semantic Search

**Description:** Verify that search mechanism outputs semantic results

**Test Designed By:**  [Ömer Ulusal](OmerUlusal.md)

**Test Design Date:** 29.03.2015

**Test Executed By:** N/A

**Test Execution Date:** N/A

**Precondition:**
  * Registered user must be logged in.

**Dependencies:** N/A

**Steps**

| **Step** | **Test Steps** | **Test Data** | **Expected Result** | **Actual Result** | **Status (Pass/Fail)** | **Notes** |
|:---------|:---------------|:--------------|:--------------------|:------------------|:-----------------------|:----------|
| 1 | User goes to main page.  | URL | Main page is shown. | N/A | N/A |   |
| 2 | User clicks the search bar. | Menuitem Click | Search bar should be chosen. | N/A | N/A |  |
| 3 | User writes the keyword. | "Genel Secimler" | Search bar should be filled with "Genel Secimler" | N/A | N/A |  |
| 4 | User clicks Search button. | Menuitem Click | Search results page is shown, results should include the cases which is semantically similar to 'Genel Secimler' (ex. TBMM) | N/A | N/A |  |

**Postconditions:**
  * The found semantically related cases will be shown on the search results page along with the direct results (found by word matching).

---


### Test Case ID: TC24 ###

**Test Priority:** Medium

**Module Name:** Getting recommendations

**Test Title:** Getting recommendations

**Description:** Verify that recommendation mechanism works properly

**Test Designed By:**  [Ömer Ulusal](OmerUlusal.md)

**Test Design Date:** 29.03.2015

**Test Executed By:** N/A

**Test Execution Date:** N/A

**Precondition:**
  * Registered user must be logged in.

**Dependencies:** N/A

**Steps**

| **Step** | **Test Steps** | **Test Data** | **Expected Result** | **Actual Result** | **Status (Pass/Fail)** | **Notes** |
|:---------|:---------------|:--------------|:--------------------|:------------------|:-----------------------|:----------|
| 1 | User goes to main page.  | URL | Main page is shown & Right side of page shows the recommendations about the user's search history and cases which was visited by him/her. | N/A | N/A |   |