package com.example.ufox.simplediscgolf;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements
        CourseFragment.OnFragmentInteractionListener,
        StartFragment.OnFragmentInteractionListener,
        PlayerAddDialogFragment.AddNewPlayerListener,
        PlayerViewHolder.SelectedPlayersListener,
        PlayerViewHolder.DeletePlayerListener{

    private final static String TAG = "MainActivity";

    private AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    //Local broadcast for theme change refresh
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String data = intent.getStringExtra("data");
            Log.d(TAG, "onReceive: " + data);

            MainActivity.this.recreate();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        // Register to receive messages.
        // We are registering an observer (mBroadcastReceiver) to receive Intents
        // with actions named "theme-change".
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver,
                new IntentFilter("theme-change"));

        //Set default setting preferences on first run
        PreferenceManager.setDefaultValues(this, R.xml.pref_settings, false);
        //Set correct theme
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("pref_theme", false)) {
            setTheme(R.style.AppTheme_Dark);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_main_toolbar);
        toolbar.inflateMenu(R.menu.appbar);
        setSupportActionBar(toolbar);

        //Create Tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_sections);
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAppSectionsPagerAdapter);

        //Add Tab Titles, title strings @AppSectionsPagerAdapter.tabTitles
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_main_tabs);
        tabLayout.setupWithViewPager(viewPager);




    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void addNewPlayer(PlayerObject player) {
        mAppSectionsPagerAdapter.updatePlayer(player);
    }

    @Override
    public void selectedPlayersChanged(PlayerObject newPlayer) {
        mAppSectionsPagerAdapter.selectedPlayersChanged(newPlayer);
    }

    @Override
    public void deletePlayer(PlayerObject playerObject) {
        mAppSectionsPagerAdapter.deletePlayer(playerObject);
    }

    //Inflate Appbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar,menu);
        return true;
    }

    //appbar action handling
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
        super.onDestroy();
    }
}
