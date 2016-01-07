package bounswe2015group5.xplore.models;


import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
/**
 * Last updated by Mert Oguz on 7/11/2015.
 */
public class Contribution implements Serializable {

    public int ID, clientRate = 0, rate;
    private String title, content, creatorUsername, creatorEmail, createdAt, updatedAt;
    private ArrayList<Tag> tags;

    /**
     * Constructor with params for Contribution
     * @author Mert Oguz
     */
    public Contribution(JSONObject contribution){

        this.ID                 = contribution.optInt("id");
        this.title              = contribution.optString("title");
        this.content            = contribution.optString("content");
        JSONObject creator      = contribution.optJSONObject("creator");
        this.creatorUsername    = creator.optString("username");
        this.creatorEmail       = creator.optString("email");
        this.createdAt          = (new Date(Long.parseLong(contribution.optString("createdAt")))).toString();

        // TODO set these values.
        this.rate = 0;
        this.clientRate = 0;
        this.tags = new ArrayList<Tag>();

//            this.type       = contribution.getInt("Type");
//            this.clientRate = contribution.optInt("ClientRate");
//            this.rate       = contribution.getInt("Rate");
//
//            this.tags = new ArrayList<Tag>();
//            JSONArray tagList = contribution.getJSONArray("Tags");
//            for(int i = 0; i < tagList.length(); i++){
//                JSONObject tag = tagList.getJSONObject(i);
//                this.tags.add(new Tag(tag));
//            }
    }

    /**
     * Access title of contribution
     * @author Mert Oguz
     */
    public String getTitle(){
        return title == null ? "Title" : title;
    }

    /**
     * Access creation date of contribution
     * @author Mert Oguz
     */
    public String getDate(){
        return createdAt == null ? "unknown" : createdAt;
    }

    /**
     * Access username of contribution author
     * @author Mert Oguz
     */
    public String getCreatorUsername(){
        return creatorUsername == null ? "dummyUser" : creatorUsername;
    }

    /**
     * Access email of contribution author
     * @author Mert Oguz
     */
    public String getCreatorEmail(){
        return creatorEmail == null ? "dummy@mail.com" : creatorEmail;
    }

    /**
     * Access content of contribution
     * @return @content
     */
    public String getContent(){
        return content == null ? "empty content" : content;
    }

    /**
     * Accesses whether the contribution is rated by the user or not
     * @return @isRated
     * @author Hakan Sahin
     */
    public int isRated() {
        return clientRate;
    }

    /**
     * Changes the rate status of the contribution
     * @author Hakan Sahin
     */
    public void setRated(int isRated) {
        int exRate = this.clientRate;
        this.clientRate = (this.clientRate != 0) ? 0 : isRated;
        updateRate(clientRate - exRate);
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
        this.rate += rate;
    }

    /**
     * Accesses the ID of the contribution
     * @author Mert Oguz
     */
    public int getId(){
        return this.ID;
    }

    /**
     * Accesses the tags of the contribution
     * @author Mert Oguz
     */
    public ArrayList<Tag> getTags(){
        return this.tags;
    }
}
