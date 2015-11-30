package bounswe2015group5.xplore.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bounswe2015group5.xplore.Globals;
import bounswe2015group5.xplore.R;
import bounswe2015group5.xplore.models.Contribution;


/**
 * Created by Mert on 26.11.2015.
 */
public class ContributionDetail extends Fragment {
    private TextView tv_title;
    private TextView tv_content;
    private TextView tv_nameSurname;
    private TextView tv_date;

    String id;

    public ContributionDetail(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        RelativeLayout parent = (RelativeLayout) inflater.inflate(R.layout.contribution_detail,null);

        tv_title = (TextView) parent.findViewById(R.id.detailConTitle);
        tv_content = (TextView) parent.findViewById(R.id.detailConContent);
        tv_nameSurname = (TextView) parent.findViewById(R.id.detailConNameSurname);
        tv_date = (TextView) parent.findViewById(R.id.detailConDate);

        id = getArguments().get("id").toString();


        /*tv_title.setText(title);
        tv_content.setText(content);
        tv_nameSurname.setText(nameSurname);
        tv_date.setText(date);*/

        return parent;
    }

    public void fetchContribution(){
        final String URL = getString(R.string.service_url) + "GetContribution"; //for POST to server

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST,URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("LOG", response.toString());
                        if(!response.toString().isEmpty()){ // Server replies with the list of contributions
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Contribution contribution = new Contribution(jsonObject);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } // TODO if response is empty, show a warning.
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("LOG_ERROR", error.toString());
                // TODO if the request fails, show a warning.
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> mParams = new HashMap<>();
                mParams.put("ContributionId",id);
                return mParams;
            }
        };

        Globals.mRequestQueue.add(jsonObjectRequest);
    }
}
