package com.example.android.simplediscgolf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity {


    private CourseAdapter mCourseAdapter;
    private RecyclerView mCourselist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        ArrayList<CourseItem> courseArrayList = new ArrayList<CourseItem>();

        courseArrayList.add(new CourseItem("Hoppu",new int[]{3,4,3,4,4,5,3,3,3,4,3,3,3,3,4,3,5,3}));
        courseArrayList.add(new CourseItem("Hoppu2",new int[]{3,4,3,4,4,5,3,3,3,4,3,3,3,3,4,3,5,3}));
        courseArrayList.add(new CourseItem("Hoppu3",new int[]{3,4,3,4,4,5,3,3,3,4,3,3,3,3,4,3,5,3}));
        courseArrayList.add(new CourseItem("Hoppu4",new int[]{3,4,3,4,4,5,3,3,3,4,3,3,3,3,4,3,5,3}));

        mCourselist = (RecyclerView) findViewById(R.id.rv_courses);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mCourselist.setLayoutManager(layoutManager);

        mCourselist.setHasFixedSize(true);

        mCourseAdapter = new CourseAdapter(courseArrayList);
        mCourseAdapter.setEditIcon(R.drawable.ic_mode_edit_black_18dp);
        mCourseAdapter.setDeleteIcon(R.drawable.ic_clear_black_18dp);
        mCourselist.setAdapter(mCourseAdapter);
    }
}
