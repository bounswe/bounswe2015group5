package com.bounswe2015group5.entities;

import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author burak
 */
public class User extends JSONObject{
    
    public User(){
        super();
    }
    
    public User(JSONObject con){
        super(con.toString());
    }
    
    public User(Map m){
        super(m);
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
    
    public User setID(int ID){
        if(!isNull("ID")){
            remove("ID");
        }
        put("ID", ID);
        return this;
    }
    
    public User setEmail(String Email){
        if(!isNull("Email")){
            remove("Email");
        }
        put("Email", Email);
        return this;
    }
   
    public User setName(String Name){
        if(!isNull("Name")){
            remove("Name");
        }
        put("Name", Name);
        return this;
    }
    
    public User setSurname(String Surname){
        if(!isNull("Surname")){
            remove("Surname");
        }
        put("Surname", Surname);
        return this;
    }
    
}