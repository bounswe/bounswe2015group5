/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe2015group5.database;

import com.bounswe2015group5.entities.Comment;
import com.bounswe2015group5.entities.CommentArray;
import com.bounswe2015group5.entities.Contribution;
import com.bounswe2015group5.entities.ContributionArray;
import com.bounswe2015group5.entities.RateArray;
import com.bounswe2015group5.entities.Tag;
import com.bounswe2015group5.entities.TagArray;
import com.bounswe2015group5.entities.User;
import java.sql.ResultSet;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author burak
 */
public class QueryTest {
    
    public QueryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getContributionByID method, of class Query.
     */
    @org.junit.Test
    public void testGetContributionByID_int() throws Exception {
        System.out.println("getContributionByID");
        int ID = 0;
        Contribution expResult = null;
        Contribution result = Query.getContributionByID(ID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContributionByID method, of class Query.
     */
    @org.junit.Test
    public void testGetContributionByID_int_int() throws Exception {
        System.out.println("getContributionByID");
        int ID = 0;
        int ClientID = 0;
        Contribution expResult = null;
        Contribution result = Query.getContributionByID(ID, ClientID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContributionsByTagName method, of class Query.
     */
    @org.junit.Test
    public void testGetContributionsByTagName() throws Exception {
        System.out.println("getContributionsByTagName");
        String TagName = "";
        int ClientID = 0;
        ContributionArray expResult = null;
        ContributionArray result = Query.getContributionsByTagName(TagName, ClientID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContributionsByTagID method, of class Query.
     */
    @org.junit.Test
    public void testGetContributionsByTagID() throws Exception {
        System.out.println("getContributionsByTagID");
        int TagID = 0;
        int ClientID = 0;
        ContributionArray expResult = null;
        ContributionArray result = Query.getContributionsByTagID(TagID, ClientID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContributionsByUserID method, of class Query.
     */
    @org.junit.Test
    public void testGetContributionsByUserID() throws Exception {
        System.out.println("getContributionsByUserID");
        int UserID = 1;
        int ClientID = -1;
        ContributionArray expResult = null;
        ContributionArray result = Query.getContributionsByUserID(UserID, ClientID);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCommentsByUserID method, of class Query.
     */
    @org.junit.Test
    public void testGetCommentsByUserID() throws Exception {
        System.out.println("getCommentsByUserID");
        int UserID = 0;
        CommentArray expResult = null;
        CommentArray result = Query.getCommentsByUserID(UserID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCommentsByContributionID method, of class Query.
     */
    @org.junit.Test
    public void testGetCommentsByContributionID() throws Exception {
        System.out.println("getCommentsByContributionID");
        int ContributionID = 0;
        CommentArray expResult = null;
        CommentArray result = Query.getCommentsByContributionID(ContributionID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCommentByID method, of class Query.
     */
    @org.junit.Test
    public void testGetCommentByID() throws Exception {
        System.out.println("getCommentByID");
        int ID = 0;
        Comment expResult = null;
        Comment result = Query.getCommentByID(ID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllContributions method, of class Query.
     */
    @org.junit.Test
    public void testGetAllContributions() throws Exception {
        System.out.println("getAllContributions");
        int ClientID = 0;
        ContributionArray expResult = null;
        ContributionArray result = Query.getAllContributions(ClientID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTagByTagName method, of class Query.
     */
    @org.junit.Test
    public void testGetTagByTagName() throws Exception {
        System.out.println("getTagByTagName");
        String TagName = "";
        Tag expResult = null;
        Tag result = Query.getTagByTagName(TagName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTagByTagID method, of class Query.
     */
    @org.junit.Test
    public void testGetTagByTagID() throws Exception {
        System.out.println("getTagByTagID");
        int TagID = 0;
        Tag expResult = null;
        Tag result = Query.getTagByTagID(TagID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTagsByContributionID method, of class Query.
     */
    @org.junit.Test
    public void testGetTagsByContributionID() throws Exception {
        System.out.println("getTagsByContributionID");
        int ContributionID = 0;
        TagArray expResult = null;
        TagArray result = Query.getTagsByContributionID(ContributionID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllTags method, of class Query.
     */
    @org.junit.Test
    public void testGetAllTags() throws Exception {
        System.out.println("getAllTags");
        TagArray expResult = null;
        TagArray result = Query.getAllTags();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRateByContributionID method, of class Query.
     */
    @org.junit.Test
    public void testGetRateByContributionID() throws Exception {
        System.out.println("getRateByContributionID");
        int ContributionID = 0;
        int expResult = 0;
        int result = Query.getRateByContributionID(ContributionID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRatesByUserID method, of class Query.
     */
    @org.junit.Test
    public void testGetRatesByUserID() throws Exception {
        System.out.println("getRatesByUserID");
        int UserID = 0;
        RateArray expResult = null;
        RateArray result = Query.getRatesByUserID(UserID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserByID method, of class Query.
     */
    @org.junit.Test
    public void testGetUserByID() throws Exception {
        System.out.println("getUserByID");
        int ID = 0;
        User expResult = null;
        User result = Query.getUserByID(ID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserByEmail method, of class Query.
     */
    @org.junit.Test
    public void testGetUserByEmail() throws Exception {
        System.out.println("getUserByEmail");
        String email = "";
        User expResult = null;
        User result = Query.getUserByEmail(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkUser method, of class Query.
     */
    @org.junit.Test
    public void testCheckUser() throws Exception {
        System.out.println("checkUser");
        String email = "";
        boolean expResult = false;
        boolean result = Query.checkUser(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserByEmailAndPassword method, of class Query.
     */
    @org.junit.Test
    public void testGetUserByEmailAndPassword() throws Exception {
        System.out.println("getUserByEmailAndPassword");
        String email = "";
        String password = "";
        User expResult = null;
        User result = Query.getUserByEmailAndPassword(email, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of JSONArrayToList method, of class Query.
     */
    @org.junit.Test
    public void testJSONArrayToList() throws Exception {
        System.out.println("JSONArrayToList");
        JSONArray arr = null;
        LinkedList<JSONObject> expResult = null;
        LinkedList<JSONObject> result = Query.JSONArrayToList(arr);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resultSetToJSONArray method, of class Query.
     */
    @org.junit.Test
    public void testResultSetToJSONArray() throws Exception {
        System.out.println("resultSetToJSONArray");
        ResultSet resultSet = null;
        JSONArray expResult = null;
        JSONArray result = Query.resultSetToJSONArray(resultSet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resultSetToJSONObject method, of class Query.
     */
    @org.junit.Test
    public void testResultSetToJSONObject() throws Exception {
        System.out.println("resultSetToJSONObject");
        ResultSet resultSet = null;
        JSONObject expResult = null;
        JSONObject result = Query.resultSetToJSONObject(resultSet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of requestToJSONObject method, of class Query.
     */
    @org.junit.Test
    public void testRequestToJSONObject() {
        System.out.println("requestToJSONObject");
        HttpServletRequest req = null;
        JSONObject expResult = null;
        JSONObject result = Query.requestToJSONObject(req);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
