package bounswe2015group5.xplore.models;


import java.util.ArrayList;

/**
 * Last updated by Mert Oguz on 7/11/2015.
 */
public class Contribution {

    private String date;
    private String name;
    private String surname;
    private String title;
    private String content;
    //private ArrayList<String> tags;
    //private int id;
    private boolean isRated;
    private int rate;

    /**
     * Default constructor for Contribution. Initializes with null values.
     * @author Mert Oguz
     */
    public Contribution(){
        this.date = null;
        this.name = null;
        this.surname = null;
        this.title = null;
        this.content = null;
        this.isRated = false;
        this.rate = 0;
    }

    /**
     * Constructor with params for Contribution
     * @author Mert Oguz
     */
    public Contribution(String date, String name, String surname, String title, String content /* TODO add @isRated and @rate as parameter */){
        this.date = date;
        this.name = name;
        this.surname = surname;
        this.title = title;
        this.content = content;
        this.isRated = false;
        this.rate = 0;
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
