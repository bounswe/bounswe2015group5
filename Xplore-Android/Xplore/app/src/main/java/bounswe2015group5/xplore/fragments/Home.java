package bounswe2015group5.xplore.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

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

import bounswe2015group5.xplore.Globals;
import bounswe2015group5.xplore.R;
import bounswe2015group5.xplore.adapters.ExpandableListAdapter;
import bounswe2015group5.xplore.models.Contribution;
/**
 * Created by hakansahin on 24/11/15.
 */
public class Home extends BaseFragment{

    private List<String> tagGroups;
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
                prepareGroupItems(tagGroups.get(groupPosition));
            }
        });

        return parent;
    }

    private void prepareListData() {
        // TODO get all tags and related contributions from server.

        tagGroups = new ArrayList<>();
        groupContributions = new HashMap<>();

        for(int i=1; i<4; i++){
            tagGroups.add("Tag " + i);
        }
    }

    private void prepareGroupItems(final String tag /* TODO send groupId as a parameter */) {

        final String URL = getString(R.string.service_url) + "AllContributions"; //for POST to server

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("LOG", response.toString());
                        if(!response.isEmpty()){ // Server replies with the list of contributions
                            List<Contribution> contList = new ArrayList<>();
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
                Log.d("LOG", error.toString());
                // TODO if the request fails, show a warning.
                hideProgressDialog();
            }
        });

        Globals.mRequestQueue.add(stringRequest);
    }
}
