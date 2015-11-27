package bounswe2015group5.xplore.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import bounswe2015group5.xplore.Globals;
import bounswe2015group5.xplore.MainActivity;
import bounswe2015group5.xplore.R;
import bounswe2015group5.xplore.Signup;
import bounswe2015group5.xplore.adapters.ContributionListAdapter;
import bounswe2015group5.xplore.models.Contribution;

/**
 * Last updated by Mert Oguz on 7/11/2015.
 */
public class ContributionList extends Fragment {

    private ArrayList<Contribution> contributions;
    private ContributionListAdapter listAdapter;
    private Boolean isContListLoaded;
    private PullToRefreshListView contList;
    private ProgressDialog pDialog;

    public ContributionList(){
        contributions = new ArrayList<Contribution>();
        isContListLoaded = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        RelativeLayout parent = (RelativeLayout) inflater.inflate(R.layout.contribution_list, null);

        contList = (PullToRefreshListView) parent.findViewById(R.id.pull_to_refresh_listview);
        contList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                populateData();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) parent.findViewById(R.id.fab);
        if(Globals.share.getBoolean("signedIn",false)) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) getActivity()).launchFragment(new ContributionCreation(), "ConributionCreation");
                }
            });
        } else {
            fab.setImageResource(R.drawable.login_btn);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), Signup.class));
                    getActivity().finishAffinity();
                }
            });
        }

        listAdapter = new ContributionListAdapter(getActivity().getApplicationContext(), contributions);
        contList.getRefreshableView().setAdapter(listAdapter);

        if(!isContListLoaded){
            showProgressDialog();
            populateData();
        }

        contList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Contribution selected = (Contribution) adapterView.getItemAtPosition(position);
                ((MainActivity) getActivity()).launchFragment(newDetailFragment(selected.getTitle(), selected.getContent(),
                        selected.getName() + " " + selected.getSurname(), selected.getDate()), "ContributionDetail");
            }
        });

        return parent;
    }

    public static ContributionDetail newDetailFragment(String title, String content, String nameSurname, String date){
        ContributionDetail myDetailFragment = new ContributionDetail();
        Bundle args = new Bundle();
        args.putString("title",title);
        args.putString("content",content);
        args.putString("nameSurname",nameSurname);
        args.putString("date", date);

        myDetailFragment.setArguments(args);

        return myDetailFragment;
    }

    @Override
    public void onResume() {
        super.onResume();

        populateData();
    }

    private void populateData() {

        //Not sure, will check.
        final String URL = getString(R.string.service_url) + "AllContributions"; //for POST to server

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("LOG", response.toString());
                        if(!response.isEmpty()){ // Server replies with the list of contributions
                            contributions.clear();
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

                                    listAdapter.notifyDataSetChanged();
                                }
                                isContListLoaded = true;
                                contList.onRefreshComplete();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else //unsuccessful register attempt
                            Toast.makeText(getActivity().getApplicationContext(), "Unsuccessful Register Attempt", Toast.LENGTH_SHORT).show();
                        hideProgressDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("LOG", error.toString());
                hideProgressDialog();
            }
        });

        Globals.mRequestQueue.add(stringRequest);
    }

    private void showProgressDialog(){
        if(pDialog == null)
            pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please wait..");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void hideProgressDialog(){
        if(pDialog != null && pDialog.isShowing())
            pDialog.dismiss();
    }
}
