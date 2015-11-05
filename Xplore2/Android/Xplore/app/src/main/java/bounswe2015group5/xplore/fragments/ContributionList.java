package bounswe2015group5.xplore.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

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

        LinearLayout parent = (LinearLayout) inflater.inflate(R.layout.contribution_list, null);
        ListView contList = (ListView) parent.findViewById(R.id.contList);

        populateData();

        contList.setAdapter(new ContributionListAdapter(getActivity().getApplicationContext(), contributions));

        return parent;
    }

    private void populateData() {
        contributions = new ArrayList<>();

        for(int i=1; i<10; i++)
            contributions.add(new Contribution("Contribution " + i));
    }
}
