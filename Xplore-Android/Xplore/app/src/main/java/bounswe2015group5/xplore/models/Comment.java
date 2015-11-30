package bounswe2015group5.xplore.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mert on 30.11.2015.
 */
public class Comment {
    private int type,userID,ID,contributionID;
    private String content,surname,name,date;

    /**
     * Default constructor for Comment
     * @author Mert Oguz
     */
    public Comment(){
        type = 0;
        userID = 0;
        ID = 0;
        contributionID = 0;
        content = null;
        surname = null;
        name = null;
        date = null;
    }

    /**
     * Constructor that takes JSONObject for Comment
     * @author Mert Oguz
     */
    public Comment(JSONObject comment){
        try {
            this.type = comment.getInt("Type");
            this.userID = comment.getInt("UserID");
            this.ID = comment.getInt("ID");
            this.contributionID = comment.getInt("ContributionID");
            this.content = comment.getString("Content");
            this.surname = comment.getString("Surname");
            this.name = comment.getString("Name");
            this.date = comment.getString("Date");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getType(){
        return type;
    }
    public int getUserID(){
        return userID;
    }
    public int getID(){
        return ID;
    }
    public int getContributionID(){
        return contributionID;
    }
    public String getContent(){
        return content;
    }
    public String getSurname(){
        return surname;
    }
    public String getName(){
        return name;
    }
    public String getDate(){
        return date;
    }
}
