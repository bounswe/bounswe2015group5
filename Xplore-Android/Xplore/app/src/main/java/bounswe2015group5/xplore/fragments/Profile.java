package bounswe2015group5.xplore.fragments;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONArray;
import org.w3c.dom.Text;


import bounswe2015group5.xplore.EditProfile;
import bounswe2015group5.xplore.Globals;
import bounswe2015group5.xplore.MainActivity;
import bounswe2015group5.xplore.R;
import bounswe2015group5.xplore.models.Contribution;


/**
 * Created by Mert on 21.12.2015.
 */
public class Profile extends BaseFragment {
    private LinearLayout contributionList,contributions;
    private ImageView pic;
    private TextView tvUsername;
    private TextView tvEmail;
    private TextView dummy;
    private TextView edit;

    public Profile(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ScrollView parent = (ScrollView) inflater.inflate(R.layout.profile, null);

        tvUsername = (TextView) parent.findViewById(R.id.profile_username);
        tvEmail = (TextView) parent.findViewById(R.id.profile_email);

        tvUsername.setText(Globals.share.getString("username","Guest user"));
        tvEmail.setText(Globals.share.getString("email", ""));

        edit = (TextView) parent.findViewById(R.id.profile_editProfile);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfile.class);
                startActivity(intent);
            }
        });
        dummy = (TextView) parent.findViewById(R.id.profile_contDummyText);
        contributions = (LinearLayout) parent.findViewById(R.id.profile_contributions);

        if(Globals.share.getBoolean("SignedIn",false)){ //if signed in
            dummy.setVisibility(View.INVISIBLE);
            fetchContributions();
        }
        else{
            edit.setVisibility(View.INVISIBLE);
        }

        return parent;
    }

    public void fetchContributions(){

        Response.Listener<JSONArray> responseListener =
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length() > 0){
                            addContribution(response);
                        }
                        else System.err.println("response is empty");
                    }
                };

        // TODO construct an error listener.
        Globals.connectionManager.getContributionsByUsername(Globals.share.getString("username",""), responseListener);
    }

    private void addContribution(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++){
            final Contribution contrib = (new Contribution(jsonArray.optJSONObject(i)));
            TextView contribView = new TextView(Globals.appContext);
            contribView.setText(contrib.getTitle());
            contribView.setTextColor(Color.parseColor("#009fe3"));
            contribView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ((MainActivity) getActivity()).launchFragment(newDetailFragment(contrib), "ContributionDetail", false);
                }
            });

            contributions.addView(contribView);

        }
    }

    public ContributionDetail newDetailFragment(Contribution contribution){
        ContributionDetail myDetailFragment = new ContributionDetail();
        Bundle args = new Bundle();
        args.putSerializable("Contribution", contribution);
        myDetailFragment.setArguments(args);

        return myDetailFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setTitle("Profile");
    }
}
