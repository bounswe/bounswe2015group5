/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe2015group5.entities;

import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author burak
 */
public class ContributionArray extends JSONArray{

    public ContributionArray() {
        super();
    }
    
    public ContributionArray(JSONArray arr) {
        super(arr.toString());
    }
    
    public ContributionArray(Map m) {
        super(m);
    }
    
    @Override
    public Contribution get(int index){
        return new Contribution((JSONObject)super.get(index));
    }
       
}
