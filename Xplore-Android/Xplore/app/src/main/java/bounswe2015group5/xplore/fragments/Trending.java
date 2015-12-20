package bounswe2015group5.xplore.fragments;

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
    private List<Button> tagButtonList;
    private Animation pulse;

    public Trending(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parent = (RelativeLayout) inflater.inflate(R.layout.trending, null);

        // Adds all tag buttons into an arraylist.
        tagButtonList = new ArrayList<>();
        for(int id : TAG_BUTTON_IDS)
            tagButtonList.add((Button) parent.findViewById(id));

        // Creates pulse animation in order to show the tag which is hot.
        pulse = AnimationUtils.loadAnimation(getActivity(), R.anim.pulse);

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

                            final Button tagBtn = tagButtonList.get(i);

                            tagBtn.setVisibility(View.VISIBLE);
                            tagBtn.startAnimation(pulse);

                            tagBtn.setText("#" + tag.getName());

                            tagBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    Fragment fragment = new TagDetail();
                                    Bundle args = new Bundle();
                                    args.putInt("TAGID",tag.getID());
                                    args.putString("TAGNAME",tag.getName());
                                    fragment.setArguments(args);

                                    ((MainActivity) getActivity()).launchFragment(fragment, "#" + tag.getName());
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

    void stopAnim(){
        parent.findViewById(R.id.tagCenterLoadingView).setVisibility(View.GONE);
        parent.findViewById(R.id.tagCenterLoadingText).setVisibility(View.GONE);
    }
}
