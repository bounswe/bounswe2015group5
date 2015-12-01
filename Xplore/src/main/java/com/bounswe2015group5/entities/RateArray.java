/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe2015group5.entities;

import java.util.Map;
import org.json.JSONArray;

/**
 *
 * @author burak
 */
public class RateArray extends JSONArray{

    public RateArray() {
        super();
    }
    
    public RateArray(Map m) {
        super(m);
    }
    
    public RateArray(JSONArray arr) {
        super(arr.toString());
    }
    
    public Rate getRate(int index){
        return new Rate(super.getJSONObject(index));
    }
    
}
