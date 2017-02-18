package com.example.ufox.simplediscgolf;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
        implements
        PlayerFragment.OnFragmentInteractionListener,
        CourseFragment.OnFragmentInteractionListener,
        OutlineFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Create Tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_sections);
        AppSectionsPagerAdapter appSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(appSectionsPagerAdapter);

        //Add Tab Titles
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_main_tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
