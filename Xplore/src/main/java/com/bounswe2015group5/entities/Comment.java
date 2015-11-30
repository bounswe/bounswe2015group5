/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe2015group5.entities;

import java.util.Date;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author burak
 */
public class Comment extends JSONObject{ 
    public Comment(JSONObject con){
        super(con.toString());
    }
    
    public Comment(){
        super();
    }
    
    public Comment(Map m){
        super(m);
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
    
    public Comment setContent(String Content){
        if(!isNull("Content")){
            remove("Content");
        }
        put("Content", Content);
        return this;
    }
    
    public Comment setContributionID(int ContributionID){
        if(!isNull("ContributionID")){
            remove("ContributionID");
        }
        put("ContributionID", ContributionID);
        return this;
    }
   
    public Comment setDate(Date date){
        if(!isNull("Date")){
            remove("Date");
        }
        put("Date", date);
        return this;
    }
    
    public Comment setID(int ID){
        if(!isNull("ID")){
            remove("ID");
        }
        put("ID", ID);
        return this;
    }
    
    public Comment setUserID(int UserID){
        if(!isNull("UserID")){
            remove("UserID");
        }
        put("UserID", UserID);
        return this;
    }
    
    public Comment setName(String Name){
       if(!isNull("Name")){
            remove("Name");
        }
        put("Name", Name);
        return this;
    }
    
    public Comment setSurname(String Surname){
        if(!isNull("Surname")){
            remove("Surname");
        }
        put("Surname", Surname);
        return this;
    }
    
    public Comment setType(int Type){
        if(!isNull("Type")){
            remove("Type");
        }
        put("Type", Type);
        return this;
    }
    
}
