package bounswe2015group5.xplore;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;

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
    private RequestQueue requestQueue;
    private String BASE_URL;
    private Response.ErrorListener errorListener;

    public ConnectionManager(Context context){
        this.context = context;

        // Instantiate the cache
        Cache cache = new DiskBasedCache(this.context.getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        this.requestQueue = new RequestQueue(cache, network);
        this.BASE_URL = context.getString(R.string.service_url);
        this.errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("CONNECTIONMANAGER_ERROR", error.toString());
            }
        };

        this.requestQueue.start();
    }

    /**
     * Registers the user with given parameters to the system.
     * @param email : email of the user.
     * @param pass : password of the user.
     * @param username : username of the user.
     */
    public void registerUser(final String email, String pass, final String username, Response.Listener<JSONObject> responseListener){

        String URL = BASE_URL + "user";

        final Map<String, String> mParams = new HashMap<>();
        mParams.put("email", email);
        mParams.put("password", pass);
        mParams.put("username", username);

        JsonObjectRequest request = new JsonObjectRequest(URL, new JSONObject(mParams), responseListener, errorListener);
        requestQueue.add(request);
    }

    /**
     * Logs in the user with given parameters to the system.
     * @param email : email of the user.
     * @param pass : password of the user.
     * @param responseListener : Listener saying what will happen after user logs in.
     */
    public void login(final String email, final String pass, Response.Listener<JSONObject> responseListener){

        String URL = BASE_URL + "user/login";

        final Map<String, String> mParams = new HashMap<>();
        mParams.put("username", email);
        mParams.put("password", pass);

        JsonObjectRequest request = new JsonObjectRequest(URL, new JSONObject(mParams), responseListener, errorListener);
        requestQueue.add(request);
    }

    // TODO ??
    /**
     * Logs out the user who logged in already.
     * @param responseListener : Listener saying what will happen after user logs in.
     */
    public void logout(Response.Listener<String> responseListener){

        String URL = BASE_URL + "user/logout";

        StringRequest request = new StringRequest(Request.Method.GET, URL, responseListener, errorListener){
            private final String PROTOCOL_CHARSET = "utf-8";
            private final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return (new JSONObject()).toString().getBytes(PROTOCOL_CHARSET);
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", (new JSONObject()), PROTOCOL_CHARSET);
                    return null;
                }
            }

            @Override
            public String getBodyContentType() {
                return (new JSONObject()).toString() == null ? null : PROTOCOL_CONTENT_TYPE;
            }
        };

//        JsonObjectRequest request = new JsonObjectRequest(URL, new JSONObject(), responseListener, errorListener);
        requestQueue.add(request);
    }

    /**
     * Gets the tag with given id.
     * @param tagId
     * @param responseListener
     */
    public void getTag(int tagId, Response.Listener<JSONObject> responseListener){

        String URL = BASE_URL + "tags/" + tagId;

        JsonObjectRequest request = new JsonObjectRequest(URL, null, responseListener, errorListener);
        requestQueue.add(request);
    }

    /**
     * Gets all tags in the system.
     * @param responseListener
     */
    public void getAllTags(Response.Listener<JSONArray> responseListener){

        String URL = BASE_URL + "tags";

        JsonArrayRequest request = new JsonArrayRequest(URL, responseListener, errorListener);
        requestQueue.add(request);
    }

    public void getRelatedTags(int tagID, Response.Listener<JSONObject> responseListener){

        String URL = BASE_URL + "tags/" + tagID + "/tags";

        JsonObjectRequest request = new JsonObjectRequest(URL, null, responseListener, errorListener);
        requestQueue.add(request);
    }

    public void getAllContributions(Response.Listener<JSONArray> responseListener){

        String URL = BASE_URL + "AllContributions";

        JsonRequest request = new JsonArrayRequest(URL, responseListener, errorListener);
        requestQueue.add(request);
    }

    /**
     * Gets contributions of the given tag.
     * @param tagId : ID of the tag.
     * @param responseListener
     */
    public void getContributionsByTagId(int tagId, Response.Listener<JSONArray> responseListener){

        String URL = BASE_URL + "/tags/" + tagId + "/contributions";


        JsonArrayRequest request = new JsonArrayRequest(URL, responseListener, errorListener);
        requestQueue.add(request);
    }

    public void getTagsByContributionId(int contId, Response.Listener<JSONArray> responseListener){

        String URL = BASE_URL + "/contributions/" + contId + "/tags";

        JsonArrayRequest request = new JsonArrayRequest(URL, responseListener, errorListener);
        requestQueue.add(request);
    }

    /**
     * Receives comments of the given contribution.
     * @param contID
     * @param responseListener
     */
    public void getCommentsByContributionId(int contID, Response.Listener<JSONArray> responseListener){

        String URL = BASE_URL + "contributions/" + contID + "/comments";


        Log.d("fetchComments",URL);
        JsonArrayRequest request = new JsonArrayRequest(URL, responseListener, errorListener);
        requestQueue.add(request);
    }


    /**
     * Creates a contribution with given parameters.
     * @param title
     * @param content
     * @param username
     * @param responseListener
     */
    public void createContribution(String title, String content, String username, Response.Listener<JSONObject> responseListener){

        String URL = BASE_URL + "contributions";

        final Map<String, String> mParams = new HashMap<>();
        mParams.put("title", title);
        mParams.put("content", content);
        mParams.put("username", username);

        JsonObjectRequest request = new JsonObjectRequest(URL, new JSONObject(mParams), responseListener, errorListener);
        requestQueue.add(request);

    }

    /**
     * Deletes the given contribution from the system.
     * @param contID
     * @param responseListener
     */
    public void deleteContribution(int contID, Response.Listener<JSONObject> responseListener){

        String URL = BASE_URL + "contributions/" + contID;

//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, URL, null, responseListener, errorListener);
//        requestQueue.add(request);
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
        requestQueue.add(request);
    }

    /**
     * Posts the given comment to the given contribution.
     * @param contID
     * @param content
     * @param username
     * @param responseListener
     */
    public void postComment(int contID, String content, String username, Response.Listener<JSONArray> responseListener){

        String URL = BASE_URL + "contributions/" + contID + "/comments";

        final Map<String, String> mParams = new HashMap<>();
        mParams.put("commentBody", ""+ content);
        mParams.put("username", username);

        customJsonArrayRequest request = new customJsonArrayRequest(URL, new JSONObject(mParams), responseListener, errorListener);
        requestQueue.add(request);
    }

    /**
     * Deletes the given comment.
     * @param commID
     * @param responseListener
     */
    public void deleteComment(int commID, Response.Listener<JSONObject> responseListener){

        String URL = BASE_URL + "";

//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, URL, new JSONObject(), responseListener, errorListener);
//        requestQueue.add(request);
    }
}

class customJsonArrayRequest extends JsonRequest<JSONArray> {

    /**
     * Creates a new request.
     * @param url URL to fetch the JSON from
     * @param listener Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public customJsonArrayRequest(String url, JSONObject mParams, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, mParams.toString(), listener, errorListener);
    }

    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString =
                    new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONArray(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response, ignoreNoCache()));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }
}