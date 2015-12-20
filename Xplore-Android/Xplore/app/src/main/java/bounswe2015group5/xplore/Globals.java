package bounswe2015group5.xplore;

import android.content.Context;
import android.content.SharedPreferences;
/**
 * Created by hakansahin on 09/11/15.
 */
public class Globals {

    public static XploreApp instance;
    public static Context appContext;
    public static SharedPreferences share;
    public static ConnectionManager connectionManager;

    public static void init(){
        share = appContext.getSharedPreferences("appdata", Context.MODE_PRIVATE);

        share = appContext.getSharedPreferences("appdata", Context.MODE_PRIVATE);
        connectionManager = new ConnectionManager(appContext);
    }

}
