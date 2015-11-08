package bounswe2015group5.xplore.models;

/**
 * Last updated by Mert Oguz on 7/11/2015.
 */
public class Contribution {

    private String date;
    private String name;
    private String surname;
    private String title;
    private String content;

    /**
     * Default constructor for Contribution. Initializes with null values.
     * @author Mert Oguz
     */
    public Contribution(){
        date = null;
        name = null;
        surname = null;
        title = null;
        content = null;
    }

    /**
     * Constructor with params for Contribution
     * @author Mert Oguz
     */
    public Contribution(String date, String name, String surname, String title, String content){
        this.date = date;
        this.name = name;
        this.surname = surname;
        this.title = title;
        this.content = content;
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
     * @return
     */
    public String getContent(){
        return content;
    }
}
