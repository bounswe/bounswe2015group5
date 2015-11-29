/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe2015group5.entities;

import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author burak
 */
public class Comment extends JSONObject{ 
    public Comment(JSONObject con){
        super(con.toString());
    }
    
    public String getContent(){
        return (String) get("Content");
    }
    
    public int getContributionID(){
        return (Integer) get("ContributionID");
    }
   
    public Date getDate(){
        return (Date) get("Date");
    }
    
    public int getID(){
        return (Integer) get("ID");
    }
    
    public int getUserID(){
        return (Integer) get("UserID");
    }
    
    public int getName(){
        return (Integer) get("Name");
    }
    
    public int getSurname(){
        return (Integer) get("Surname");
    }
    
    public int getType(){
        return (Integer) get("Type");
    }
    
}
