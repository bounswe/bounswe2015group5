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
    
    public int getTagID(){
        return (Integer) get("TagID");
    }
    
    public String getTagName(){
        return (String) get("TagName");
    }
       
}