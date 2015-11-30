package bounswe2015group5.xplore.models;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
/**
 * Last updated by Mert Oguz on 7/11/2015.
 */
public class Contribution {

    private int ID, type, clientRate = 0, rate;
    private String title, content, date, name, surname;
    private ArrayList<Tag> tags;
    private boolean isRated;

    /**
     * Default constructor for Contribution. Initializes with null values.
     * @author Mert Oguz
     */
    public Contribution(){
        this.ID = 0;
        this.title = null;
        this.content = null;
        this.date = null;
        this.type = -1;
        this.name = null;
        this.surname = null;
        this.clientRate = 0;
        this.isRated = false;
        this.rate = 0;
        this.tags = null;
    }

    /**
     * Constructor with params for Contribution
     * @author Mert Oguz
     */
    public Contribution(JSONObject contribution){

        try {

            this.ID         = contribution.getInt("ID");
            this.title      = contribution.getString("Title");
            this.content    = contribution.getString("Content");
            this.date       = contribution.getString("Date");
            this.type       = contribution.getInt("Type");
            this.name       = contribution.getString("Name");
            this.surname    = contribution.getString("Surname");
            this.clientRate = contribution.optInt("ClientRate");
            this.rate       = contribution.getInt("Rate");

//            JSONArray tagList = contribution.getJSONArray("Tags");
//            for(int i = 0; i < tagList.length(); i++){
//                JSONObject tag = tagList.getJSONObject(i);
//                tags.add(new Tag(tag));
//            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.isRated = (this.clientRate != 0);
    }

    /**
     * Access title of contribution
     * @author Mert Oguz
     */
    public String getTitle(){
        return title;
    }

    /**
     * Access date of contribution
     * @author Mert Oguz
     */
    public String getDate(){ return date; }

    /**
     * Access name of contribution author
     * @author Mert Oguz
     */
    public String getName(){ return name; }

    /**
     * Access surname of contribution author
     * @author Mert Oguz
     */
    public String getSurname(){ return surname; }

    /**
     * Access content of contribution
     * @return @content
     */
    public String getContent(){
        return content;
    }

    /**
     * Accesses whether the contribution is rated by the user or not
     * @return @isRated
     * @author Hakan Sahin
     */
    public boolean isRated() { return isRated; }

    /**
     * Changes the rate status of the contribution
     * @author Hakan Sahin
     */
    public void setRated(boolean isRated) {
        this.isRated = isRated;
        // TODO post this data to the related servlet.
    }

    /**
     * Accesses the number of rate of the contribution
     * @return @rate
     * @author Hakan Sahin
     */
    public int getRate(){
        return this.rate;
    }

    /**
     * Updates the number of rate of the contribution
     * @author Hakan Sahin
     */
    public void updateRate(int rate){
        // TODO post updated rate to the related servlet.
        this.rate = rate;
    }
}
