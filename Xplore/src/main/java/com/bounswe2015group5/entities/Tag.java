/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe2015group5.entities;

import org.json.JSONObject;

/**
 *
 * @author burak
 */
public class Tag extends JSONObject{ 
    public Tag(JSONObject t){
        super(t.toString());
    }
    
    public Tag(){
        super();
    }
    
    public int getTagID(){
        return (Integer) get("TagID");
    }
    
    public String getTagName(){
        return (String) get("TagName");
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
        put("TagID", TagName);
        return this;
    }
       
}