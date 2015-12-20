package bounswe2015group5.xplore.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

import java.util.ArrayList;
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
    private String TAG_NAME;
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
        TAG_NAME = getArguments().getString("TAGNAME");

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
        populateContributions(TAG_NAME);
        contList = (PullToRefreshListView) parent.findViewById(R.id.contributionList);
        contList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                populateContributions(TAG_NAME);
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

        Response.Listener<JSONArray> responseListener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response.length() > 0){
                    try {
                        stopAnim();

                        for(int i = 0; i < Math.min(TAG_COUNT,response.length()); i++){

                            final Tag tag = new Tag(response.getJSONObject(i));

                            tagList.add(tag);

                            final Button tagBtn = tagButtonList.get(i);

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
                                        // TODO Go to detail page of the selected tag.
                                        tagBtn.clearAnimation();

                                        ((MainActivity) getActivity()).setTitle("#" + tag.getName());
                                    }
                                }
                            });

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } // TODO if response is empty, show a warning.
            }
        };

        // TODO construct an error listener. (hideProgressDialog)
        Globals.connectionManager.getAllTags(responseListener);
    }

    private void populateContributions(String tag) {

        Response.Listener<String> responseListener =
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(!response.isEmpty()){
                            try {
                                JSONArray jArray = new JSONArray(response);
                                contributions.clear();
                                for(int i = 0; i < jArray.length(); i++){
                                    Contribution contribution = new Contribution(jArray.getJSONObject(i));
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

        Globals.connectionManager.searchByTag(tag, responseListener);
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

    @Override
    }
}
