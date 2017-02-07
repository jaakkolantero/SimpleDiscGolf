package com.example.android.simplediscgolf;

/**
 * Created by ufox on 4.2.2017.
 */

public class CourseItem {

    private String mCourseName;
    private int[] mCourseTracks;

    public CourseItem() {
        mCourseName = "";
        mCourseTracks =  new int[]{0};
    }

    public CourseItem(String courseName, int[] courseTracks) {
        mCourseName = courseName;
        mCourseTracks = courseTracks;
    }

    void setCourseName(String courseName) {
        mCourseName = courseName;
    }

    String getCourseName() {
        return mCourseName;
    }

    void setCourseTracks(int[] courseTracks) {
        mCourseTracks = courseTracks;
    }

    int[] getCourseTracks() {
        return mCourseTracks;
    }
}
