package bounswe2015group5.xplore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import bounswe2015group5.xplore.adapters.LeftNavAdapter;
import bounswe2015group5.xplore.fragments.ContributionList;

public class MainActivity extends FragmentActivity {

    private DrawerLayout drawerLayout;
    private ListView drawerLeft;

    private boolean signedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signedIn = Globals.share.getBoolean("signedIn",false);

        setContentView(R.layout.activity_main);
        setupDrawer();

    }


    public void launchFragment(Fragment fragment, String title){

        getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment,title)
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
        TextView username = (TextView) header.findViewById(R.id.tvUsername);
        TextView email = (TextView) header.findViewById(R.id.tvEmail);
        TextView edit = (TextView) header.findViewById(R.id.tvEditProfile);

        if(signedIn) {

//            username.setText(Login.share.getString("name", "") + " " + Login.share.getString("surname", ""));
//            email.setText(Login.share.getString("email", ""));

        } else {
            ImageView pp = (ImageView) header.findViewById(R.id.profilePic);

            pp.setImageResource(R.mipmap.ic_launcher);
            username.setVisibility(View.INVISIBLE);
            email.setText("Guest");
            edit.setVisibility(View.INVISIBLE);
        }

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
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (position) {
            case 0:
                return;
            case 1:
                /*TODO Home Page Fragment*/
                return;
            case 2:
                /*TODO Trending Page Fragment*/
                return;
            case 3: //Contributions
                title = "ContributionList";

                Fragment currentFragment = fragmentManager.findFragmentById(R.id.content_frame);
                if (currentFragment == null){
                    fragment = new ContributionList();
                    break;
                }
                else if(!title.equals(currentFragment.getTag())){
                    fragment = fragmentManager.findFragmentByTag("ContributionList");
                    break;
                }
                else
                    return;
            case 4:
                /*TODO About Page Fragment*/
                return;
            case 5: //Log In OR Log Out
                SharedPreferences.Editor editor = Globals.share.edit();
                editor.putBoolean("signedIn",false);
                editor.clear();
                editor.apply();

                startActivity(new Intent(MainActivity.this, Login.class));
                finish();
                return;
        }
        for(int iterate = fragmentManager.getBackStackEntryCount(); iterate > 1; --iterate)
            fragmentManager.popBackStackImmediate();
        launchFragment(fragment, title);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (getSupportFragmentManager().getBackStackEntryCount() > 1)
                getSupportFragmentManager().popBackStackImmediate();
            else {
                if(signedIn)
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Exit")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                else {
                    startActivity(new Intent(MainActivity.this, Login.class));
                    finish();
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
