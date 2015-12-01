package bounswe2015group5.xplore.models;

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
            this.type = comment.optInt("Type");
            this.userID = comment.optInt("UserID");
            this.ID = comment.optInt("ID");
            this.contributionID = comment.optInt("ContributionID");
            this.content = comment.optString("Content");
            this.surname = comment.optString("Surname");
            this.name = comment.optString("Name");
            this.date = comment.optString("Date");
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
