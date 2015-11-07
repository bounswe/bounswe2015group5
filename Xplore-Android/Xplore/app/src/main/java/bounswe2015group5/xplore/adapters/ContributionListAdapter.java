package bounswe2015group5.xplore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
    public Object getItem(int i) {
        return contributions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.contribution, null);
        }
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.contText);
        lblListHeader.setText(contributions.get(i).getTitle());

        return convertView;
    }
}
