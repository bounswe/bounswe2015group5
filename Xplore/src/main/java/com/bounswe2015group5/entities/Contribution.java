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
public class Contribution extends JSONObject{ 
    public Contribution(JSONObject con){
        super(con.toString());
    }
    
    public int getID(){
        return (Integer) get("ID");
    }
    
    public String getTitle(){
        return (String) get("Title");
    }
    
    public String getContent(){
        return (String) get("Content");
    }
   
    public Date getDate(){
        return (Date) get("Date");
    }
    
    public String getName(){
        return (String) get("Name");
    }
    
    public String getSurname(){
        return (String) get("Surname");
    }
    
    public int getUserID(){
        return (Integer) get("UserID");
    }
    
    public int getType(){
        return (Integer) get("Type");
    }
    
    public TagArray getTags(){
        return new TagArray((JSONArray)get("Tags"));
    }
       
}