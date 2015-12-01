package bounswe2015group5.xplore.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dpizarro.autolabel.library.AutoLabelUI;

import java.util.HashMap;
import java.util.Map;

import bounswe2015group5.xplore.Globals;
import bounswe2015group5.xplore.MainActivity;
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

        final String URL = getString(R.string.service_url) + "RegisterContribution"; //for POST to server

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("LOG", response.toString());
                        if(response.toLowerCase().contains("succes")){ // Server replies successfully.

                            Toast.makeText(getActivity().getApplicationContext(), "Contribution is created.", Toast.LENGTH_SHORT).show();

                            // Close the keyboard if it is open.
                            View view = getActivity().getCurrentFocus();
                            if (view != null) hideKeyboard(view);

                            ((MainActivity) getActivity()).onMenuItemClick(3);

                        } else //unsuccessful contribution creation attempt
                            Toast.makeText(getActivity().getApplicationContext(), "Contribution is not created. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("LOG", error.toString());
            }
        }){
            /**
             * Puts arguments for POST that will be sent to the server
             * @auth Hakan Sahin
             * @throws AuthFailureError
             */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> mParams = new HashMap<String, String>();
                mParams.put("title", title);
                mParams.put("content", content);
                mParams.put("type", "1");     // type is set to 1 statically until we create content types.
                return mParams;
            }
        };

        Globals.mRequestQueue.add(stringRequest);
    }

    public void hideKeyboard(View view){

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
