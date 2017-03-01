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
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements
        PlayerFragment.OnFragmentInteractionListener,
        CourseFragment.OnFragmentInteractionListener,
        OutlineFragment.OnFragmentInteractionListener {

    private final static String TAG = "MainActivity";

    //Local broadcast for theme change information
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
        setSupportActionBar(toolbar);

        //Create Tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_sections);
        AppSectionsPagerAdapter appSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(appSectionsPagerAdapter);

        //Add Tab Titles, title strings @AppSectionsPagerAdapter.tabTitles
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_main_tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    //Create actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.appbar,menu);
        return true;
    }

    //actionbar action handling
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
