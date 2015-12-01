package bounswe2015group5.xplore.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bounswe2015group5.xplore.Globals;
import bounswe2015group5.xplore.R;
import bounswe2015group5.xplore.models.Comment;
import bounswe2015group5.xplore.models.Contribution;


/**
 * Created by Mert on 26.11.2015.
 */
public class ContributionDetail extends BaseFragment {
    private LinearLayout commentsList;
    private EditText et_enterComment;
    private Button commentBtn;
    private ProgressDialog pDialog;

    private String id;
    private Contribution contribution;
    private ArrayList<Comment> comments;
    private LayoutInflater inflater;

    public ContributionDetail(){
        contribution = new Contribution();
        comments = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.inflater = inflater;
        ScrollView parent = (ScrollView) inflater.inflate(R.layout.contribution_detail, null);

        TextView
        tv_title = (TextView) parent.findViewById(R.id.detailConTitle),
        tv_content = (TextView) parent.findViewById(R.id.detailConContent),
        tv_nameSurname = (TextView) parent.findViewById(R.id.detailConNameSurname),
        tv_date = (TextView) parent.findViewById(R.id.detailConDate),
        tv_tags = (TextView) parent.findViewById(R.id.detailConTags);

        commentsList = (LinearLayout) parent.findViewById(R.id.detailConCommentList);
        et_enterComment = (EditText) parent.findViewById(R.id.detailConComment);
        commentBtn = (Button) parent.findViewById(R.id.detailConCommentBtn);
        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postComment();
            }
        });

        // TODO gives IOException at runtime.
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

        comments = new ArrayList<>();
        comments = (ArrayList<Comment>) getArguments().getSerializable("Comments");
        populateComments();
        return parent;
    }

    public void populateComments(){

        for(Comment comment : comments)
            addComment(comment);
    }

    public void addComment(Comment comment){

        View commentView = inflater.inflate(R.layout.comment, null);

        TextView tv_content = (TextView) commentView.findViewById(R.id.comment_content);
        tv_content.setText(comment.getContent());

        TextView tv_nameSurname = (TextView) commentView.findViewById(R.id.comment_name_surname);
        tv_nameSurname.setText(comment.getName()+" "+comment.getSurname());

        TextView tv_date = (TextView) commentView.findViewById(R.id.comment_date);
        tv_date.setText(comment.getDate());

        commentView.setPadding(10,5,10,5);
        commentsList.addView(commentView);
    }

    public void postComment(){

        showProgressDialog();

        final String content = et_enterComment.getText().toString();
        if(content.equals("")) return;


        Map<String, String> mParams = new HashMap<>();
        mParams.put("ContributionID", ""+contribution.getId());
        mParams.put("Content", content);
        final JSONObject postBody = new JSONObject(mParams);

        final String URL = getString(R.string.service_url) + "RegisterComment"; //for POST to server
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST,URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("POST_COMMENT", response);
                        if(response.toLowerCase().contains("succes")){

                            Comment comm = new Comment();
                            comm.setContent(content);
                            comm.setName(Globals.share.getString("Name", ""));
                            comm.setSurname(Globals.share.getString("Surname",""));
                            addComment(comm);
                        } else
                            Toast.makeText(getActivity(), "Please try again later.", Toast.LENGTH_SHORT).show();

                        et_enterComment.setText("");
                        hideProgressDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("LOG_ERROR", error.toString());
                hideProgressDialog();
                // TODO if the request fails, show a warning.
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

        Globals.mRequestQueue.add(jsonObjectRequest);
    }
}
