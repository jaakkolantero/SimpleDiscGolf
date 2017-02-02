package com.example.android.simplediscgolf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class CoursesActivity extends AppCompatActivity {


    private CourseAdapter mCourseAdapter;
    private RecyclerView mCourselist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        mCourselist = (RecyclerView) findViewById(R.id.rv_courses);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mCourselist.setLayoutManager(layoutManager);

        mCourselist.setHasFixedSize(true);

        mCourseAdapter = new CourseAdapter(100);
        mCourselist.setAdapter(mCourseAdapter);
    }
}
