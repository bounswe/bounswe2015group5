package bounswe2015group5.xplore.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import bounswe2015group5.xplore.Globals;
import bounswe2015group5.xplore.MainActivity;
import bounswe2015group5.xplore.R;

/**
 * Created by Mert on 21.12.2015.
 */
public class Profile extends BaseFragment {
    private LinearLayout contributionList;
    private ImageView pic;
    private TextView tvUsername;
    private TextView tvEmail;
    private TextView tvTags;

    public Profile(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ScrollView parent = (ScrollView) inflater.inflate(R.layout.profile, null);

        tvUsername = (TextView) parent.findViewById(R.id.profile_username);
        tvEmail = (TextView) parent.findViewById(R.id.profile_email);

        tvUsername.setText(Globals.share.getString("username","Guest user"));
        tvEmail.setText(Globals.share.getString("email",""));
        return parent;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setTitle("Profile");
    }
}
