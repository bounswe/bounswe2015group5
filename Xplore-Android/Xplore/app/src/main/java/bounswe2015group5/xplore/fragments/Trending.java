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
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import bounswe2015group5.xplore.Globals;
import bounswe2015group5.xplore.MainActivity;
import bounswe2015group5.xplore.R;
import bounswe2015group5.xplore.models.Tag;
/**
 * Created by hakansahin on 19/12/15.
 */
public class Trending extends BaseFragment {

    private final int TAG_COUNT = 5;
    private final int[] TAG_BUTTON_IDS = {R.id.tagBtnCenter, R.id.tagBtnUL, R.id.tagBtnDR, R.id.tagBtnUR, R.id.tagBtnDL};

    private RelativeLayout parent;
    private List<Tag> tagList;
    private Button[] tagButtonList;
    private int connectionSize;
//    private Animation pulse;

    public Trending(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parent = (RelativeLayout) inflater.inflate(R.layout.trending, null);

        // Adds all tag buttons into an arraylist.
        tagButtonList = new Button[TAG_COUNT];
        for(int i=0; i<TAG_COUNT; i++)
            tagButtonList[i] = (Button) parent.findViewById(TAG_BUTTON_IDS[i]);

        // Creates pulse animation in order to show the tag which is hot.
        //pulse = AnimationUtils.loadAnimation(getActivity(), R.anim.pulse);

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int w=dm.widthPixels, h=dm.heightPixels;
        connectionSize = (int) Math.sqrt(w*w+h*h) / 3;

        populateTags();

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

                            final Button tagBtn = tagButtonList[i];

                            tagBtn.setVisibility(View.VISIBLE);
//                            tagBtn.startAnimation(pulse);

                            tagBtn.setText("#" + tag.getName());

                            tagBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    Fragment fragment = new TagDetail();
                                    Bundle args = new Bundle();
                                    args.putInt("TAGID",tag.getID());
                                    args.putString("TAGNAME",tag.getName());
                                    fragment.setArguments(args);

                                    ((MainActivity) getActivity()).launchFragment(fragment, "#" + tag.getName(), false);
                                }
                            });
                        }

                        addCenterConnections();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } // TODO if response is empty, show a warning.
            }
        };

        // TODO construct an error listener. (hideProgressDialog)
        Globals.connectionManager.getAllTags(responseListener);
    }

    void stopAnim(){
        parent.findViewById(R.id.tagCenterLoadingView).setVisibility(View.GONE);
        parent.findViewById(R.id.tagCenterLoadingText).setVisibility(View.GONE);
    }

    public void addCenterConnections(){

        Button centerBtn = tagButtonList[0];

        float   x1 = centerBtn.getX() + centerBtn.getWidth() / 2,
                y1 = centerBtn.getY() + centerBtn.getHeight() / 2;

        for(int i=1; i<TAG_COUNT; i++){

            Button  btn = tagButtonList[i];

            float   x2 = btn.getX() + btn.getWidth() / 2,
                    y2 = btn.getY() + btn.getHeight() / 2;

            addConnection(new Point((int) x1, (int) y1), new Point((int) x2, (int) y2));
        }
    }

    public void addConnection(Point p1, Point p2){

        int xDiff = p1.x - p2.x,
                yDiff = p1.y - p2.y;

        View connection = new View(Globals.appContext);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(connectionSize, 10);
        layoutParams.setMargins(p1.x, p1.y, 0, 0);
        connection.setLayoutParams(layoutParams);
        connection.setBackgroundColor(ContextCompat.getColor(Globals.appContext, R.color.tab_btn_text));

        float degree = 57.0f;
        if(xDiff > 0 && yDiff > 0) degree -= 180.0f;
        else if(xDiff > 0 && yDiff < 0) degree = 180 - degree;
        else if(xDiff < 0 && yDiff > 0) degree *= -1;
        else if(xDiff == 0 && yDiff > 0) degree = -90.0f;
        else if(xDiff == 0 && yDiff < 0) degree = 90.0f;
        else if(yDiff == 0) degree = 0.0f;

        parent.addView(connection,0);

        AnimationSet animSet = new AnimationSet(true);
        animSet.setInterpolator(new DecelerateInterpolator());
        animSet.setFillAfter(true);
        animSet.setFillEnabled(true);

        final RotateAnimation animRotate = new RotateAnimation(0.0f, degree,
                RotateAnimation.RELATIVE_TO_SELF, 0.0f,
                RotateAnimation.RELATIVE_TO_SELF, 0.0f);

        animRotate.setDuration(0);
        animRotate.setFillAfter(true);
        animSet.addAnimation(animRotate);

        connection.startAnimation(animSet);
    }
}
