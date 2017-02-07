package com.example.android.simplediscgolf;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ufox on 4.2.2017.
 */

class CourseViewHolder extends RecyclerView.ViewHolder {

    protected TextView courseNameTextView;
    protected TextView secondaryTextView;
    protected ImageButton editImageButton;
    protected ImageButton deleteImageButton;

    private int mEditIcon;
    private int mDeleteIcon;


    public CourseViewHolder(View itemView) {
        super(itemView);

        courseNameTextView = (TextView) itemView.findViewById(R.id.tv_course_item_name);
        secondaryTextView = (TextView) itemView.findViewById(R.id.tv_course_item_secondary);
        editImageButton = (ImageButton) itemView.findViewById(R.id.iw_course_item_edit);
        deleteImageButton = (ImageButton) itemView.findViewById(R.id.iw_course_item_delete);
    }


    void bind(int listIndex, CourseItem courseItem) {
        String courseInfo = "";
        for (int track : courseItem.getCourseTracks() ) {
            courseInfo = courseInfo.concat(String.valueOf(track) + " ");
        }

        courseNameTextView.setText(courseItem.getCourseName());
        secondaryTextView.setText(courseInfo);
        editImageButton.setImageResource(mEditIcon);
        deleteImageButton.setImageResource(mDeleteIcon);
    }

    public void setEditIcon(int resId) {
        mEditIcon = resId;
    }

    public void setDeleteIcon(int resId) {
        mDeleteIcon = resId;
    }


}