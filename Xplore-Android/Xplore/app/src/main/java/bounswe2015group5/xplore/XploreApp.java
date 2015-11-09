package bounswe2015group5.xplore;

import android.app.Application;
import android.content.Context;
/**
 * Created by hakansahin on 09/11/15.
 */
public class XploreApp extends Application {

    public XploreApp() {}

    public XploreApp(Context context) {

        Globals.appContext = context;
        Globals.instance = this;

        Globals.init();
    }

    public static void initInstance(Context context){
        if (Globals.instance == null)
            Globals.instance = new XploreApp(context); // create the instance
    }
}
