package bounswe2015group5.xplore.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
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
import bounswe2015group5.xplore.adapters.ExpandableListAdapter;
import bounswe2015group5.xplore.models.Contribution;
import bounswe2015group5.xplore.models.Tag;
/**
 * Created by hakansahin on 24/11/15.
 */
public class Home extends BaseFragment{

    private List<Tag> tagGroups;
    private HashMap<String, List<Contribution>> groupContributions;

    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;

    public Home(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout parent = (RelativeLayout) inflater.inflate(R.layout.home, null);

        expListView = (ExpandableListView) parent.findViewById(R.id.home_extend_cont_list);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(getActivity(), tagGroups, groupContributions);
        expListView.setAdapter(listAdapter);

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                showProgressDialog();
                prepareGroupItems(tagGroups.get(groupPosition).getName());
            }
        });

        return parent;
    }

    private void prepareListData() {
        // TODO get all tags and related contributions from server.

        tagGroups = new ArrayList<>();
        groupContributions = new HashMap<>();

        final String URL = getString(R.string.service_url) + "AllTags"; //for POST to server

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("LOG", response.toString());
                        if(!response.toString().isEmpty()){ // Server replies with the list of contributions
                            try {
                                int numContributions = response.length();
                                for(int i = 0; i < numContributions; i++){
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    tagGroups.add(new Tag(jsonObject));
                                }

                                listAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } // TODO if response is empty, show a warning.
                        hideProgressDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("LOG_ERROR", error.toString());
                // TODO if the request fails, show a warning.
                hideProgressDialog();
            }
        });

        Globals.mRequestQueue.add(jsonArrayRequest);
    }

    private void prepareGroupItems(final String tag /* TODO send groupId as a parameter */) {

        final String URL = getString(R.string.service_url) + "SearchByTag"; //for POST to server

        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST,URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("LOG", response.toString());
                        if(!response.toString().isEmpty()){ // Server replies with the list of contributions
                            List<Contribution> contList = new ArrayList<>();
                            try {
                                JSONArray jArray = new JSONArray(response);
                                int numContributions = jArray.length();
                                for(int i = 0; i < numContributions; i++){
                                    JSONObject jsonObject = jArray.getJSONObject(i);

                                    Contribution contribution = new Contribution(jsonObject);
                                    contList.add(contribution);
                                }
                                groupContributions.put(tag, contList);

                                listAdapter.notifyDataSetChanged();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } // TODO if response is empty, show a warning.
                        hideProgressDialog();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("LOG_ERROR", error.toString());
                        // TODO if the request fails, show a warning.
                        hideProgressDialog();
                    }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> mParams = new HashMap<>();
                mParams.put("Tag",tag);
                return mParams;
            }
        };

        Globals.mRequestQueue.add(jsonArrayRequest);
    }
}
