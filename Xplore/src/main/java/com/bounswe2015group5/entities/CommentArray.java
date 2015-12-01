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
public class CommentArray extends JSONArray{

    public CommentArray() {
        super();
    }
    
    public CommentArray(Map m) {
        super(m);
    }
    
    public CommentArray(JSONArray arr) {
        super(arr.toString());
    }
    
    public Comment getComment(int index){
        return new Comment(super.getJSONObject(index));
    }
       
}