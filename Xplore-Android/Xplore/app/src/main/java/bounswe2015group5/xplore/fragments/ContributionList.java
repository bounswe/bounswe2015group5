package bounswe2015group5.xplore.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bounswe2015group5.xplore.Login;
import bounswe2015group5.xplore.MainActivity;
import bounswe2015group5.xplore.R;
import bounswe2015group5.xplore.adapters.ContributionListAdapter;
import bounswe2015group5.xplore.models.Contribution;

/**
 * Last updated by Mert Oguz on 7/11/2015.
 */
public class ContributionList extends Fragment {

    private ArrayList<Contribution> contributions;
    private RequestQueue mRequestQueue;
    private ContributionListAdapter listAdapter;
    private Boolean isContListLoaded;

    public ContributionList(){
        contributions = new ArrayList<Contribution>();
        isContListLoaded = false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout parent = (RelativeLayout) inflater.inflate(R.layout.contribution_list, null);
        ListView contList = (ListView) parent.findViewById(R.id.contList);
        FloatingActionButton fab = (FloatingActionButton) parent.findViewById(R.id.fab);

        if(Login.share.getBoolean("signedIn",false)) {

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) getActivity()).launchFragment(new ContributionCreation(), "ConributionCreation");
                }
            });
        } else fab.setVisibility(View.INVISIBLE);

        if(!isContListLoaded){
            populateData();
        }

        listAdapter = new ContributionListAdapter(getActivity().getApplicationContext(), contributions);
        listAdapter.notifyDataSetChanged();
        contList.setAdapter(listAdapter);

        return parent;
    }

    private void populateData() {
        //Not sure, will check.
        mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        final String URL = getString(R.string.service_url) + "AllContributions"; //for POST to server

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("LOG", response.toString());
                        if(!response.isEmpty()){ // Server replies with the list of contributions
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                int numContributions = jsonArray.length();
                                for(int i = 0; i < numContributions; i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String date = jsonObject.getString("date");
                                    String name = jsonObject.getString("name");
                                    String surname = jsonObject.getString("surname");
                                    String title = jsonObject.getString("title");
                                    String content = jsonObject.getString("content");

                                    Contribution contribution = new Contribution(date,name,surname,title,content);
                                    contributions.add(contribution);
                                }
                                isContListLoaded = true;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else //unsuccessful register attempt
                            Toast.makeText(getActivity().getApplicationContext(), "Unsuccessful Register Attempt", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("LOG", error.toString());
            }
        }){
            /**
             * Puts arguments for POST that will be sent to the server
             * @auth Mert Oguz
             * @throws AuthFailureError
             */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> mParams = new HashMap<String, String>();
                return mParams;
            }
        };

        mRequestQueue.add(stringRequest);
    }
}
