/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe2015group5.database;

import com.bounswe2015group5.entities.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author burak
 */
public class Query {

    /*
    public static Contribution getContributionByID(int ID) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String sql = "SELECT \n"
                + "Contribution.ID as ID,\n"
                + "Contribution.Title as Title, \n"
                + "Contribution.Content as Content, \n"
                + "Contribution.Date as Date, \n"
                + "Contribution.Type as Type, \n"
                + "User2.Name as Name, \n"
                + "User2.Surname as Surname\n"
                + "FROM Contribution\n"
                + "INNER JOIN User2\n"
                + "ON Contribution.UserID = User2.ID \n"
                + "WHERE Contribution.ID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, ID);
        Contribution cont = new Contribution(resultSetToJSONObject(stmt.executeQuery()));
        stmt.close();
        conn.close();
        cont.put("Tags", getTagsByContributionID(ID));
        cont.put("Rate", getRateByContributionID(ID));
        return cont;
    }
    */
    
    public static Contribution getContributionByID(int ID) throws SQLException, ClassNotFoundException{
        return getContributionByID(ID,-1);
    }
    
    public static Contribution getContributionByID(int ID,int ClientID) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String inner = "(SELECT \n"
                + "Rate.Rate "
                + "FROM Contribution as Con2\n"
                + "INNER JOIN Rate\n"
                + "ON Con2.ID = Rate.ContributionID \n"
                + "WHERE Con2.ID = Contribution.ID AND Rate.UserID = ?)";
        String sql = "SELECT\n"
                + "Contribution.ID as ID,\n"
                + "Contribution.Title as Title, \n"
                + "Contribution.Content as Content, \n"
                + "Contribution.Date as Date, \n"
                + "Contribution.Type as Type, \n"
                + "User2.Name as Name, \n"
                + "User2.Surname as Surname,\n"
                + inner + " as ClientRate\n"
                + "FROM Contribution\n"
                + "INNER JOIN User2\n"
                + "ON Contribution.UserID = User2.ID \n"
                + "WHERE Contribution.ID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, ClientID);
        stmt.setInt(2, ID);
        Contribution cont = new Contribution(resultSetToJSONObject(stmt.executeQuery()));
        stmt.close();
        conn.close();
        cont.put("Tags", getTagsByContributionID(ID));
        cont.put("Rate", getRateByContributionID(ID));
        return cont;
    }

    public static ContributionArray getContributionsByTagName(String TagName, int ClientID) throws SQLException, ClassNotFoundException {
        Tag tag = getTagByTagName(TagName);
        return getContributionsByTagID(tag.getTagID(),ClientID);
    }

    public static ContributionArray getContributionsByTagID(int TagID, int ClientID) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String inner = "(SELECT \n"
                + "Rate.Rate "
                + "FROM Contribution as Con2\n"
                + "INNER JOIN Rate\n"
                + "ON Con2.ID = Rate.ContributionID \n"
                + "WHERE Con2.ID = Contribution.ID AND Rate.UserID = ?)";
        String sql = "SELECT \n"
                + "Contribution.ID as ID,\n"
                + "Contribution.Title as Title, \n"
                + "Contribution.Content as Content, \n"
                + "Contribution.Date as Date, \n"
                + "Contribution.Type as Type, \n"
                + "User2.Name as Name, \n"
                + "User2.Surname as Surname,\n"
                + inner + " as ClientRate\n"
                + "FROM Contribution\n"
                + "INNER JOIN User2\n"
                + "ON Contribution.UserID = User2.ID \n"
                + "INNER JOIN ContributionTag\n"
                + "ON Contribution.ID = ContributionTag.ContributionID \n"
                + "WHERE ContributionTag.TagID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, ClientID);
        stmt.setInt(2, TagID);
        ContributionArray conarr = new ContributionArray(resultSetToJSONArray(stmt.executeQuery()));
        ContributionArray result = new ContributionArray();
        for (int i = 0; i < conarr.length(); i++) {
            Contribution cont = conarr.getContribution(i);
            cont.put("Tags", getTagsByContributionID(cont.getID()));
            cont.put("Rate", getRateByContributionID(cont.getID()));
            result.put(cont);
        }
        stmt.close();
        conn.close();
        return result;
    }

    public static ContributionArray getContributionsByUserID(int UserID, int ClientID) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String inner = "(SELECT \n"
                + "Rate.Rate "
                + "FROM Contribution as Con2\n"
                + "INNER JOIN Rate\n"
                + "ON Con2.ID = Rate.ContributionID \n"
                + "WHERE Con2.ID = Contribution.ID AND Rate.UserID = ?)";
        String sql = "SELECT \n"
                + "Contribution.ID as ID,\n"
                + "Contribution.Title as Title, \n"
                + "Contribution.Content as Content, \n"
                + "Contribution.Date as Date, \n"
                + "Contribution.Type as Type, \n"
                + "User2.Name as Name, \n"
                + "User2.Surname as Surname,\n"
                + inner + " as ClientRate \n"
                + "FROM Contribution\n"
                + "INNER JOIN User2\n"
                + "ON Contribution.UserID = User2.ID \n"
                + "WHERE User2.ID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, ClientID);
        stmt.setInt(2, UserID);
        ContributionArray conarr = new ContributionArray(resultSetToJSONArray(stmt.executeQuery()));
        ContributionArray result = new ContributionArray();
        for (int i = 0; i < conarr.length(); i++) {
            Contribution cont = conarr.getContribution(i);
            cont.put("Tags", getTagsByContributionID(cont.getID()));
            cont.put("Rate", getRateByContributionID(cont.getID()));
            result.put(cont);
        }
        stmt.close();
        conn.close();
        return result;
    }

    public static CommentArray getCommentsByUserID(int UserID) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String sql = "SELECT \n"
                + "Comment.ID as ID,\n"
                + "Comment.UserID as UserID, \n"
                + "Comment.Content as Content, \n"
                + "Comment.ContribID as ConributionID, \n"
                + "Comment.Date as Date, \n"
                + "Comment.Type as Type, \n"
                + "User2.Name as Name, \n"
                + "User2.Surname as Surname\n"
                + "FROM Comment\n"
                + "INNER JOIN User2\n"
                + "ON Comment.UserID = User2.ID \n"
                + "WHERE User2.ID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, UserID);
        CommentArray result = new CommentArray(resultSetToJSONArray(stmt.executeQuery()));
        stmt.close();
        conn.close();
        return result;
    }

    public static CommentArray getCommentsByContributionID(int ContributionID) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String sql = "SELECT \n"
                + "Comment.ID as ID,\n"
                + "Comment.UserID as UserID, \n"
                + "Comment.Content as Content, \n"
                + "Comment.ContribID as ConributionID, \n"
                + "Comment.Date as Date, \n"
                + "Comment.Type as Type, \n"
                + "User2.Name as Name, \n"
                + "User2.Surname as Surname\n"
                + "FROM Comment\n"
                + "INNER JOIN User2\n"
                + "ON Comment.UserID = User2.ID \n"
                + "WHERE Comment.ContribID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, ContributionID);
        CommentArray result = new CommentArray(resultSetToJSONArray(stmt.executeQuery()));
        stmt.close();
        conn.close();
        return result;
    }

    public static Comment getCommentByID(int ID) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String sql = "SELECT \n"
                + "Comment.ID as ID,\n"
                + "Comment.UserID as UserID, \n"
                + "Comment.Content as Content, \n"
                + "Comment.ContribID as ConributionID, \n"
                + "Comment.Date as Date, \n"
                + "Comment.Type as Type, \n"
                + "User2.Name as Name, \n"
                + "User2.Surname as Surname\n"
                + "FROM Comment\n"
                + "INNER JOIN User2\n"
                + "ON Comment.UserID = User2.ID \n"
                + "WHERE Comment.ID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, ID);
        Comment result = new Comment(resultSetToJSONObject(stmt.executeQuery()));
        stmt.close();
        conn.close();
        return result;
    }

    public static ContributionArray getAllContributions(int ClientID) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String inner = "(SELECT \n"
                + "Rate.Rate "
                + "FROM Contribution as Con2\n"
                + "INNER JOIN Rate\n"
                + "ON Con2.ID = Rate.ContributionID \n"
                + "WHERE Con2.ID = Contribution.ID AND Rate.UserID = ?)";
        String sql = "SELECT \n"
                + "Contribution.ID as ID,\n"
                + "Contribution.Title as Title, \n"
                + "Contribution.Content as Content, \n"
                + "Contribution.Date as Date, \n"
                + "Contribution.Type as Type, \n"
                + "User2.Name as Name, \n"
                + "User2.Surname as Surname,\n"
                + inner + " as ClientRate \n"
                + "FROM Contribution\n"
                + "INNER JOIN User2\n"
                + "ON Contribution.UserID = User2.ID \n";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, ClientID);
        System.out.println(stmt.toString());
        ContributionArray conarr = new ContributionArray(resultSetToJSONArray(stmt.executeQuery()));
        ContributionArray result = new ContributionArray();
        for (int i = 0; i < conarr.length(); i++) {
            Contribution cont = conarr.getContribution(i);
            cont.put("Tags", getTagsByContributionID(cont.getID()));
            cont.put("Rate", getRateByContributionID(cont.getID()));
            result.put(cont);
        }
        stmt.close();
        conn.close();
        return result;
    }

    public static Tag getTagByTagName(String TagName) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String sql = "SELECT \n"
                + "Tag.ID as TagID,\n"
                + "Tag.TagName as TagName\n"
                + "FROM Tag\n"
                + "WHERE Tag.TagName = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, TagName);
        Tag t = new Tag(resultSetToJSONObject(stmt.executeQuery()));
        stmt.close();
        conn.close();
        return t;
    }

    public static Tag getTagByTagID(int TagID) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String sql = "SELECT \n"
                + "Tag.ID as TagID,\n"
                + "Tag.TagName as TagName\n"
                + "FROM Tag\n"
                + "WHERE Tag.TagID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, TagID);
        Tag t = new Tag(resultSetToJSONObject(stmt.executeQuery()));
        stmt.close();
        conn.close();
        return t;
    }

    public static TagArray getTagsByContributionID(int ContributionID) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String sql = "SELECT \n"
                + "Tag.TagName as TagName,\n"
                + "Tag.ID as TagID\n"
                + "FROM ContributionTag\n"
                + "INNER JOIN Tag\n"
                + "ON ContributionTag.TagID = Tag.ID\n"
                + "WHERE ContributionTag.ContributionID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, ContributionID);
        TagArray result = new TagArray(resultSetToJSONArray(stmt.executeQuery()));
        stmt.close();
        conn.close();
        return result;
    }
    
    public static TagArray getAllTags() throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String sql = "SELECT \n"
                + "Tag.TagName as TagName,\n"
                + "Tag.ID as TagID\n"
                + "FROM Tag\n";
        PreparedStatement stmt = conn.prepareStatement(sql);
        TagArray result = new TagArray(resultSetToJSONArray(stmt.executeQuery()));
        stmt.close();
        conn.close();
        return result;
    }

    public static int getRateByContributionID(int ContributionID) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String sql = "SELECT SUM(Rate.Rate) as sum_r\n"
                + "FROM Rate\n"
                + "WHERE Rate.ContributionID = ? \n"
                + "GROUP BY Rate.ContributionID";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, ContributionID);
        int rate = 0;
        try {
            rate = resultSetToJSONObject(stmt.executeQuery()).getInt("sum_r");
        } catch (SQLException | JSONException e) {
        }
        stmt.close();
        conn.close();
        return rate;
    }

    public static RateArray getRatesByUserID(int UserID) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String sql = "SELECT \n"
                + "Rate.UserID as UserID,\n"
                + "Rate.ContributionID as ContributionID,\n"
                + "Rate.Rate as Rate\n"
                + "FROM Rate\n"
                + "WHERE Rate.UserID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, UserID);
        System.out.println(stmt.toString());
        RateArray rates = new RateArray(resultSetToJSONArray(stmt.executeQuery()));
        stmt.close();
        conn.close();
        return rates;
    }

    public static User getUserByID(int ID) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String sql = "SELECT \n"
                + "User2.Name as Name, \n"
                + "User2.Surname as Surname,\n"
                + "User2.ID as ID,\n"
                + "User2.Email as Email \n"
                + "FROM User2\n"
                + "WHERE User2.ID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, ID);
        User us = new User(resultSetToJSONObject(stmt.executeQuery()));
        stmt.close();
        conn.close();
        return us;
    }

    public static User getUserByEmail(String email) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String sql = "SELECT \n"
                + "User2.Name as Name, \n"
                + "User2.Surname as Surname,\n"
                + "User2.ID as ID,\n"
                + "User2.Email as Email \n"
                + "FROM User2\n"
                + "WHERE User2.Email = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        User us = new User(resultSetToJSONObject(stmt.executeQuery()));
        stmt.close();
        conn.close();
        return us;
    }

    public static boolean checkUser(String email) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String sql = "SELECT Email FROM User2 WHERE Email=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        boolean occupied = rs.next();
        stmt.close();
        conn.close();
        return occupied;
    }

    public static User getUserByEmailAndPassword(String email, String password) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String sql = "SELECT \n"
                + "User2.Name as Name, \n"
                + "User2.Surname as Surname,\n"
                + "User2.ID as ID,\n"
                + "User2.Email as Email \n"
                + "FROM User2\n"
                + "WHERE User2.Email = ? AND User2.Pass = PASSWORD(?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, password);
        User us = new User(resultSetToJSONObject(stmt.executeQuery()));
        stmt.close();
        conn.close();
        return us;
    }

    public static LinkedList<JSONObject> JSONArrayToList(JSONArray arr) throws SQLException {
        LinkedList<JSONObject> list = new LinkedList<>();
        if (arr != null) {
            for (int i = 0; i < arr.length(); i++) {
                list.add((JSONObject) arr.get(i));
            }
        }
        return list;
    }

    public static JSONArray resultSetToJSONArray(ResultSet resultSet) throws SQLException {
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i = 0; i < total_rows; i++) {
                obj.put(resultSet.getMetaData().getColumnLabel(i + 1), resultSet.getObject(i + 1));
            }
            jsonArray.put(obj);
        }
        resultSet.close();
        return jsonArray;
    }

    public static JSONObject resultSetToJSONObject(ResultSet resultSet) throws SQLException {
        JSONObject obj = new JSONObject();
        if (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            for (int i = 0; i < total_rows; i++) {
                obj.put(resultSet.getMetaData().getColumnLabel(i + 1), resultSet.getObject(i + 1));
            }
        }
        resultSet.close();
        return obj;
    }
    
    public static JSONObject requestToJSONObject(HttpServletRequest req) {
        JSONObject jsonObj = new JSONObject();
        Map<String, String[]> params = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            String v[] = entry.getValue();
            Object o = (v.length == 1) ? v[0] : v;
            jsonObj.put(entry.getKey(), o);
        }
        return jsonObj;
    }
}