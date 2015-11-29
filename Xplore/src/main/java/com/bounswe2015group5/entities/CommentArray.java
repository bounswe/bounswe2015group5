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
public class CommentArray extends JSONArray{

    public CommentArray() {
        super();
    }
    
    public CommentArray(JSONArray arr) {
        super(arr.toString());
    }
    
    @Override
    public Comment get(int index){
        return new Comment((JSONObject)super.get(index));
    }
       
}