package bounswe2015group5.xplore.models;

import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Mert on 30.11.2015.
 */
public class Comment {
    private int ID;
    private String content,username,email,createdAt;
    private Contribution contribution;

    /**
     * Default constructor for Comment
     * @author Mert Oguz
     */
    public Comment(){}

    /**
     * Constructor that takes JSONObject for Comment
     * @author Mert Oguz
     */
    public Comment(JSONObject comment){
        this.ID = comment.optInt("id");
        this.content = comment.optString("content");

        JSONObject user = comment.optJSONObject("user");
        this.username = user.optString("username");
        this.email = user.optString("email");

        this.contribution = new Contribution(comment.optJSONObject("contribution"));

        this.createdAt = (new Date(Long.parseLong(comment.optString("createdAt")))).toString();
    }

    public int getID(){
        return this.ID;
    }
    public Contribution getContribution(){
        return this.contribution;
    }
    public String getContent(){
        return this.content;
    }
    public String getUsername(){
        return this.username;
    }
    public String getEmail() { return this.email; }
    public String getCreatedAt(){
        return (this.createdAt == null) ? "Today" : this.createdAt;
    }

    public void setContent(String content){ this.content = content; }
    public void setUsername(String username){ this.username = username; }
}
