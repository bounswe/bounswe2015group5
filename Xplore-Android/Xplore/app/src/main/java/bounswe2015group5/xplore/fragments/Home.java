package bounswe2015group5.xplore.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bounswe2015group5.xplore.Globals;
import bounswe2015group5.xplore.MainActivity;
import bounswe2015group5.xplore.R;
import bounswe2015group5.xplore.Signup;
import bounswe2015group5.xplore.adapters.ExpandableListAdapter;
import bounswe2015group5.xplore.models.Comment;
import bounswe2015group5.xplore.models.Contribution;
import bounswe2015group5.xplore.models.Tag;
/**
 * Created by hakansahin on 24/11/15.
 */
public class Home extends BaseFragment{

    private List<Tag> tagGroups;
    private HashMap<Integer, List<Contribution>> groupContributions;

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
                prepareGroupItems(tagGroups.get(groupPosition).getID());
            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                Contribution selected = listAdapter.getChild(groupPosition,childPosition);
                ((MainActivity) getActivity()).launchFragment(newDetailFragment(selected), "ContributionDetail", false);
                return true;
            }
        });

        return parent;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setTitle("Home");
    }

    public ContributionDetail newDetailFragment(Contribution contribution){
        ContributionDetail myDetailFragment = new ContributionDetail();
        Bundle args = new Bundle();
        args.putSerializable("Contribution", contribution);
        myDetailFragment.setArguments(args);

        return myDetailFragment;
    }

    private void prepareListData() {

        tagGroups = new ArrayList<>();
        groupContributions = new HashMap<>();

        Response.Listener<JSONArray> responseListener = new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length() > 0){ // Server replies with the list of contributions
                            try {
                                for(int i = 0; i < response.length(); i++)
                                    tagGroups.add(new Tag(response.getJSONObject(i)));

                                listAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } // TODO if response is empty, show a warning.
//                        hideProgressDialog();
                    }
                };

        // TODO construct an error listener. (hideProgressDialog)
        Globals.connectionManager.getAllTags(responseListener);
    }

    private void prepareGroupItems(final int tagId) {

        Response.Listener<JSONArray> responseListener =
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length() > 0){
                            List<Contribution> contList = new ArrayList<>();
                            try {
                                for(int i = 0; i < response.length(); i++){
                                    Contribution contribution = new Contribution(response.getJSONObject(i));
                                    contList.add(contribution);
                                }
                                groupContributions.put(tagId, contList);

                                listAdapter.notifyDataSetChanged();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } // TODO if response is empty, show a warning.
                    }
                };

        // TODO construct an error listener. (hideProgressDialog)
        Globals.connectionManager.getContributionsByTagId(tagId, responseListener);
    }
}
