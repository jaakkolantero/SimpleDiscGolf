package com.example.ufox.simplediscgolf;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by ufox on 9.3.2017.
 */

public class CourseViewHolder extends RecyclerView.ViewHolder{

    protected TextView courseTextView;
    protected TextView trackTextView;
    protected ImageButton dropdownMenu;
    protected ImageButton editButton;
    protected ImageButton deleteButton;

    private int dropdownIcon = -1;
    private int editIcon = -1;
    private int deleteIcon = -1;

    public CourseViewHolder(View view) {
        super(view);

        courseTextView = (TextView) view.findViewById(R.id.tv_course_item_name);
        //TODO Add dropdownmenu

    }

    void bind(int listIndex, CourseObject courseObject) {
        courseTextView.setText(courseObject.getCourse());
    }

    void setDropdownIcon(int resId) {
        dropdownIcon = resId;
    }
    void setEditIcon(int resId) {
        editIcon = resId;
    }
    void setDeleteIcon(int resId) {
        deleteIcon = resId;
    }
}
