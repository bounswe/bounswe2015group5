package bounswe2015group5.xplore.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;

import bounswe2015group5.xplore.Globals;
import bounswe2015group5.xplore.MainActivity;
import bounswe2015group5.xplore.R;
import bounswe2015group5.xplore.models.Tag;

/**
 * Created by Mert on 6.01.2016.
 */
public class AddTagToContribution extends BaseFragment {
    private LinearLayout existingTags;
    private Button addNewTagBtn;

    public AddTagToContribution(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ScrollView parent = (ScrollView) inflater.inflate(R.layout.add_tag_to_contribution,null);

        existingTags = (LinearLayout) parent.findViewById(R.id.addtagtocont_existing_tags);
        addNewTagBtn = (Button) parent.findViewById(R.id.addtagtocont_addtagbtn);
        addNewTagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Redirect to "Add New Tag page"
            }
        });

        showExistingTags();

        return parent;
    }

    private void showExistingTags() {
        Response.Listener<JSONArray> responseListener =
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("FETCH ALL TAGS", response.toString());
                        if(response.length() > 0){
                            try {
                                for(int i = 0; i < response.length(); i++){
                                    Tag tag = new Tag(response.getJSONObject(i));
                                    addTag(tag);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else{
                            System.err.println("response is empty");
                        }
                    }
                };
        // TODO construct an error listener.
        Globals.connectionManager.getAllTags(responseListener);
    }

    private void addTag(final Tag tag) {
        TextView tagView = new TextView(Globals.appContext);
        tagView.setText(tag.getName());
        tagView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Response.Listener<JSONArray> responseListener =
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                if(response.length() > 0){
                                    Log.d("AddTagToContribution", response.toString());
                                    ((MainActivity) getActivity()).onBackPressed();
                                }
                                else{
                                    System.err.println("response is empty");
                                }
                            }
                        };
                // TODO construct an error listener.
                int contId = getArguments().getInt("contId");
                Globals.connectionManager.addTagToContribution(contId,tag.getID(),responseListener);

                /*Fragment fragment = new TagDetail();
                Bundle args = new Bundle();
                args.putInt("TAGID",tag.getID());
                args.putString("TAGNAME",tag.getName());
                fragment.setArguments(args);

                ((MainActivity) getActivity()).launchFragment(fragment, "#" + tag.getName(), false);*/
            }
        });

        tagView.setTextColor(Globals.appContext.getResources().getColor(R.color.tab_btn_text));
        tagView.setPadding(5, 5, 5, 5);

        existingTags.addView(tagView);
    }
}
