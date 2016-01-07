package bounswe2015group5.xplore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.dpizarro.autolabel.library.AutoLabelUI;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import bounswe2015group5.xplore.models.Contribution;
import bounswe2015group5.xplore.models.Tag;
/**
 * Created by hakansahin on 06/01/16.
 */
public class ContributionCreation extends Activity {

    private EditText titleEditText, contentEditText;
    private TextView tagEditText;
    private AutoLabelUI tagLabels;
    private ArrayList<Tag> tagList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.tagList = new ArrayList<>();

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

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d("ContributionCreation","onNewIntent");
        Bundle b = intent.getExtras();
        if (b != null){
            Tag tag = (Tag) b.getSerializable("tag");

            if(tag != null && !tagList.contains(tag)){
                tagList.add(tag);
                tagLabels.addLabel(tag.getName());
            }
        }
    }

    public void createContribution(){

        final String title = titleEditText.getText().toString();
        final String content = contentEditText.getText().toString();

        if(title.isEmpty()||content.isEmpty()){
            Toast.makeText(Globals.appContext, "Please fill in all of the required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Response.Listener<JSONObject> responseListener =
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response.length() > 0){ // Server replies successfully.

                            Toast.makeText(Globals.appContext, "Contribution is created.", Toast.LENGTH_SHORT).show();

                            Contribution cont = new Contribution(response);
                            // Close the keyboard if it is open.
                            View view = getCurrentFocus();
                            if (view != null) hideKeyboard(view);

                            for(Tag tag : tagList){

                                Response.Listener<JSONArray> responseListener = new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                            // Tag Added.
                                        Log.d("CreateContribution","tag added.");
                                    }
                                };

                                Globals.connectionManager.addTagToContribution(cont.getId(), tag.getID(), responseListener);

                            }

                            finish();

                        } else //unsuccessful contribution creation attempt
                            Toast.makeText(Globals.appContext, "Contribution is not created. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                };

        Globals.connectionManager.createContribution(title, content, responseListener);
    }

    public void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
