/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe2015group5.entities;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author burak
 */
public class RateArray extends JSONArray{

    public RateArray() {
        super();
    }
    
    public RateArray(JSONArray arr) {
        super(arr.toString());
    }
    
    @Override
    public Rate get(int index){
        return new Rate((JSONObject)super.get(index));
    }
    
}
