package bounswe2015group5.xplore.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import bounswe2015group5.xplore.Login;
import bounswe2015group5.xplore.MainActivity;
import bounswe2015group5.xplore.R;
import bounswe2015group5.xplore.adapters.ContributionListAdapter;
import bounswe2015group5.xplore.models.Contribution;

/**
 * Created by hakansahin on 05/11/15.
 */
public class ContributionList extends Fragment {

    private ArrayList<Contribution> contributions;

    public ContributionList(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        RelativeLayout parent = (RelativeLayout) inflater.inflate(R.layout.contribution_list, null);
        ListView contList = (ListView) parent.findViewById(R.id.contList);
        FloatingActionButton fab = (FloatingActionButton) parent.findViewById(R.id.fab);

        if(Login.share.getBoolean("signedIn",false)) {

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) getActivity()).launchFragment(new ContributionCreation(), "Conribution Creation");
                }
            });
        } else fab.setVisibility(View.INVISIBLE);

        populateData();

        contList.setAdapter(new ContributionListAdapter(getActivity().getApplicationContext(), contributions));

        return parent;
    }

    private void populateData() {
        contributions = new ArrayList<>();

        for(int i=1; i<20; i++)
            contributions.add(new Contribution("Contribution " + i));
    }
}
