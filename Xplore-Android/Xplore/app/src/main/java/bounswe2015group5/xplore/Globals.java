package bounswe2015group5.xplore;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
/**
 * Created by hakansahin on 09/11/15.
 */
public class Globals {

    public static XploreApp instance;
    public static Context appContext;
    public static SharedPreferences share;
    public static ConnectionManager connectionManager;
    public static HttpStack httpStack;

    public static void init(){
        share = appContext.getSharedPreferences("appdata", Context.MODE_PRIVATE);

        DefaultHttpClient httpclient = new DefaultHttpClient();
        CookieStore cookieStore = new BasicCookieStore();
        httpclient.setCookieStore(cookieStore);
        httpStack = new HttpClientStack( httpclient );

        share = appContext.getSharedPreferences("appdata", Context.MODE_PRIVATE);
        connectionManager = new ConnectionManager(appContext, httpStack);
    }

}
