package bounswe2015group5.xplore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Response;
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

            final String email = Globals.share.getString("Email", ""),
                         pass = Globals.share.getString("Pass", "");

            Response.Listener<String> responseListener =
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.toLowerCase().contains("success")) {
                                if(!Globals.share.contains("ID")) getUserInfo();
                                else {
                                    startActivity(new Intent(Splash.this, MainActivity.class));
                                    finish();                                }
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

    public void getUserInfo(){
        Globals.connectionManager.getUserInfo(Splash.this);
    }
}
