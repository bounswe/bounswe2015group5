package bounswe2015group5.xplore.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bounswe2015group5.xplore.Globals;
import bounswe2015group5.xplore.MainActivity;
import bounswe2015group5.xplore.R;
import bounswe2015group5.xplore.adapters.ContributionListAdapter;
import bounswe2015group5.xplore.models.Contribution;
import bounswe2015group5.xplore.models.Tag;
/**
 * Created by hakansahin on 20/12/15.
 */
public class TagDetail extends BaseFragment{

    private final int TAG_COUNT = 5;
    private final int[] TAG_BUTTON_IDS = {R.id.tagBtnCenter, R.id.tagBtnUL, R.id.tagBtnDR, R.id.tagBtnUR, R.id.tagBtnDL};

    private RelativeLayout parent, tagBtnLayout;
    private TextView contListText;
    private ArrayList<Tag> tagList;
    private ArrayList<Contribution> contributions;
    private List<Button> tagButtonList;
    private Animation pulse;
    private boolean isContListOpen;
    private int TAG_ID;
    private ContributionListAdapter listAdapter;
    private PullToRefreshListView contList;

    public TagDetail(){

        // Initializes tag button list.
        tagButtonList = new ArrayList<>();

        // Initializes contribution list.
        contributions = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parent = (RelativeLayout) inflater.inflate(R.layout.tag_detail, null);
        tagBtnLayout = (RelativeLayout) parent.findViewById(R.id.tagBtnLayout);

        TAG_ID = getArguments().getInt("TAGID");

        // Adds all tag buttons into an arraylist.
        for(int id : TAG_BUTTON_IDS)
            tagButtonList.add((Button) parent.findViewById(id));

        // Creates pulse animation in order to show the tag which is hot.
        pulse = AnimationUtils.loadAnimation(getActivity(), R.anim.pulse);

        populateTags();

        isContListOpen = false;
        contListText = (TextView) parent.findViewById(R.id.contributionText);
        contListText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isContListOpen) hideContributionList();
                else showContributionList();
            }
        });

        // Populates contribution list, and creates its views.
        populateContributions(TAG_ID);
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
                ((MainActivity) getActivity()).launchFragment(newDetailFragment(selected), "ContributionDetail");
            }
        });

        listAdapter = new ContributionListAdapter(getActivity().getApplicationContext(), contributions);
        contList.getRefreshableView().setAdapter(listAdapter);

        return parent;
    }

    /**
     * Gets Trending Tags and creates related tag buttons.
     */
    public void populateTags(){

        tagList = new ArrayList<>();

        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Iterator<String> tagIds = response.keys();
                if(tagIds.hasNext()){
                    stopAnim();
                    int cnt = 0, btnCnt = 1;
                    while(tagIds.hasNext() && cnt < TAG_COUNT){

                        int tagId = Integer.parseInt(tagIds.next());

                        final Button tagBtn;
                        if(tagId == TAG_ID) tagBtn = tagButtonList.get(0);
                        else{
                            tagBtn = tagButtonList.get(btnCnt);
                            btnCnt++;
                        }

                        Response.Listener<JSONObject> tagResponseListener = new Response.Listener<JSONObject>(){
                            @Override
                            public void onResponse(JSONObject response) {

                                final Tag tag = new Tag(response);

                                tagBtn.setVisibility(View.VISIBLE);
                                // TODO if the tag is hot, then start animation.
                                tagBtn.startAnimation(pulse);

                                tagBtn.setText("#" + tag.getName());

                                tagBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        // if the centered tag is selected, shows its contributions.
                                        if(TAG_ID == tag.getID()) showContributionList();
                                        else{
                                            Fragment fragment = new TagDetail();
                                            Bundle args = new Bundle();
                                            args.putInt("TAGID",tag.getID());
                                            args.putString("TAGNAME",tag.getName());
                                            fragment.setArguments(args);

                                            ((MainActivity) getActivity()).launchFragment(fragment, "#" + tag.getName());
                                        }
                                    }
                                });

                            }
                        };

                        Globals.connectionManager.getTag(tagId, tagResponseListener);
                        cnt++;
                    }
                } // TODO if response is empty, show a warning.
            }
        };

        // TODO construct an error listener. (hideProgressDialog)
        Globals.connectionManager.getRelatedTags(TAG_ID, responseListener);
    }

    private void populateContributions(int tagId) {

        Response.Listener<JSONArray> responseListener =
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length() > 0){
                            try {
                                contributions.clear();
                                for(int i = 0; i < response.length(); i++){
                                    Contribution contribution = new Contribution(response.getJSONObject(i));
                                    contributions.add(contribution);

                                    listAdapter.notifyDataSetChanged();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } // TODO if response is empty, show a warning.
                        contList.onRefreshComplete();
                    }
                };

        Globals.connectionManager.getContributionsByTagId(tagId, responseListener);
    }


    public ContributionDetail newDetailFragment(Contribution contribution){
        ContributionDetail myDetailFragment = new ContributionDetail();
        Bundle args = new Bundle();
        args.putSerializable("Contribution", contribution);
        myDetailFragment.setArguments(args);

        return myDetailFragment;
    }

    public void stopAnim(){
        parent.findViewById(R.id.tagCenterLoadingView).setVisibility(View.GONE);
        parent.findViewById(R.id.tagCenterLoadingText).setVisibility(View.GONE);
    }

    /**
     * Shows the contribution list of the tag.
     * Tag buttons will be invisible.
     */
    public void showContributionList(){

        isContListOpen = true;

        tagBtnLayout.setVisibility(View.GONE);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) contListText.getLayoutParams();
        params.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        contListText.setLayoutParams(params);

    }

    /**
     * Shows tag buttons.
     * The contribution list will be invisible.
     */
    public void hideContributionList(){

        isContListOpen = false;

        tagBtnLayout.setVisibility(View.VISIBLE);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) contListText.getLayoutParams();
        params.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        contListText.setLayoutParams(params);
    }
}
