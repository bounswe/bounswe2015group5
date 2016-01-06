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
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;

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

        final TextView rateTxt = (TextView) parent.findViewById(R.id.conDetailrate);

        tagLayout = (LinearLayout) parent.findViewById(R.id.detailConTagLayout);

        // TODO gives IOException at runtime.
        contribution = (Contribution) getArguments().getSerializable("Contribution");
        tv_title.setText(contribution.getTitle());
        tv_content.setText(contribution.getContent());
        tv_nameSurname.setText(contribution.getCreatorUsername());
        tv_date.setText(contribution.getDate());
        rateTxt.setText(String.valueOf(contribution.getRate()));

        ImageButton upVoteBtn = (ImageButton) parent.findViewById(R.id.conDetail_up_vote_btn);
        upVoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upDownVote(rateTxt, contribution, 1);
            }
        });

        ImageButton downVoteBtn = (ImageButton) parent.findViewById(R.id.conDetail_down_vote_btn);
        downVoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upDownVote(rateTxt, contribution, -1);
            }
        });

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

    public void addTag(final Tag tag){

        TextView tagView = new TextView(getContext());
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

        tagView.setTextColor(getResources().getColor(R.color.tab_btn_text));
        tagView.setPadding(5,5,5,5);

        tagLayout.addView(tagView);
    }

    public void fetchComments(){

        Response.Listener<JSONArray> responseListener =
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("FEtCHCOMMENTS",response.toString());
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

    public void addComment(Comment comment){

        View commentView = inflater.inflate(R.layout.comment, null);

        TextView tv_content = (TextView) commentView.findViewById(R.id.comment_content);
        tv_content.setText(comment.getContent());

        TextView tv_nameSurname = (TextView) commentView.findViewById(R.id.comment_username);
        tv_nameSurname.setText(comment.getUsername());

        TextView tv_date = (TextView) commentView.findViewById(R.id.comment_date);
        tv_date.setText(comment.getCreatedAt());

        commentView.setPadding(10,5,10,5);
        commentsList.addView(commentView);
    }

    public void postComment(){

//        showProgressDialog();

        final String content = et_enterComment.getText().toString();
        if(content.equals("")) return;

        Response.Listener<String> responseListener =
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("POST_COMMENT", response);
                        if(response.toLowerCase().contains("succes")){

                            Comment comm = new Comment();
                            comm.setContent(content);
                            comm.setUsername(Globals.share.getString("username", ""));
                            addComment(comm);
                        } else
                            Toast.makeText(getActivity(), "Please try again later.", Toast.LENGTH_SHORT).show();

                        et_enterComment.setText("");
//                        hideProgressDialog();
                    }
                };

        // TODO construct an error listener.
        Globals.connectionManager.registerComment(contribution.getId(), content, responseListener);
    }

    public void upDownVote(final TextView rateTxt, final Contribution contribution, final int rate){

        //if(contribution.isRated() == rate) return;    // TODO change the rate functionality. A user, has rated a cont. before, may change its rate.
        if(contribution.isRated() != 0) return;

        Response.Listener<String> responseListener =
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("saved")){
                            contribution.setRated(rate);
                            rateTxt.setText("" + contribution.getRate());
                        }
                    }
                };

        Globals.connectionManager.rateContribution(contribution.getId(), rate, responseListener);
    }
}
