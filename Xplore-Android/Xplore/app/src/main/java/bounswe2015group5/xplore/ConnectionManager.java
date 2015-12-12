package bounswe2015group5.xplore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by hakansahin on 06/12/15.
 */
public class ConnectionManager {

    private Context context;
    private RequestQueue mRequestQueue;
    private String BASE_URL;
    private Response.ErrorListener errorListener;

    public ConnectionManager(Context context, HttpStack httpStack){
        this.context = context;
        this. mRequestQueue = Volley.newRequestQueue(context, httpStack);
        this.BASE_URL = context.getString(R.string.service_url);
        this.errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("CONNECTIONMANAGER_ERROR", error.toString());
            }
        };
    }

    public void registerUser(final Activity activity, final String email, String pass, final String name, final String surname){

        // TODO fix register functionality.
        if(true) {
            Toast.makeText(context, "Register is not functional, please try again later !", Toast.LENGTH_SHORT).show();
            return;
        }

        String URL = BASE_URL + "RegisterUser";

        final Map<String, String> mParams = new HashMap<>();
        mParams.put("Email", email);
        mParams.put("Pass", pass);
        mParams.put("Name", name);
        mParams.put("Surname", surname);

        Response.Listener<String> responseListener =
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("REGISTER_USER", response);
                        if(response.toLowerCase().contains("succes")){ // Server replies with "Success"
                            SharedPreferences.Editor editor = Globals.share.edit();
                            editor.putBoolean("SignedIn", true);
                            editor.putString("Email",email);
                            editor.putString("Name",name);
                            editor.putString("Surname",surname);
                            editor.apply();

                            Toast.makeText(context, "You have successfully registered", Toast.LENGTH_SHORT).show();
                            activity.startActivity(new Intent(activity, MainActivity.class));
                            activity.finishAffinity();
                        } else //unsuccessful register attempt
                            Toast.makeText(context, "Unsuccessful Register Attempt", Toast.LENGTH_SHORT).show();
                    }
                };

        StringRequest request = new StringRequest(Request.Method.POST, URL, responseListener, errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return mParams;
            }
        };
        mRequestQueue.add(request);
    }

    public void login(String email, String pass, Response.Listener<String> responseListener){

        String URL = BASE_URL + "Login";

        final Map<String, String> mParams = new HashMap<>();
        mParams.put("Email", email);
        mParams.put("Password", pass);

        StringRequest request = new StringRequest(Request.Method.POST, URL, responseListener, errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return mParams;
            }
        };
        mRequestQueue.add(request);
    }

    public void getUserInfo(final Activity activity){

        String URL = BASE_URL + "UserInfo";

        Response.Listener<JSONObject> responseListener =
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(!response.toString().isEmpty()){
                            try {
                                int ID = response.getInt("ID");
                                String name = response.getString("Name");
                                String surname = response.getString("Surname");

                                SharedPreferences.Editor editor = Globals.share.edit();
                                editor.putInt("ID",ID);
                                editor.putString("Name",name);
                                editor.putString("Surname", surname);
                                editor.apply();

                                activity.startActivity(new Intent(activity, MainActivity.class));
                                activity.finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else //unsuccessful attempt while receiving user info
                            Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                };

        JsonRequest request = new JsonObjectRequest(Request.Method.POST, URL, null, responseListener, errorListener);
        mRequestQueue.add(request);
    }

    public void getAllTags(Response.Listener<JSONArray> responseListener){

        String URL = BASE_URL + "AllTags";

        JsonArrayRequest request = new JsonArrayRequest(URL, responseListener, errorListener);
        mRequestQueue.add(request);
    }

    public void getAllContributions(Response.Listener<JSONObject> responseListener){

        String URL = BASE_URL + "AllContributions";

        JsonRequest request = new JsonObjectRequest(Request.Method.POST, URL, null, responseListener, errorListener);
        mRequestQueue.add(request);
    }

    public void searchByTag(String tag, Response.Listener<String> responseListener){

        String URL = BASE_URL + "SearchByTag";

        final Map<String, String> mParams = new HashMap<>();
        mParams.put("Tag", tag);

        StringRequest request = new StringRequest(Request.Method.POST, URL, responseListener, errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return mParams;
            }
        };
        mRequestQueue.add(request);
    }

    public void commentsByContributionID(int contID, Response.Listener<String> responseListener){

        String URL = BASE_URL + "CommentsByContributionID";

        final Map<String, String> mParams = new HashMap<>();
        mParams.put("ContributionID", String.valueOf(contID));

        StringRequest request = new StringRequest(Request.Method.POST, URL, responseListener, errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return mParams;
            }
        };
        mRequestQueue.add(request);
    }

    public void registerContribution(String title, String content, /* TODO add tags */ Response.Listener<String> responseListener){

        String URL = BASE_URL + "RegisterContribution";

        final JSONObject mParams = new JSONObject();
        try {
            mParams.put("Title", title);
            mParams.put("Content", content);
            mParams.put("Tags", new JSONArray());
        } catch (JSONException e) {
            // TODO handle exception.
        }

        StringRequest request = new StringRequest(Request.Method.POST, URL, responseListener, errorListener){
            private final String PROTOCOL_CHARSET = "utf-8";
            private final String PROTOCOL_CONTENT_TYPE =
                    String.format("application/json; charset=%s", PROTOCOL_CHARSET);
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mParams.toString().getBytes(PROTOCOL_CHARSET);
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mParams, PROTOCOL_CHARSET);
                    return null;
                }
            }

            @Override
            public String getBodyContentType() {
                return mParams.toString() == null ? null : PROTOCOL_CONTENT_TYPE;
            }
        };
        mRequestQueue.add(request);

    }

    public void rateContribution(int contID, int rate, Response.Listener<String> responseListener){

        String URL = BASE_URL + "RateContribution";

        final JSONObject mParams = new JSONObject();
        try {
            mParams.put("ContributionID","" + contID);
            mParams.put("Rate","" + rate);
        } catch (JSONException e) {
            // TODO handle exception.
        }

        StringRequest request = new StringRequest(Request.Method.POST, URL, responseListener, errorListener){
            private final String PROTOCOL_CHARSET = "utf-8";
            private final String PROTOCOL_CONTENT_TYPE =
                    String.format("application/json; charset=%s", PROTOCOL_CHARSET);
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mParams.toString().getBytes(PROTOCOL_CHARSET);
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mParams, PROTOCOL_CHARSET);
                    return null;
                }
            }

            @Override
            public String getBodyContentType() {
                return mParams.toString() == null ? null : PROTOCOL_CONTENT_TYPE;
            }
        };
        mRequestQueue.add(request);
    }

    public void registerComment(int contID, String content, Response.Listener<String> responseListener){

        String URL = BASE_URL + "RegisterComment";

        final JSONObject mParams = new JSONObject();
        try {
            mParams.put("ContributionID", ""+ contID);
            mParams.put("Content", content);
        } catch (JSONException e) {
            // TODO handle exception.
        }

        StringRequest request = new StringRequest(Request.Method.POST, URL, responseListener, errorListener){

            private final String PROTOCOL_CHARSET = "utf-8";
            private final String PROTOCOL_CONTENT_TYPE =
                    String.format("application/json; charset=%s", PROTOCOL_CHARSET);
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mParams.toString().getBytes(PROTOCOL_CHARSET);
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mParams, PROTOCOL_CHARSET);
                    return null;
                }
            }

            @Override
            public String getBodyContentType() {
                return mParams.toString() == null ? null : PROTOCOL_CONTENT_TYPE;
            }
        };
        mRequestQueue.add(request);
    }

    public JSONObject setParameters(HashMap<Object, Object> params){

        final JSONObject mParams = new JSONObject();
        try {
            for(Map.Entry entry : params.entrySet())
                mParams.put((String) entry.getKey(), entry.getValue());
        } catch (JSONException e) {
            // TODO handle exception.
        }

        return mParams;
    }

}