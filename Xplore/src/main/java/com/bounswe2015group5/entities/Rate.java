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
    
    public Rate(){
        super();
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
    
    public Rate setContributionID(int ContributionID){
        if(!isNull("ContributionID")){
            remove("ContributionID");
        }
        put("ContributionID", ContributionID);
        return this;
    }
    
    public Rate setUserID(int UserID){
        if(!isNull("UserID")){
            remove("UserID");
        }
        put("UserID", UserID);
        return this;
    }
    
    public Rate setRate(int rate){
        if(!isNull("Rate")){
            remove("Rate");
        }
        put("Rate", rate);
        return this;
    }
       
}