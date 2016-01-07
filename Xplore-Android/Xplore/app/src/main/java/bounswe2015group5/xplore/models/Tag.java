package bounswe2015group5.xplore.models;

import org.json.JSONObject;

import java.io.Serializable;
/**
 * Created by hakansahin on 30/11/15.
 */
public class Tag implements Serializable{

    private int ID;
    private String name, concept, creatorUsername, creatorEmail, createdAt, updatedAt;

    public Tag(JSONObject tag){

        this.ID                 = tag.optInt("id");
        this.name               = tag.optString("name");
        this.concept            = tag.optString("concept");
        JSONObject creator      = tag.optJSONObject("creator");
        if(creator != null){
            this.creatorUsername    = creator.optString("username");
            this.creatorEmail       = creator.optString("email");
        }
        this.createdAt          = tag.optString("createdAt");
        this.updatedAt          = tag.optString("updatedAt");

    }

    public int getID(){ return this.ID; }
    public String getName(){ return this.name; }
    public String getConcept(){ return this.concept; }
}
