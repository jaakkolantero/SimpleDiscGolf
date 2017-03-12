package com.example.ufox.simplediscgolf;

/**
 * Created by ufox on 9.3.2017.
 */

public class CourseObject {

    private String mCourse;
    private int[] mCourseTrackArray;

    public CourseObject() {
        mCourse = "";
        mCourseTrackArray = null;
    }

    public CourseObject(String course, int[] courseTrackArray) {
        mCourse = course;
        mCourseTrackArray = courseTrackArray;
    }

    String getCourse() {
        return mCourse;
    }

    int[] getTrackArray() {
        return mCourseTrackArray;
    }

    void setPlayer(String course) {
        mCourse = course;
    }

    void setMail(int[] courseTrackArray) {
        mCourseTrackArray = courseTrackArray;
    }
}
