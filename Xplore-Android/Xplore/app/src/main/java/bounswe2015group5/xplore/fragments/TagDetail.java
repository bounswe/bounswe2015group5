package bounswe2015group5.xplore.fragments;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
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
    private final int[] TAG_BUTTON_IDS = {R.id.tagBtnCenter, R.id.tagBtnUL, R.id.tagBtnDR, R.id.tagBtnDL, R.id.tagBtnUR};
    private final int[] TAG_BUTTON_PREV_IDS = {R.id.tagBtnCenter, R.id.tagBtnULPREV, R.id.tagBtnDRPREV, R.id.tagBtnDLPREV, R.id.tagBtnURPREV};

    private RelativeLayout parent;
    private Button[] tagButtonList, tagButtonPrevList;
    private List<View> connections;
    private List<Float> connectionDegrees;
    private List<Tag> tagList;
    private Point[] animationPoints;
    private Animation pulse;
    private int TAG_ID;
    private String TAG_NAME;
    private boolean isCreated, isConnectionsAdded;
    private int connectionSize, connectionCount;

    public TagDetail(){

        this.isCreated = false;
        this.isConnectionsAdded = false;
        this.connectionCount = 0;
        this.tagList = new ArrayList<>();

        // Initializes tag button arrays.
        tagButtonList = new Button[TAG_COUNT];
        tagButtonPrevList = new Button[TAG_COUNT];

        animationPoints = new Point[TAG_COUNT];
        connections = new ArrayList<>();
        connectionDegrees = new ArrayList<>();
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

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int w=dm.widthPixels, h=dm.heightPixels;
        connectionSize = (int) Math.sqrt(w*w+h*h) / 4 + 20;

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

            for(int i=0; i<connections.size(); i++){
                addConnection(connections.get(i), connectionDegrees.get(i));
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
                    int btnCnt = 1;
                    while(tagIds.hasNext() && connectionCount < TAG_COUNT){

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

                                if(!isConnectionsAdded){
                                    addCenterConnections(connectionCount);
                                    isConnectionsAdded = true;
                                }
                            }
                        };

                        Globals.connectionManager.getTag(tagId, tagResponseListener);
                        connectionCount++;
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
        tagButton.animate().translationXBy(position.x).translationYBy(position.y).setDuration(500);

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

        int xDiff = newButton.getLeft() - original.getLeft();
        int yDiff = newButton.getTop() - original.getTop();

        Point position = new Point(xDiff,yDiff);
        animationPoints[buttonCnt] = position;

        return position;
    }

    public View createConnection(Point p1){

        View connection = new View(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(connectionSize, 10);
        layoutParams.setMargins(p1.x, p1.y, 0, 0);
        connection.setLayoutParams(layoutParams);
        connection.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.tab_btn_text));

        connections.add(connection);

        return connection;
    }

    public void addCenterConnections(int cnt){

        Button centerBtn = tagButtonList[0];

        float   x1 = centerBtn.getX() + centerBtn.getWidth() / 2,
                y1 = centerBtn.getY() + centerBtn.getHeight() / 2;

        for(int i=1; i<cnt; i++){

            Button  btn = tagButtonPrevList[i];

            float   x2 = btn.getX() + btn.getWidth() / 2,
                    y2 = btn.getY() + btn.getHeight() / 2;

            Point p1 = new Point((int) x1, (int) y1),
                    p2 = new Point((int) x2, (int) y2);

            View connection = createConnection(p1);
            float degree = computeDegree(p1, p2);

            addConnection(connection, degree);
        }
    }

    public void addConnection(View connection, float degree){

        ViewGroup vg = (ViewGroup) connection.getParent();
        if(vg != null) vg.removeView(connection);

        parent.addView(connection,0);

        AnimationSet animSet = new AnimationSet(true);
        animSet.setInterpolator(new DecelerateInterpolator());
        animSet.setFillAfter(true);
        animSet.setFillEnabled(true);

        final RotateAnimation animRotate = new RotateAnimation(0.0f, degree,
                RotateAnimation.RELATIVE_TO_SELF, 0.0f,
                RotateAnimation.RELATIVE_TO_SELF, 0.0f);

        animRotate.setDuration(500);
        animRotate.setFillAfter(true);
        animSet.addAnimation(animRotate);

        connection.startAnimation(animSet);
    }

    public float computeDegree(Point p1, Point p2){

        int xDiff = p1.x - p2.x,
                yDiff = p1.y - p2.y;

        float degree = 57.0f;
        if(xDiff > 0 && yDiff > 0) degree -= 175.0f;
        else if(xDiff > 0 && yDiff < 0) degree = 180 - degree;
        else if(xDiff < 0 && yDiff > 0) degree -= 108;
        else if(xDiff == 0 && yDiff > 0) degree = -90.0f;
        else if(xDiff == 0 && yDiff < 0) degree = 90.0f;
        else if(yDiff == 0) degree = 0.0f;

        connectionDegrees.add(degree);
        return degree;
    }
}
