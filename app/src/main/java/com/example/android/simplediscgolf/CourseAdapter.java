package com.example.android.simplediscgolf;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by ufox on 2.2.2017.
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseViewHolder> {

    public CourseAdapter(ArrayList<CourseItem> courseItemArrayList) {
        mCourseItemArrayList = courseItemArrayList;
        mNumberOfItems = mCourseItemArrayList.size();
    }

    private static final String TAG = CourseAdapter.class.getSimpleName();

    private int mNumberOfItems;

    private int mEditIcon;
    private int mDeleteIcon;

    private ArrayList<CourseItem> mCourseItemArrayList;


    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layout = R.layout.course_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean attachToParentImmediately = false;

        View view = inflater.inflate(layout,parent,attachToParentImmediately);
        CourseViewHolder courseViewHolder = new CourseViewHolder(view);
        courseViewHolder.setEditIcon(mEditIcon);
        courseViewHolder.setDeleteIcon(mDeleteIcon);

        return courseViewHolder;
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        Log.d(TAG, "#: " + position);
        holder.bind(position, mCourseItemArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return mNumberOfItems;
    }

    public void setEditIcon(int resId) {
        mEditIcon = resId;
    }

    public void setDeleteIcon(int resId) {
        mDeleteIcon = resId;
    }

}
