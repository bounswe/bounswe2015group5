/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe2015group5.entities;

import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author burak
 */
public class Tag extends JSONObject{ 
    public Tag(JSONObject t){
        super(t.toString());
    }
    
    public Tag(String t){
        super(t);
    }
    
    public Tag(){
        super();
    }
    
    public Tag(Map m){
        super(m);
    }
    
    public int getTagID(){
        return getInt("TagID");
    }
    
    public String getTagName(){
        return getString("TagName");
    }
    
    public Tag setTagID(int TagID){
        if(!isNull("TagID")){
            remove("TagID");
        }
        put("TagID", TagID);
        return this;
    }
    
    public Tag setTagName(String TagName){
        if(!isNull("TagName")){
            remove("TagName");
        }
        put("TagName", TagName);
        return this;
    }
       
}