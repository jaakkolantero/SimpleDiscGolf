package com.example.ufox.simplediscgolf;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

/**
 * Created by ufox on 18.2.2017.
 */


//https://guides.codepath.com/android/google-play-style-tabs-using-tablayout

public class AppSectionsPagerAdapter extends FragmentPagerAdapter {

    //TAG
    private final static String TAG ="AppSectionsAdapter";
    //Number of tabs
    final static int PAGE_COUNT = 3;
    //Tab Titles
    private String tabTitles[] = new String[] { "Start", "Players", "Courses" };

    private PlayerFragment mPlayerFragment;
    private StartFragment mStartFragment;
    private CourseFragment mCourseFragment;


    public AppSectionsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return mStartFragment.newInstance();
            case 1:
                return mPlayerFragment.newInstance();
            case 2:
                return mCourseFragment.newInstance();
            default:
                return mStartFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
        switch (position) {
            case 0:
                mStartFragment = (StartFragment) createdFragment;
                break;
            case 1:
                mPlayerFragment = (PlayerFragment) createdFragment;
                break;
            case 2:
                mCourseFragment = (CourseFragment) createdFragment;
                break;
        }
        return createdFragment;
    }

    void updatePlayer (PlayerObject player) {
        if (mPlayerFragment != null) {
            mPlayerFragment.updatePlayer(player);
        }
    }

    void selectedPlayersChanged(PlayerObject newPlayer) {
        if (mStartFragment != null) {
            mStartFragment.selectedPlayersChanged(newPlayer);
        }
    }
}
