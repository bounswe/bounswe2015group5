package bounswe2015group5.xplore.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bounswe2015group5.xplore.R;
/**
 * Created by hakansahin on 06/11/15.
 */
public class ContributionCreation extends Fragment{

    public ContributionCreation(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contribution_creation,null);
    }
}
