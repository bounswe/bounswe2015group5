/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe2015group5.entities;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;
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
    
    public Contribution(){
        super();
    }
    
    public Contribution(Map m) {
        super(m);
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
        return new TagArray(get("Tags").toString());
    }
    
    public int getRate(){
        return (Integer) get("Rate");
    }
    
    public Contribution setID(int ID){
        if(!isNull("ID")){
            remove("ID");
        }
        put("ID", ID);
        return this;
    }
    
    public Contribution setTitle(String Title){
        if(!isNull("Title")){
            remove("Title");
        }
        put("Title", Title);
        return this;
    }
    
    public Contribution setContent(String Content){
        if(!isNull("Content")){
            remove("Content");
        }
        put("Content", Content);
        return this;
    }
   
    public Contribution setDate(Date date){
        if(!isNull("Date")){
            remove("Date");
        }
        put("Date", date);
        return this;
    }
    
    public Contribution setName(String Name){
        if(!isNull("Name")){
            remove("Name");
        }
        put("Name", Name);
        return this;
    }
    
    public Contribution setSurname(String Surname){
        if(!isNull("Surname")){
            remove("Surname");
        }
        put("Surname", Surname);
        return this;
    }
    
    public Contribution setUserID(int UserID){
        if(!isNull("UserID")){
            remove("UserID");
        }
        put("UserID", UserID);
        return this;
    }
    
    public Contribution setType(int Type){
       if(!isNull("Type")){
            remove("Type");
        }
        put("Type", Type);
        return this;
    }
    
    public Contribution setTags(TagArray Tags){
       if(!isNull("Tags")){
            remove("Tags");
        }
        put("Tags", Tags);
        return this;
    }
    
    public Contribution setRate(int rate){
       if(!isNull("Rate")){
            remove("Rate");
        }
        put("Rate", rate);
        return this;
    }
    
    public static Contribution stringToContribution(String text){
        JSONObject jsonObj = new JSONObject(text);
        Contribution cont = new Contribution();
        cont.setTitle(jsonObj.getString("Title"));
        cont.setContent(jsonObj.getString("Content"));
        JSONArray tags = jsonObj.getJSONArray("Tags");
        TagArray ts = new TagArray(tags);
        cont.setTags(ts);
        return cont;
    }
       
}