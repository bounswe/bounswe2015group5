package bounswe2015group5.xplore.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bounswe2015group5.xplore.Globals;
import bounswe2015group5.xplore.MainActivity;
import bounswe2015group5.xplore.R;
import bounswe2015group5.xplore.models.Comment;
import bounswe2015group5.xplore.models.Contribution;
import bounswe2015group5.xplore.models.Tag;


/**
 * Created by Mert on 26.11.2015.
 */
public class ContributionDetail extends BaseFragment {
    private LinearLayout commentsList, tagLayout;
    private EditText et_enterComment;
    private TextView rateTxt;
    private ImageButton upVoteBtn, downVoteBtn;
    private Button commentBtn;

    private Contribution contribution;
    private LayoutInflater inflater;

    public ContributionDetail(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.inflater = inflater;
        ScrollView parent = (ScrollView) inflater.inflate(R.layout.contribution_detail, null);

        TextView
        tv_title = (TextView) parent.findViewById(R.id.detailConTitle),
        tv_content = (TextView) parent.findViewById(R.id.detailConContent),
        tv_nameSurname = (TextView) parent.findViewById(R.id.detailConNameSurname),
        tv_date = (TextView) parent.findViewById(R.id.detailConDate);

        commentsList = (LinearLayout) parent.findViewById(R.id.detailConCommentList);
        et_enterComment = (EditText) parent.findViewById(R.id.detailConComment);
        commentBtn = (Button) parent.findViewById(R.id.detailConCommentBtn);
        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postComment();
            }
        });

        rateTxt = (TextView) parent.findViewById(R.id.conDetailrate);

        tagLayout = (LinearLayout) parent.findViewById(R.id.detailConTagLayout);

        // TODO gives IOException at runtime.
        contribution = (Contribution) getArguments().getSerializable("Contribution");
        tv_title.setText(contribution.getTitle());
        tv_content.setText(contribution.getContent());
        tv_nameSurname.setText(contribution.getCreatorUsername());
        tv_date.setText(contribution.getDate());

        upVoteBtn = (ImageButton) parent.findViewById(R.id.conDetail_up_vote_btn);
        downVoteBtn = (ImageButton) parent.findViewById(R.id.conDetail_down_vote_btn);

        if(contribution.getCreatorUsername().equals(Globals.share.getString("username",""))){

            ImageView deleteBtn = (ImageView) parent.findViewById(R.id.contDeleteBtn);
            deleteBtn.setVisibility(View.VISIBLE);

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Response.Listener<String> responseListener = new Response.Listener<String>(){
                        @Override
                        public void onResponse(String response) {
                            Log.d("Delete Contribution",response);
                            Toast.makeText(Globals.appContext, "Contribution is deleted.", Toast.LENGTH_SHORT).show();
                            ((MainActivity) getActivity()).onBackPressed();
                        }
                    };
                    Globals.connectionManager.deleteContribution(contribution.getId(), responseListener);
                }
            });
        }

        // TODO
        //fetchRateOfContribution();
        fetchTags();
        fetchComments();
        return parent;
    }

    public void fetchTags(){

        Response.Listener<JSONArray> responseListener =
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length() > 0){
                            for(int i = 0; i < response.length(); i++)
                                addTag(new Tag(response.optJSONObject(i)));
                        }
                        else System.err.println("response is empty");
                    }
                };

        // TODO construct an error listener.
        Globals.connectionManager.getTagsByContributionId(contribution.getId(), responseListener);
    }

    private void fetchRateOfContribution(){

        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(response.length() > 0){

                    contribution.rate = response.optInt("up",0) - response.optInt("down",0);
                    contribution.clientRate = response.optInt("currentUser",0);

                    rateTxt.setText(String.valueOf(contribution.getRate()));

                    upVoteBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            upDownVote(contribution, 1);
                        }
                    });

                    downVoteBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            upDownVote(contribution, -1);
                        }
                    });

                }
            }
        };

        Globals.connectionManager.getRateByContributionId(contribution.getId(), responseListener);
    }

    public void addTag(final Tag tag){

        TextView tagView = new TextView(Globals.appContext);
        tagView.setText(tag.getName());
        tagView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new TagDetail();
                Bundle args = new Bundle();
                args.putInt("TAGID",tag.getID());
                args.putString("TAGNAME",tag.getName());
                fragment.setArguments(args);

                ((MainActivity) getActivity()).launchFragment(fragment, "#" + tag.getName(), false);
            }
        });

        tagView.setTextColor(Globals.appContext.getResources().getColor(R.color.tab_btn_text));
        tagView.setPadding(5,5,5,5);

        tagLayout.addView(tagView);
    }

    public void fetchComments(){

        Response.Listener<JSONArray> responseListener =
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("FETCHCOMMENTS",response.toString());
                        if(response.length() > 0){
                            try {
                                for(int i = 0; i < response.length(); i++){
                                    Comment comment = new Comment(response.getJSONObject(i));
                                    addComment(comment);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else{
                            System.err.println("response is empty");
                        }
                    }
                };

        // TODO construct an error listener.
        Globals.connectionManager.getCommentsByContributionId(contribution.getId(), responseListener);
    }

    public void addComment(final Comment comment){

        final View commentView = inflater.inflate(R.layout.comment, null);

        TextView tv_content = (TextView) commentView.findViewById(R.id.comment_content);
        tv_content.setText(comment.getContent());

        TextView tv_nameSurname = (TextView) commentView.findViewById(R.id.comment_username);
        tv_nameSurname.setText(comment.getUsername());

        TextView tv_date = (TextView) commentView.findViewById(R.id.comment_date);
        tv_date.setText(comment.getCreatedAt());

        if(comment.getUsername().equals(Globals.share.getString("username", ""))){

            ImageView deleteBtn = (ImageView) commentView.findViewById(R.id.commDeleteBtn);
            deleteBtn.setVisibility(View.VISIBLE);

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {

                    Response.Listener<String> responsListener = new Response.Listener<String>(){
                        @Override
                        public void onResponse(String response) {

                            Log.d("Delete Comment",response);

                            commentsList.removeView(commentView);
                            Toast.makeText(Globals.appContext, "Comment is deleted.", Toast.LENGTH_SHORT).show();

                        }
                    };

                    Globals.connectionManager.deleteComment(comment.getID(), responsListener);
                }
            });
        }

        commentView.setPadding(10,5,10,5);
        commentsList.addView(commentView);
    }

    public void postComment(){

//        showProgressDialog();

        final String content = et_enterComment.getText().toString();
        if(content.equals("")) return;

        final String username = Globals.share.getString("username", "");

        Response.Listener<JSONArray> responseListener =
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("POST_COMMENT", response.toString());
                        if(response.length() > 0){
                            Comment comm = new Comment(response.optJSONObject(0));
                            addComment(comm);
                        } else
                            Toast.makeText(getActivity(), "Please try again later.", Toast.LENGTH_SHORT).show();

                        et_enterComment.setText("");
//                        hideProgressDialog();
                    }
                };

        // TODO construct an error listener.
        Globals.connectionManager.postComment(contribution.getId(), content, username, responseListener);
    }

    public void upDownVote(final Contribution contribution, final int rate){

        if(contribution.isRated() == rate) return;    // TODO change the rate functionality. A user, has rated a cont. before, may change its rate.
//        if(contribution.isRated() != 0) return;

        Response.Listener<JSONObject> responseListener =
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                            contribution.setRated(rate);
                            rateTxt.setText(String.valueOf(contribution.getRate()));
                    }
                };

        Globals.connectionManager.rateContribution(contribution.getId(), rate, responseListener);
    }
}
