package bounswe2015group5.xplore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import bounswe2015group5.xplore.R;
import bounswe2015group5.xplore.models.Contribution;
/**
 * Created by hakansahin on 05/11/15.
 */
public class ContributionListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Contribution> contributions;

    public ContributionListAdapter(Context context, ArrayList<Contribution> contributions){
        this.context = context;
        this.contributions = contributions;
    }

    @Override
    public int getCount() {
        return contributions.size();
    }

    @Override
    public Contribution getItem(int i) {
        return contributions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.contribution, null);
        }

        Contribution contribution = getItem(i);

        final TextView rateTxt = (TextView) convertView.findViewById(R.id.rate);
        rateTxt.setText("" + contribution.getRate());

        TextView title = (TextView) convertView.findViewById(R.id.cont_detail);
        title.setText(contribution.getTitle());

        TextView nameSurname = (TextView) convertView.findViewById(R.id.cont_name_surname);
        nameSurname.setText(contribution.getName() + " " + contribution.getSurname());

        TextView date = (TextView) convertView.findViewById(R.id.cont_date);
        date.setText(contribution.getDate());

        ImageButton upVoteBtn = (ImageButton) convertView.findViewById(R.id.up_vote_btn);
        upVoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upDownVote(rateTxt, i, true);
            }
        });

        ImageButton downVoteBtn = (ImageButton) convertView.findViewById(R.id.down_vote_btn);
        downVoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upDownVote(rateTxt, i, false);
            }
        });

        return convertView;
    }

    public void upDownVote(TextView rateTxt, int i, boolean isUpvoted){
        Contribution contribution = getItem(i);

        if(contribution.isRated()) return;

        contribution.setRated(true);
        int rate = contribution.getRate() + (isUpvoted ? 1 : -1);

        rateTxt.setText("" + rate);
        contribution.updateRate(rate);
    }
}
