package bounswe2015group5.xplore.adapters;

/**
 * Last updated by Mert Oguz on 08/11/15.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import bounswe2015group5.xplore.Globals;
import bounswe2015group5.xplore.R;

public class LeftNavAdapter extends BaseAdapter
{
    private ArrayList<String> items = new ArrayList<String>();
    private Context context;

    public LeftNavAdapter(Context context)
    {
        this.context = context;

        items.add("Home");
        items.add("Trending");
        items.add("Contributions");
        items.add("About");
        if(!Globals.share.getBoolean("signedIn",false))
            items.add("Log In");
        else
            items.add("Log Out");
    }

    @Override
    public int getCount()
    {
        return items.size();
    }

    @Override
    public String getItem(int arg0)
    {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.left_navigator_item, null);
        TextView lbl = (TextView) convertView.findViewById(R.id.lbl);
        lbl.setText(getItem(position));

        return convertView;
    }

}

