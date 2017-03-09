package com.example.ufox.simplediscgolf;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by ufox on 2.3.2017.
 */

public class PlayerViewHolder extends RecyclerView.ViewHolder {

    protected TextView playerTextView;
    protected TextView mailTextView;
    protected ImageButton dropdownMenu;
    protected ImageButton editButton;
    protected ImageButton deleteButton;

    private int dropdownIcon = -1;
    private int editIcon = -1;
    private int deleteIcon = -1;

    public PlayerViewHolder(View view) {
        super(view);

        playerTextView = (TextView) view.findViewById(R.id.tv_player_item_name);
        mailTextView = (TextView) view.findViewById(R.id.tv_player_item_mail);
        //TODO Add dropdownmenu

    }

    void bind(int listIndex, PlayerObject playerObject) {
        playerTextView.setText(playerObject.getPlayer());
        mailTextView.setText(playerObject.getMail());
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
