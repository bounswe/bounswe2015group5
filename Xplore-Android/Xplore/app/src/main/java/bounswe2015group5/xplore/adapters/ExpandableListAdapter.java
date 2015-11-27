package bounswe2015group5.xplore.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import bounswe2015group5.xplore.R;
import bounswe2015group5.xplore.models.Contribution;

/**
 * Created by hakansahin on 24/11/15.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> tagList;
    private HashMap<String, List<Contribution>> contributionList;

    public ExpandableListAdapter(Context context, List<String> tagList, HashMap<String, List<Contribution>> contributionList) {
        this.context = context;
        this.tagList = tagList;
        this.contributionList = contributionList;
    }

    @Override
    public Contribution getChild(int tagPosition, int contributionPosititon) {
        return this.contributionList.get(this.tagList.get(tagPosition)).get(contributionPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int tagPosition, final int contributionPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final Contribution contribution = getChild(tagPosition, contributionPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.home_group_item, null);
        }

        final TextView rateTxt = (TextView) convertView.findViewById(R.id.ext_cont_rate);
        rateTxt.setText("" + contribution.getRate());

        TextView contTitle = (TextView) convertView.findViewById(R.id.ext_cont_title);
        contTitle.setText(contribution.getTitle());

        TextView nameSurname = (TextView) convertView.findViewById(R.id.ext_cont_name_surname);
        nameSurname.setText(contribution.getName() + " " + contribution.getSurname());

        TextView date = (TextView) convertView.findViewById(R.id.ext_cont_date);
        date.setText(contribution.getDate());

        ImageButton upVoteBtn = (ImageButton) convertView.findViewById(R.id.ext_cont_up_vote_btn);
        upVoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upDownVote(rateTxt, contribution, true);
            }
        });

        ImageButton downVoteBtn = (ImageButton) convertView.findViewById(R.id.ext_cont_down_vote_btn);
        downVoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upDownVote(rateTxt, contribution, false);
            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<Contribution> contributions = this.contributionList.get(this.tagList.get(groupPosition));
        return contributions == null ? 0 : contributions.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.tagList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.tagList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int tagPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerTitle = (String) getGroup(tagPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.home_group, null);
        }

        TextView tag = (TextView) convertView.findViewById(R.id.home_group_tag);
        tag.setTypeface(null, Typeface.BOLD);
        tag.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void upDownVote(TextView rateTxt, Contribution contribution, boolean isUpvoted){

        if(contribution.isRated()) return;

        contribution.setRated(true);
        int rate = contribution.getRate() + (isUpvoted ? 1 : -1);

        rateTxt.setText("" + rate);
        contribution.updateRate(rate);
    }
}
