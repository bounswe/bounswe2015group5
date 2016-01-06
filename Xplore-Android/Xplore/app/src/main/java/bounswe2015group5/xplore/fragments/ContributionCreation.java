package bounswe2015group5.xplore.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.dpizarro.autolabel.library.AutoLabelUI;
import com.dpizarro.autolabel.library.Label;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import bounswe2015group5.xplore.Globals;
import bounswe2015group5.xplore.R;
/**
 * Created by hakansahin on 06/11/15.
 */
public class ContributionCreation extends BaseFragment{

    private EditText titleEditText, contentEditText, tagEditText;
    private Button createBtn, addTagBtn;
    private AutoLabelUI tagLabels;

    public ContributionCreation(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        RelativeLayout parent = (RelativeLayout) inflater.inflate(R.layout.contribution_creation,null);

        titleEditText    = (EditText) parent.findViewById(R.id.conTitle);
        contentEditText  = (EditText) parent.findViewById(R.id.conText);
        tagEditText      = (EditText) parent.findViewById(R.id.conTag);
        tagLabels        = (AutoLabelUI) parent.findViewById(R.id.conTagLabels);

        createBtn = (Button) parent.findViewById(R.id.createBtn);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createContribution();
            }
        });

        addTagBtn = (Button) parent.findViewById(R.id.addTagBtn);
        addTagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tagLabels.addLabel(tagEditText.getText().toString());
                tagEditText.setText("");
                hideKeyboard(view);
            }
        });

        return parent;
    }

    public void createContribution(){

        final String title = titleEditText.getText().toString();
        final String content = contentEditText.getText().toString();

        if(title.isEmpty()||content.isEmpty()){
            Toast.makeText(Globals.appContext, "Please fill in all of the required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        List<String> labelList = new ArrayList<>();
        for(Label label : tagLabels.getLabels())
            labelList.add(label.getText());

        Response.Listener<JSONObject> responseListener =
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response.length() > 0){ // Server replies successfully.

                            Toast.makeText(getActivity().getApplicationContext(), "Contribution is created.", Toast.LENGTH_SHORT).show();

                            // Close the keyboard if it is open.
                            View view = getActivity().getCurrentFocus();
                            if (view != null) hideKeyboard(view);

//                            ((MainActivity) getActivity()).pressTab(R.id.homeTabBtn);

                        } else //unsuccessful contribution creation attempt
                            Toast.makeText(getActivity().getApplicationContext(), "Contribution is not created. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                };

        Globals.connectionManager.createContribution(title, content, Globals.share.getString("username", ""), responseListener);
    }

    public void hideKeyboard(View view){

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
