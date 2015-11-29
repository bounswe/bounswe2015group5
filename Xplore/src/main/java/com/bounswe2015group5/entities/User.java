package com.bounswe2015group5.entities;

import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author burak
 */
public class User extends JSONObject{ 
    public User(JSONObject con){
        super(con.toString());
    }
    
    public int getID(){
        return (Integer) get("ID");
    }
    
    public String getEmail(){
        return (String) get("Email");
    }
   
    public String getName(){
        return (String) get("Name");
    }
    
    public String getSurname(){
        return (String) get("Surname");
    }
    
}