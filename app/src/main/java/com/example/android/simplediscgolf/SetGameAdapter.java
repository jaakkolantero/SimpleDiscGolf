package com.example.android.simplediscgolf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//TODO COMMENT

/**
 * Created by Tero on 23.1.2017.
 */
public class SetGameAdapter extends ArrayAdapter<SetGameItem> {
    public SetGameAdapter(Context context, ArrayList<SetGameItem> translations) {
        super(context, 0, translations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SetGameItem setGameItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvPrimary = (TextView) convertView.findViewById(R.id.tv_set_game_primary);
        TextView tvSecondary = (TextView) convertView.findViewById(R.id.tv_set_game_secondary);
        ImageView iwIcon = (ImageView) convertView.findViewById(R.id.iw_set_game_icon);
        // Populate the data into the template view using the data object
        tvPrimary.setText(setGameItem.getPrimaryString());
        tvSecondary.setText(setGameItem.getSecondaryString());
        iwIcon.setImageResource(setGameItem.getIcon());
        // Return the completed view to render on screen
        return convertView;
    }
}