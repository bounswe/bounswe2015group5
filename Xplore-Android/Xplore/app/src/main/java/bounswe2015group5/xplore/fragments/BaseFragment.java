package bounswe2015group5.xplore.fragments;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
/**
 * Created by hakansahin on 24/11/15.
 */
public class BaseFragment extends Fragment {
    private ProgressDialog pDialog;

    public BaseFragment(){}

    protected void showProgressDialog(){
        if(pDialog == null)
            pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please wait..");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    protected void hideProgressDialog(){
        if(pDialog != null && pDialog.isShowing())
            pDialog.dismiss();
    }
}
