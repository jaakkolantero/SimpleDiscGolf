package com.example.android.simplediscgolf;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ufox on 2.2.2017.
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private static final String TAG = CourseAdapter.class.getSimpleName();

    private int mNumberOfItems;

    public CourseAdapter(int itemCount) {
        mNumberOfItems = itemCount;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layout = R.layout.course_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean attachToParentImmediately = false;

        View view = inflater.inflate(layout,parent,attachToParentImmediately);
        CourseViewHolder courseViewHolder = new CourseViewHolder(view);

        return courseViewHolder;
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        Log.d(TAG, "#: " + position);
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberOfItems;
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {

        protected TextView listItemTextView;

        public CourseViewHolder(View itemView) {
            super(itemView);

            listItemTextView = (TextView) itemView.findViewById(R.id.tw_course_list_name);
        }

        void bind(int listIndex) {
            listItemTextView.setText(String.valueOf(listIndex));
        }

    }
}
