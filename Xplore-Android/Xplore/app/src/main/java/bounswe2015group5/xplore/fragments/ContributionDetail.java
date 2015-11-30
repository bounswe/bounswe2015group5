package bounswe2015group5.xplore.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bounswe2015group5.xplore.Globals;
import bounswe2015group5.xplore.R;
import bounswe2015group5.xplore.adapters.CommentListAdapter;
import bounswe2015group5.xplore.models.Comment;
import bounswe2015group5.xplore.models.Contribution;


/**
 * Created by Mert on 26.11.2015.
 */
public class ContributionDetail extends Fragment {
    private TextView tv_title;
    private TextView tv_content;
    private TextView tv_nameSurname;
    private TextView tv_date;
    private TextView tv_tags;
    private ListView commentsList;
    private EditText et_enterComment;
    private Button commentBtn;
    private ProgressDialog pDialog;

    private String id;
    private Contribution contribution;
    private ArrayList<Comment> comments;
    private ListAdapter commentsListAdapter;

    public ContributionDetail(){
        contribution = new Contribution();
        comments = new ArrayList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        RelativeLayout parent = (RelativeLayout) inflater.inflate(R.layout.contribution_detail,null);

        tv_title = (TextView) parent.findViewById(R.id.detailConTitle);
        tv_content = (TextView) parent.findViewById(R.id.detailConContent);
        tv_nameSurname = (TextView) parent.findViewById(R.id.detailConNameSurname);
        tv_date = (TextView) parent.findViewById(R.id.detailConDate);
        tv_tags = (TextView) parent.findViewById(R.id.detailConTags);
        commentsList = (ListView) parent.findViewById(R.id.detailConCommentList);
        et_enterComment = (EditText) parent.findViewById(R.id.detailConComment);
        commentBtn = (Button) parent.findViewById(R.id.detailConCommentBtn);

        contribution = (Contribution) getArguments().getSerializable("Contribution");
        String tags = "Tags:  ";
        tv_title.setText(contribution.getTitle());
        tv_content.setText(contribution.getContent());
        tv_nameSurname.setText(contribution.getName() + " " + contribution.getSurname());
        tv_date.setText(contribution.getDate());
        for(int i = 0; i < contribution.getTags().size(); i++){
            tags+= (contribution.getTags().get(i).getName() + " ");
        }
        tv_tags.setText(tags);
        comments = new ArrayList<Comment>();
        //comments is somehow received as null.
        //comments = (ArrayList<Comment>)getArguments().getSerializable("Comments");
        commentsListAdapter = new CommentListAdapter(getActivity().getApplicationContext(),R.layout.comment,comments);
        commentsList.setAdapter(commentsListAdapter);

        return parent;
    }

    private void showProgressDialog(){
        if(pDialog == null)
            pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please wait..");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void hideProgressDialog(){
        if(pDialog != null && pDialog.isShowing())
            pDialog.dismiss();
    }
}
