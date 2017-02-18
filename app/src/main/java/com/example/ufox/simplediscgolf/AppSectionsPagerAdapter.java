package com.example.ufox.simplediscgolf;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ufox on 18.2.2017.
 */

public class AppSectionsPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Start", "Players", "Courses" };

    public AppSectionsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return OutlineFragment.newInstance("Main","param2");
            case 1:
                return PlayerFragment.newInstance("Main","param2");
            case 2:
                return CourseFragment.newInstance("Main","param2");
            default:
                return OutlineFragment.newInstance("Main","param2");
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
}
