package bounswe2015group5.xplore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import bounswe2015group5.xplore.R;
import bounswe2015group5.xplore.models.Comment;

/**
 * Created by Mert on 30.11.2015.
 */
public class CommentListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Comment> comments;

    public CommentListAdapter(Context context, ArrayList<Comment> comments){
        this.comments = comments;
        this.context = context;
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
    public long getItemId(int i) { return i; }

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

        TextView tv_nameSurname = (TextView) convertView.findViewById(R.id.comment_username);
        tv_nameSurname.setText(comment.getUsername());

        TextView tv_date = (TextView) convertView.findViewById(R.id.comment_date);
        tv_date.setText(comment.getCreatedAt());

        return convertView;
    }
}
