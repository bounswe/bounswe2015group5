package bounswe2015group5.xplore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
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

        if(Globals.share.getBoolean("signedIn", false)){
            String URL = getString(R.string.service_url) + "Login";


            final String email = Globals.share.getString("email", ""),
                         pass = Globals.share.getString("pass", "");

            StringRequest loginRequest = new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("LOG", response.toString());
                            if(response.toLowerCase().contains("success")){

                                startActivity(new Intent(Splash.this, MainActivity.class));
                                finish();

                            } else {
                                Toast.makeText(getApplicationContext(), "Unsuccessful Attempt", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(Splash.this, Login.class));
                                finish();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("LOG_error", error.toString());
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> mParams = new HashMap<String, String>();
                    mParams.put("email", email);
                    mParams.put("pass", pass);

                    return mParams;
                }
            };

            Globals.mRequestQueue.add(loginRequest);
        } else {
            startActivity(new Intent(Splash.this, Login.class));
            finish();
        }

    }
}
