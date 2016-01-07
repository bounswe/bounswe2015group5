package bounswe2015group5.xplore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import bounswe2015group5.xplore.models.Tag;
/**
 * Created by hakansahin on 06/01/16.
 */
public class Search extends Activity {

    private ArrayList<Tag> tags;
    private SearchResultAdapter adapter;
    private EditText searchView;
    public Tag selectedTag;
    private AVLoadingIndicatorView loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.search);

        selectedTag = null;
        loadingView = (AVLoadingIndicatorView) findViewById(R.id.searchLoadingView);

        searchView = (EditText) findViewById(R.id.searchView);
        searchView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) { }

            @Override
            public void afterTextChanged(Editable arg0) {}
        });

        tags = new ArrayList<>();
        ListView results = (ListView) findViewById(R.id.resultList);
        adapter = new SearchResultAdapter(this, tags, searchView);
        results.setAdapter(adapter);

        getAllTags();

        TextView contributeBtn = (TextView) findViewById(R.id.searchDoneBtn);
        contributeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selectedTag != null){
                    Intent i = new Intent(Search.this, ContributionCreation.class);
                    Bundle b = new Bundle();
                    b.putSerializable("tag", selectedTag);
                    i.putExtras(b);
                    i.setFlags (Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    finish();
                } else {

                    Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject response) {

                            if(response.length() > 0){

                                Tag tag = new Tag(response);

                                Intent i = new Intent(Search.this, ContributionCreation.class);
                                Bundle b = new Bundle();
                                b.putSerializable("tag", tag);
                                i.putExtras(b);
                                i.setFlags (Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                finish();
                            }
                        }
                    };

                    Response.ErrorListener errorListener = new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Globals.appContext, "Tag is not selected. Please try again later.", Toast.LENGTH_SHORT).show();

                            Log.d("SearchError",error.toString());
                            Intent i = new Intent(Search.this, ContributionCreation.class);
                            i.setFlags (Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            finish();
                        }
                    };

                    Globals.connectionManager.createTag(searchView.getText().toString(), searchView.getText().toString(), responseListener, errorListener);
                }
            }
        });

        ImageView backBtn = (ImageView) findViewById(R.id.searchBackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Search.this, ContributionCreation.class);
                i.setFlags (Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });

    }

    public void getAllTags(){

        Response.Listener<JSONArray> responseListener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response.length() > 0){
                    stopAnim();
                    tags.clear();
                    for(int i=0; i<response.length(); i++){
                        tags.add(new Tag(response.optJSONObject(i)));
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        };

        Globals.connectionManager.getAllTags(responseListener);
    }

    public void stopAnim(){
        loadingView.setVisibility(View.GONE);
    }
}

class SearchResultAdapter extends BaseAdapter implements Filterable{
    private Search activity;
    private ArrayList<Tag> tags;
    private ArrayList<Tag> allTags;
    private EditText searchView;

    public SearchResultAdapter(Search activity, ArrayList<Tag> tags, EditText searchView){
        this.tags = tags;
        this.allTags = tags;
        this.activity = activity;
        this.searchView = searchView;
    }

    @Override
    public int getCount() {
        return tags.size();
    }

    @Override
    public Tag getItem(int i) {
        return tags.get(i);
    }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) Globals.appContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.comment, null);
        }

        final Tag tag = getItem(i);

        TextView tagNameView = (TextView) convertView.findViewById(R.id.comment_content);
        tagNameView.setText(tag.getName());

        TextView tagConceptView = (TextView) convertView.findViewById(R.id.comment_username);
        tagConceptView.setText(tag.getConcept());

        (convertView.findViewById(R.id.comment_date)).setVisibility(View.GONE);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setText(tag.getName());
                activity.selectedTag = tag;
            }
        });

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults result = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    result.values = tags;
                    result.count = tags.size();
                }else{
                    ArrayList<Tag> filteredList = new ArrayList<>();
                    for(Tag tag: allTags)
                        if(tag.getName().toLowerCase().contains(constraint.toString().toLowerCase()))
                            filteredList.add(tag);

                    result.values = filteredList;
                    result.count = filteredList.size();
                }

                return result;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.count == 0) {
                    notifyDataSetInvalidated();
                } else {
                    tags = (ArrayList<Tag>) results.values;
                    notifyDataSetChanged();
                }
            }

        };
    }
}
