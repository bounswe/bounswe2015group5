/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe2015group5.database;

import com.bounswe2015group5.entities.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author burak
 */
public class Update {
    public static int registerContribution(Contribution cont) throws SQLException, ClassNotFoundException {
            DBConnection conn = new DBConnection();
            String sql = "INSERT INTO Contribution(UserID,Title,Content,Type,Date) VALUES(?,?,?,1,CURDATE())";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cont.getUserID());
            stmt.setString(2, cont.getTitle());
            stmt.setString(3, cont.getContent());
            //stmt.setInt(4, cont.getType());
            int result = stmt.executeUpdate();
            registerTags(cont.getTags(),cont.getID());
            stmt.close();
            conn.close();
            return result;
    }
    
    public static int removeContribution(int ID) throws SQLException, ClassNotFoundException {
            DBConnection conn = new DBConnection();
            String sql = "DELETE FROM Contribution WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ID);
            int result = stmt.executeUpdate();
            stmt.close();
            conn.close();
            return result;
    }
    
    public static int registerComment(Comment com) throws SQLException, ClassNotFoundException {
            DBConnection conn = new DBConnection();
            String sql = "INSERT INTO Comment(UserID,ContribID,Content,Type,Date) VALUES(?,?,?,?,CURDATE())";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, com.getUserID());
            stmt.setInt(2, com.getContributionID());
            stmt.setString(3, com.getContent());
            stmt.setInt(4, com.getType());
            int result = stmt.executeUpdate();
            stmt.close();
            conn.close();
            return result;
    }
    
    public static int removeComment(Comment com) throws SQLException, ClassNotFoundException {
            DBConnection conn = new DBConnection();
            String sql = "DELETE FROM Comment WHERE Comment.ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, com.getID());
            int result = stmt.executeUpdate();
            stmt.close();
            conn.close();
            return result;
    }
    
    public static int registerUser(User us,String password) throws SQLException, ClassNotFoundException {
            DBConnection conn = new DBConnection();
            String sql = "INSERT INTO User2(Email,Pass,Name,Surname) VALUES(?,PASSWORD(?),?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, us.getEmail());
            stmt.setString(2, password);
            stmt.setString(3, us.getName());
            stmt.setString(4, us.getSurname());
            int result = stmt.executeUpdate();
            stmt.close();
            conn.close();
            return result;
    }
    
    public static int registerRate(Rate rate) throws SQLException, ClassNotFoundException {
            DBConnection conn = new DBConnection();
            String sql = "INSERT INTO Rate(UserID,ContribID,Rate) VALUES(?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, rate.getUserID());
            stmt.setInt(2, rate.getContributionID());
            stmt.setInt(3, rate.getRate());
            int result = stmt.executeUpdate();
            stmt.close();
            conn.close();
            return result;
    }
    
    public static int removeRate(Rate rate) throws SQLException, ClassNotFoundException {
            DBConnection conn = new DBConnection();
            String sql = "DELETE FROM Rate WHERE Rate.UserID = ? AND Rate.ContributionID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, rate.getUserID());
            stmt.setInt(2, rate.getContributionID());
            int result = stmt.executeUpdate();
            stmt.close();
            conn.close();
            return result;
    }

    public static void registerTags(TagArray tags,int ContributionID) throws SQLException, ClassNotFoundException {
        registerTags(tags);
        DBConnection conn = new DBConnection();
        String sql = "INSERT INTO ContributionTag(ContributionID,TagID)\n"
                 + "SELECT ?,TagID FROM Tag WHERE Tag.TagName = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        for(int i=0;i<tags.length();i++){
            Tag t = new Tag(tags.get(i));
            stmt.setInt(1, ContributionID);
            stmt.setString(2, t.getTagName());
            stmt.executeUpdate();
        }
    }
    
    public static void registerTags(TagArray tags) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String sql = "INSERT IGNORE INTO Tag(TagName) VALUES(?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        for(int i=0;i<tags.length();i++){
            Tag t = new Tag(tags.get(i));
            stmt.setString(1, t.getTagName());
            stmt.executeUpdate();
        }
    }
    
    public static void removeTags(TagArray tags,int ContributionID) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String sql = "DELETE FROM ContributionTag WHERE ContributionID = ? AND TagID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        for(int i=0;i<tags.length();i++){
            Tag t = new Tag(tags.get(i));
            stmt.setInt(1, ContributionID);
            stmt.setInt(2, t.getTagID());
        }
    }
    
    public static void removeTags(TagArray tags) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        String sql = "DELETE FROM Tag WHERE ID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        for(int i=0;i<tags.length();i++){
            Tag t = new Tag(tags.get(i));
            stmt.setInt(2, t.getTagID());
        }
    }
}
