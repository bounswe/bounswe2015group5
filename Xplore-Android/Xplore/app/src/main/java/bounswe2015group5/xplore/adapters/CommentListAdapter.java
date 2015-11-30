package bounswe2015group5.xplore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bounswe2015group5.xplore.R;
import bounswe2015group5.xplore.models.Comment;

/**
 * Created by Mert on 30.11.2015.
 */
public class CommentListAdapter extends ArrayAdapter<Comment> {
    private Context context;
    private ArrayList<Comment> comments;
    private int layoutResourceId;

    public CommentListAdapter(Context context, int layoutResourceId, ArrayList<Comment> comments){
        super(context, layoutResourceId, comments);
        this.comments = comments;
        this.context = context;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Comment getItem(int i) {
        return comments.get(i);
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.comment, null);
        }

        Comment comment = getItem(i);

        TextView tv_content = (TextView) convertView.findViewById(R.id.comment_content);
        tv_content.setText(comment.getContent());

        TextView tv_nameSurname = (TextView) convertView.findViewById(R.id.comment_name_surname);
        tv_nameSurname.setText(comment.getName()+" "+comment.getSurname());

        TextView tv_date = (TextView) convertView.findViewById(R.id.comment_date);
        tv_date.setText(comment.getDate());

        return convertView;
    }
}
