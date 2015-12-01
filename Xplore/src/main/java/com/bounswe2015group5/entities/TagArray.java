package com.bounswe2015group5.entities;

import java.util.Map;
import org.json.JSONArray;

/**
 *
 * @author burak
 */
public class TagArray extends JSONArray{

    public TagArray() {
        super();
    }
    
    public TagArray(JSONArray arr) {
        super(arr.toString());
    }
    
    public TagArray(Map m){
        super(m);
    }
    
    public TagArray(String t){
        super(t);
    }
    
    public Tag getTag(int index){
        return new Tag(super.getJSONObject(index));
    }
       
}
