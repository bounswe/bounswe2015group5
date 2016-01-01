package bounswe2015group5.xplore.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bounswe2015group5.xplore.Globals;
import bounswe2015group5.xplore.MainActivity;
import bounswe2015group5.xplore.R;
import bounswe2015group5.xplore.models.Tag;
/**
 * Created by hakansahin on 20/12/15.
 */
public class TagDetail extends BaseFragment{

    private final int TAG_COUNT = 5;
    private final int[] TAG_BUTTON_IDS = {R.id.tagBtnCenter, R.id.tagBtnUL, R.id.tagBtnDR, R.id.tagBtnUR, R.id.tagBtnDL};

    private RelativeLayout parent;
    private List<Button> tagButtonList;
    private List<Tag> tagList;
    private Animation pulse;
    private int TAG_ID;
    private String TAG_NAME;
    private boolean isCreated;

    public TagDetail(){

        this.isCreated = false;
        this.tagList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parent = (RelativeLayout) inflater.inflate(R.layout.tag_detail, null);

        TAG_ID = getArguments().getInt("TAGID");
        TAG_NAME = getArguments().getString("TAGNAME");

        ((MainActivity) getActivity()).setTitle(TAG_NAME);

        // Adds all tag buttons into an arraylist.
        tagButtonList = new ArrayList<>();
        for(int id : TAG_BUTTON_IDS)
            tagButtonList.add((Button) parent.findViewById(id));

        // Creates pulse animation in order to show the tag which is hot.
        pulse = AnimationUtils.loadAnimation(getActivity(), R.anim.pulse);

        if(!isCreated){
            populateTags();
            isCreated = true;
        }
        else {
            stopAnim();

            Log.d("TagDetail","isCreated");
            Log.d("TagDetail","TagList: " + tagList.size());

            int btnCnt = 1;
            for(Tag tag : tagList){

                int tagId = tag.getID();

                final Button tagBtn;
                if(tagId == TAG_ID) tagBtn = tagButtonList.get(0);
                else{
                    tagBtn = tagButtonList.get(btnCnt);
                    btnCnt++;
                }

                prepareTagButton(tagBtn, tagId, tag.getName());
            }
        }
        return parent;
    }

    /**
     * Gets Related Tags and creates related tag buttons.
     */
    public void populateTags(){

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
                                tagList.add(tag);

                                prepareTagButton(tagBtn, tag.getID(), tag.getName());
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

    public void stopAnim(){
        parent.findViewById(R.id.tagCenterLoadingView).setVisibility(View.GONE);
        parent.findViewById(R.id.tagCenterLoadingText).setVisibility(View.GONE);
    }

    public void prepareTagButton(Button tagButton, final int tagId, final String tagName){

        tagButton.setVisibility(View.VISIBLE);
        // TODO if the tag is hot, then start animation.
        tagButton.startAnimation(pulse);

        tagButton.setText("#" + tagName);

        tagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if the centered tag is selected, shows its contributions.
                if(TAG_ID == tagId) showContributionList();
                else{
                    Fragment fragment = new TagDetail();
                    Bundle args = new Bundle();
                    args.putInt("TAGID", tagId);
                    args.putString("TAGNAME",tagName);
                    fragment.setArguments(args);

                    ((MainActivity) getActivity()).launchFragment(fragment, "#" + tagName, false);
                }
            }
        });

    }

    public void showContributionList(){

        Fragment fragment = new ContributionList();
        Bundle args = new Bundle();
        args.putInt("TAGID",TAG_ID);
        args.putString("TAGNAME",TAG_NAME);
        fragment.setArguments(args);

        ((MainActivity) getActivity()).launchFragment(fragment, "Contributions", false);

    }
}
