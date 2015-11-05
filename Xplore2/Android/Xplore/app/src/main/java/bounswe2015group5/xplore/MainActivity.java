package bounswe2015group5.xplore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import bounswe2015group5.xplore.adapters.LeftNavAdapter;
import bounswe2015group5.xplore.fragments.ContributionList;

public class MainActivity extends FragmentActivity {

    private DrawerLayout drawerLayout;
    private ListView drawerLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setupDrawer();

    }


    public void launchFragment(Fragment fragment, String title){

        getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(title)
                        .commit();
    }

    private void setupDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawers();

        setupLeftNavDrawer();
    }

    private void setupLeftNavDrawer() {
        drawerLeft = (ListView) findViewById(R.id.left_drawer);

        View header = getLayoutInflater().inflate(R.layout.left_navigator_header, null);
        drawerLeft.addHeaderView(header);

        drawerLeft.setAdapter(new LeftNavAdapter(this));
        drawerLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                drawerLayout.closeDrawers();
                onMenuItemClick(position);
            }
        });
        drawerLayout.closeDrawer(drawerLeft);
        onMenuItemClick(3);
    }

    private void onMenuItemClick(int position) {
        Fragment fragment = null;
        String title = null;

        switch (position){
            case 0:
                return;
            case 1:
                /*TODO Home Page Fragment*/
                return;
            case 2:
                /*TODO Trending Page Fragment*/
                return;
            case 3:
                title = "Contribution List";
                fragment = new ContributionList();
                break;
            case 4:
                /*TODO About Page Fragment*/
                return;
            default:
			/*
			 * this codes run when logout button click
			 */
                return;

        }
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        for(int iterate = fragmentManager.getBackStackEntryCount(); iterate > 1; --iterate)
            fragmentManager.popBackStackImmediate();

        launchFragment(fragment, title);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (getSupportFragmentManager().getBackStackEntryCount() > 1)
                getSupportFragmentManager().popBackStackImmediate();

            else new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Exit")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finishAffinity();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
