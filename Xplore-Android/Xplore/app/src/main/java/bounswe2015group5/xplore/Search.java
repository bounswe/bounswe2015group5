package bounswe2015group5.xplore;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONArray;

import java.util.ArrayList;

import bounswe2015group5.xplore.models.Tag;
/**
 * Created by hakansahin on 06/01/16.
 */
public class Search extends Activity {

    private ArrayList<Tag> tags;
    private SearchResultAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.search);

        EditText searchView = (EditText) findViewById(R.id.searchView);
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
        adapter = new SearchResultAdapter(Globals.appContext, tags);
        results.setAdapter(adapter);

        getAllTags();
    }

    public void getAllTags(){

        Response.Listener<JSONArray> responseListener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response.length() > 0){
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
}

class SearchResultAdapter extends BaseAdapter implements Filterable{
    private Context context;
    private ArrayList<Tag> tags;
    private ArrayList<Tag> allTags;

    public SearchResultAdapter(Context context, ArrayList<Tag> tags){
        this.tags = tags;
        this.allTags = tags;
        this.context = context;
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
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.comment, null);
        }

        Tag tag = getItem(i);

        TextView tv_content = (TextView) convertView.findViewById(R.id.comment_content);
        tv_content.setText(tag.getName());

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
