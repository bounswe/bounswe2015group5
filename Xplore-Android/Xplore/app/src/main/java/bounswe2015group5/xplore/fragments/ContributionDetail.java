package bounswe2015group5.xplore.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import bounswe2015group5.xplore.R;


/**
 * Created by Mert on 26.11.2015.
 */
public class ContributionDetail extends Fragment {
    private TextView tv_title;
    private TextView tv_content;
    private TextView tv_nameSurname;
    private TextView tv_date;

    String title,content,nameSurname,date;

    public ContributionDetail(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        RelativeLayout parent = (RelativeLayout) inflater.inflate(R.layout.contribution_detail,null);

        tv_title = (TextView) parent.findViewById(R.id.detailConTitle);
        tv_content = (TextView) parent.findViewById(R.id.detailConContent);
        tv_nameSurname = (TextView) parent.findViewById(R.id.detailConNameSurname);
        tv_date = (TextView) parent.findViewById(R.id.detailConDate);

        title = getArguments().get("title").toString();
        content = getArguments().get("content").toString();
        nameSurname = getArguments().get("nameSurname").toString();
        date = getArguments().get("date").toString();

        tv_title.setText(title);
        tv_content.setText(content);
        tv_nameSurname.setText(nameSurname);
        tv_date.setText(date);

        return parent;
    }
}
