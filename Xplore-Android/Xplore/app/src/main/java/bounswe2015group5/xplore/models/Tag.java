package bounswe2015group5.xplore.models;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by hakansahin on 30/11/15.
 */
public class Tag {

    private int ID;
    private String name;

    public Tag(){}

    public Tag(JSONObject tag){
        try {
            this.ID = tag.getInt("id");
            this.name = tag.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getID(){ return this.ID; }
    public String getName(){ return this.name; }

}
