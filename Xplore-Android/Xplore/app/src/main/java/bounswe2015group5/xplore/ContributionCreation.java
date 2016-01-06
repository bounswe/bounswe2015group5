package bounswe2015group5.xplore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.dpizarro.autolabel.library.AutoLabelUI;
import com.dpizarro.autolabel.library.Label;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by hakansahin on 06/01/16.
 */
public class ContributionCreation extends Activity {

    private EditText titleEditText, contentEditText;
    private TextView tagEditText;
    private AutoLabelUI tagLabels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.contribution_creation);

        titleEditText    = (EditText) findViewById(R.id.conTitle);
        contentEditText  = (EditText) findViewById(R.id.conText);
        tagEditText      = (TextView) findViewById(R.id.conTag);
        tagLabels        = (AutoLabelUI) findViewById(R.id.conTagLabels);

        tagEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContributionCreation.this, Search.class));
            }
        });

        TextView contributeBtn = (TextView) findViewById(R.id.contributeBtn);
        contributeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createContribution();
            }
        });

        ImageView backBtn = (ImageView) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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

                            Toast.makeText(Globals.appContext, "Contribution is created.", Toast.LENGTH_SHORT).show();

                            // Close the keyboard if it is open.
                            View view = getCurrentFocus();
                            if (view != null) hideKeyboard(view);

//                            ((MainActivity) getActivity()).pressTab(R.id.homeTabBtn);

                        } else //unsuccessful contribution creation attempt
                            Toast.makeText(Globals.appContext, "Contribution is not created. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                };

        Globals.connectionManager.createContribution(title, content, Globals.share.getString("username", ""), responseListener);
    }

    public void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
