package bounswe2015group5.xplore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Response;

import org.json.JSONObject;
/**
 * Created by hakansahin on 09/11/15.
 */
public class Splash extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);

        XploreApp.initInstance(getApplicationContext());

        startSplash();
    }

    public void startSplash(){

        if(Globals.share.getBoolean("SignedIn", false)){

            final String email = Globals.share.getString("username", ""),
                         pass = Globals.share.getString("password", "");

            Response.Listener<JSONObject> responseListener =
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            if(response.toString().length() > 0) {

//                                if(!Globals.share.contains("ID")) getUserInfo();
//                                else {
                                    startActivity(new Intent(Splash.this, MainActivity.class));
                                    finish();
//                                }
                            } else {
                                startActivity(new Intent(Splash.this, Login.class));
                                finish();
                            }
                        }
                    };

            Globals.connectionManager.login(email, pass, responseListener);

        } else {
            startActivity(new Intent(Splash.this, Login.class));
            finish();
        }

    }
}
