package bounswe2015group5.xplore.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.Response;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import bounswe2015group5.xplore.Globals;
import bounswe2015group5.xplore.MainActivity;
import bounswe2015group5.xplore.R;
import bounswe2015group5.xplore.adapters.ContributionListAdapter;
import bounswe2015group5.xplore.models.Contribution;

/**
 * Last updated by Mert Oguz on 7/11/2015.
 */
public class ContributionList extends BaseFragment {

    private RelativeLayout parent;
    private int TAG_ID;
    private String TAG_NAME;
    private ArrayList<Contribution> contributions;
    private ContributionListAdapter listAdapter;
    private Boolean isContListLoaded;
    private PullToRefreshListView contList;

    public ContributionList(){
        contributions = new ArrayList<>();
        isContListLoaded = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        parent = (RelativeLayout) inflater.inflate(R.layout.contribution_list, null);

        TAG_ID = getArguments().getInt("TAGID");

        ((MainActivity) getActivity()).setTitle("Contributions");

        if(!isContListLoaded) populateContributions(TAG_ID);
        contList = (PullToRefreshListView) parent.findViewById(R.id.contributionList);
        contList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                populateContributions(TAG_ID);
            }
        });

        contList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Contribution selected = (Contribution) adapterView.getItemAtPosition(position);
                ((MainActivity) getActivity()).launchFragment(newDetailFragment(selected), "ContributionDetail", false);
            }
        });

        listAdapter = new ContributionListAdapter(Globals.appContext, contributions);
        contList.getRefreshableView().setAdapter(listAdapter);

        return parent;
    }

    public ContributionDetail newDetailFragment(Contribution contribution){
        ContributionDetail myDetailFragment = new ContributionDetail();
        Bundle args = new Bundle();
        args.putSerializable("Contribution", contribution);
        myDetailFragment.setArguments(args);

        return myDetailFragment;
    }

    private void populateContributions(int tagId) {

        Response.Listener<JSONArray> responseListener =
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length() > 0){

                            stopAnim();
                            contributions.clear();
                            for(int i = 0; i < response.length(); i++){
                                JSONObject contributionJSON = response.optJSONObject(i);
                                if(contributionJSON == null) continue;

                                contributions.add(new Contribution(contributionJSON));
                                listAdapter.notifyDataSetChanged();
                            }
                        } // TODO if response is empty, show a warning.
                        contList.onRefreshComplete();
                    }
                };

        Globals.connectionManager.getContributionsByTagId(tagId, responseListener);
    }

    public void stopAnim(){
        parent.findViewById(R.id.contListLoadingView).setVisibility(View.GONE);
        parent.findViewById(R.id.contListLoadingText).setVisibility(View.GONE);

        contList.setVisibility(View.VISIBLE);
    }
}
