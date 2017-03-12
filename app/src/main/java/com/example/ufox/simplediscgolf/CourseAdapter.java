package com.example.ufox.simplediscgolf;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by ufox on 9.3.2017.
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseViewHolder> {

    private ArrayList<CourseObject> mCourseArrayList = null;

    private int mDropdownIcon = -1;
    private int mEditIcon = -1;
    private int mDeleteIcon = -1;

    public CourseAdapter(ArrayList<CourseObject> courseArrayList) {
        mCourseArrayList = courseArrayList;

    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layout = R.layout.listitem_course;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean attachToParentImmediately = false;

        View view = inflater.inflate(layout,parent,attachToParentImmediately);
        CourseViewHolder courseViewHolder = new CourseViewHolder(view);
        courseViewHolder.setDropdownIcon(mDropdownIcon);
        courseViewHolder.setEditIcon(mEditIcon);
        courseViewHolder.setDeleteIcon(mDeleteIcon);

        return courseViewHolder;
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        holder.bind(position,mCourseArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCourseArrayList.size();
    }

    public void setDropdownIcon(int resId) {
        mDropdownIcon = resId;
    }

    public void setEditIcon(int resId) {
        mEditIcon = resId;
    }

    public void setDeleteIcon(int resId) {
        mDeleteIcon = resId;
    }
}
