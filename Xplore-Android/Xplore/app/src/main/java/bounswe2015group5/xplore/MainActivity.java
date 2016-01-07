package bounswe2015group5.xplore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Response;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import bounswe2015group5.xplore.fragments.Home;
import bounswe2015group5.xplore.fragments.Profile;
import bounswe2015group5.xplore.fragments.Trending;

public class MainActivity extends FragmentActivity{

    private boolean signedIn;
    private String fragmentTag;
    private Fragment fragment, currentFragment;
    private FragmentManager fragmentManager;

    private TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Globals.instance == null) Globals.init();

        signedIn = Globals.share.getBoolean("SignedIn",false);

        setContentView(R.layout.activity_main);
        setupTabLayout();

        titleTextView = (TextView) findViewById(R.id.title);

        pressTab(R.id.trendingTabBtn);

        final FloatingActionsMenu fabMenu = (FloatingActionsMenu) findViewById(R.id.fabMenu);

//        FloatingActionButton fabMenuAddTag = (FloatingActionButton) findViewById(R.id.fabMenuAddTag);

        FloatingActionButton fabMenuAddCont = (FloatingActionButton) findViewById(R.id.fabMenuAddCont);
        fabMenuAddCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabMenu.collapse();
                startActivity(new Intent(MainActivity.this, ContributionCreation.class));
//                launchFragment(new ContributionCreation(), "Create Contribution", false);
            }
        });

    }

    public void launchFragment(Fragment fragment, String title, boolean clearAll){

        if(clearAll)
            for(int iterate = fragmentManager.getBackStackEntryCount(); iterate > 1; --iterate)
                fragmentManager.popBackStackImmediate();

        getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment,title)
                        .addToBackStack(title)
                        .commit();

        setTitle(title);
    }

    public void setTitle(String title){
        titleTextView.setText(title);
    }

    public void setupTabLayout(){

        fragmentManager = getSupportFragmentManager();
        currentFragment = fragmentManager.findFragmentById(R.id.content_frame);

        ImageButton homeBtn = (ImageButton) findViewById(R.id.homeTabBtn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTag = "Home";
                if (currentFragment == null || !fragmentTag.equals(currentFragment.getTag()))
                    fragment = new Home();
                else fragment = fragmentManager.findFragmentByTag(fragmentTag);

                launchFragment(fragment, fragmentTag,true);
            }
        });

        ImageButton trendingBtn = (ImageButton) findViewById(R.id.trendingTabBtn);
        trendingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTag = "Trending";
                if (currentFragment == null || !fragmentTag.equals(currentFragment.getTag()))
                    fragment = new Trending();
                else fragment = fragmentManager.findFragmentByTag(fragmentTag);

                launchFragment(fragment, fragmentTag,true);
            }
        });

        ImageButton profileBtn = (ImageButton) findViewById(R.id.profileTabBtn);
        // TODO: arrange main layout for not showing Profile button to unsigned users
        //if(!signedIn){
        //    profileBtn.setVisibility(View.INVISIBLE);
        //}
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTag = "Profile";
                if (currentFragment == null || !fragmentTag.equals(currentFragment.getTag()))
                    fragment = new Profile();
                else fragment = fragmentManager.findFragmentByTag(fragmentTag);

                launchFragment(fragment, fragmentTag,true);
            }
        });

        ImageButton logoutBtn = (ImageButton) findViewById(R.id.logoutTabBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(signedIn){

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            SharedPreferences.Editor editor = Globals.share.edit();
                            editor.putBoolean("SignedIn",false);
                            editor.clear();
                            editor.apply();

                            startActivity(new Intent(MainActivity.this, Login.class));
                            finish();
                        }
                    };

                    Globals.connectionManager.logout(responseListener);

                } else {
                    startActivity(new Intent(MainActivity.this, Login.class));
                    finish();
                }

            }
        });
    }

    public void pressTab(int tabId){
        findViewById(tabId).performClick();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                getSupportFragmentManager().popBackStackImmediate();
            } else {
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

    /**
     * Created by hakansahin on 06/01/16.
     */
    public static class ContributionCreationActivity {}
}
