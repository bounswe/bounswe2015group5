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
public class Rate extends JSONObject{ 
    public Rate(JSONObject t){
        super(t.toString());
    }
    
    public int getContributionID(){
        return (Integer) get("ContributionID");
    }
    
    public int getUserID(){
        return (Integer) get("UserID");
    }
    
    public int getRate(){
        return (Integer) get("UserID");
    }
       
}