package bounswe2015group5.xplore.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

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
import bounswe2015group5.xplore.MainActivity;
import bounswe2015group5.xplore.R;
import bounswe2015group5.xplore.adapters.ExpandableListAdapter;
import bounswe2015group5.xplore.models.Comment;
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

    private ArrayList<Comment> commentList;

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

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                Contribution selected = (Contribution)listAdapter.getChild(groupPosition,childPosition);
                showProgressDialog();
                fetchComments(""+selected.getId());
                ((MainActivity) getActivity()).launchFragment(newDetailFragment(selected, commentList), "ContributionDetail");
                return true;
            }
        });

        return parent;
    }

    public static ContributionDetail newDetailFragment(Contribution c, ArrayList<Comment> comments){
        ContributionDetail myDetailFragment = new ContributionDetail();
        Bundle args = new Bundle();
        args.putSerializable("Contribution", c);
        args.putSerializable("Comments",comments);
        myDetailFragment.setArguments(args);

        return myDetailFragment;
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

    public void fetchComments(final String id){
        final String URL = getString(R.string.service_url) + "CommentsByContributionID"; //for POST to server

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("LOG","response is received");
                        Log.d("LOG", response.toString());
                        if(!response.toString().isEmpty()){ // Server replies with the list of contributions
                            Log.d("LOG","response is not empty");
                            try {
                                JSONArray jArray = new JSONArray(response);
                                int numComments = jArray.length();
                                for(int i = 0; i < numComments; i++){
                                    JSONObject jsonObject = jArray.getJSONObject(i);
                                    Comment comment = new Comment(jsonObject);
                                    commentList.add(comment);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else{
                            System.err.println("response is empty");
                        }
                        hideProgressDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("LOG_ERROR", error.toString());
                hideProgressDialog();
                // TODO if the request fails, show a warning.
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> mParams = new HashMap<>();
                mParams.put("ContributionID",id);
                return mParams;
            }
        };

        Globals.mRequestQueue.add(jsonObjectRequest);
    }

}
