package bounswe2015group5.xplore.fragments;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    private final int[] TAG_BUTTON_PREV_IDS = {R.id.tagBtnCenter, R.id.tagBtnULPREV, R.id.tagBtnDRPREV, R.id.tagBtnURPREV, R.id.tagBtnDLPREV};

    private RelativeLayout parent;
    private Button[] tagButtonList, tagButtonPrevList;
    private List<Tag> tagList;
    private Point[] animationPoints;
    private Animation pulse;
    private int TAG_ID;
    private String TAG_NAME;
    private boolean isCreated;

    public TagDetail(){

        this.isCreated = false;
        this.tagList = new ArrayList<>();

        // Initializes tag button arrays.
        tagButtonList = new Button[TAG_BUTTON_IDS.length];
        tagButtonPrevList = new Button[TAG_BUTTON_PREV_IDS.length];

        animationPoints = new Point[TAG_BUTTON_IDS.length];
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parent = (RelativeLayout) inflater.inflate(R.layout.tag_detail, null);

        TAG_ID = getArguments().getInt("TAGID");
        TAG_NAME = getArguments().getString("TAGNAME");

        // Adds all tag buttons into an array.
        for(int i = 0; i<TAG_BUTTON_IDS.length; i++) tagButtonList[i] = ((Button) parent.findViewById(TAG_BUTTON_IDS[i]));
        for(int i = 0; i<TAG_BUTTON_PREV_IDS.length; i++) tagButtonPrevList[i] = ((Button) parent.findViewById(TAG_BUTTON_PREV_IDS[i]));

        // Creates pulse animation in order to show the tag which is hot.
        pulse = AnimationUtils.loadAnimation(getActivity(), R.anim.pulse);

        if(!isCreated){
            populateTags();
            isCreated = true;
        }
        else {
            stopAnim();

            int btnCnt = 1;
            for(Tag tag : tagList){

                int tagId = tag.getID();
                if(tagId == TAG_ID)  prepareTagButton(tagButtonList[0], animationPoints[0], tagId, tag.getName());
                else{
                    prepareTagButton(tagButtonList[btnCnt], animationPoints[btnCnt], tagId, tag.getName());
                    btnCnt++;
                }
            }
        }

        ((MainActivity) getActivity()).setTitle(TAG_NAME);

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
                        final Point position;
                        if(tagId == TAG_ID) {
                            tagBtn = tagButtonList[0];
                            position = computePosition(0);
                        } else{
                            tagBtn = tagButtonList[btnCnt];
                            position = computePosition(btnCnt);
                            btnCnt++;
                        }

                        Response.Listener<JSONObject> tagResponseListener = new Response.Listener<JSONObject>(){
                            @Override
                            public void onResponse(JSONObject response) {

                                final Tag tag = new Tag(response);
                                tagList.add(tag);

                                prepareTagButton(tagBtn, position, tag.getID(), tag.getName());
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

    public void prepareTagButton(Button tagButton, Point position, final int tagId, final String tagName){

        tagButton.setVisibility(View.VISIBLE);
        tagButton.animate().translationXBy(position.x).translationYBy(position.y);

        // TODO if the tag is hot, then start animation.
        //tagButton.startAnimation(pulse);

        tagButton.setText("#" + tagName);

        tagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if the centered tag is selected, shows its contributions.
                if(TAG_ID == tagId) createContributionList();
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

    public void createContributionList(){

        Fragment fragment = new ContributionList();
        Bundle args = new Bundle();
        args.putInt("TAGID",TAG_ID);
        args.putString("TAGNAME",TAG_NAME);
        fragment.setArguments(args);

        ((MainActivity) getActivity()).launchFragment(fragment, "Contributions", false);
    }

    public Point computePosition(int buttonCnt){

        Button original = tagButtonList[buttonCnt];
        Button newButton = tagButtonPrevList[buttonCnt];

        int xDiff = original.getLeft() - newButton.getLeft();
        int yDiff = original.getTop() - newButton.getTop();

        Point position = new Point(xDiff,yDiff);
        animationPoints[buttonCnt] = position;

        return position;
    }
}
