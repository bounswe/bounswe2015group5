package bounswe2015group5.xplore.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import bounswe2015group5.xplore.R;
import bounswe2015group5.xplore.adapters.CommentListAdapter;
import bounswe2015group5.xplore.models.Comment;
import bounswe2015group5.xplore.models.Contribution;


/**
 * Created by Mert on 26.11.2015.
 */
public class ContributionDetail extends BaseFragment {
    private TextView tv_title, tv_content, tv_nameSurname, tv_date, tv_tags;
    private ListView commentsList;
    private EditText et_enterComment;
    private Button commentBtn;
    private ProgressDialog pDialog;

    private String id;
    private Contribution contribution;
    private ArrayList<Comment> comments;

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
        comments = (ArrayList<Comment>) getArguments().getSerializable("Comments");
        CommentListAdapter commentsListAdapter = new CommentListAdapter(getActivity().getApplicationContext(),comments);
        commentsList.setAdapter(commentsListAdapter);

        return parent;
    }
}
