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
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.dpizarro.autolabel.library.AutoLabelUI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
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

        final Map<String, String> mParams = new HashMap<>();
        mParams.put("Title", title);
        mParams.put("Content", content);
        final JSONObject postBody = new JSONObject(mParams);
        try {
            postBody.put("Tags", new JSONArray());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String URL = getString(R.string.service_url) + "RegisterContribution"; //for POST to server

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("LOG", response);
                        if(response.toLowerCase().contains("saved")){ // Server replies successfully.

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

            private final String PROTOCOL_CHARSET = "utf-8";
            private final String PROTOCOL_CONTENT_TYPE =
                    String.format("application/json; charset=%s", PROTOCOL_CHARSET);
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return postBody.toString().getBytes(PROTOCOL_CHARSET);
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", postBody, PROTOCOL_CHARSET);
                    return null;
                }
            }

            @Override
            public String getBodyContentType() {
                return postBody.toString() == null ? null : PROTOCOL_CONTENT_TYPE;
            }
        };
        Globals.mRequestQueue.add(stringRequest);
    }

    public void hideKeyboard(View view){

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
