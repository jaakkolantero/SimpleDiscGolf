package com.example.ufox.simplediscgolf;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by ufox on 9.3.2017.
 */

public class PlayerAdapter extends RecyclerView.Adapter<PlayerViewHolder> {

    private final String TAG = "PlayerAdapter";

    private ArrayList<PlayerObject> mPlayerArrayList = null;

    private int mDropdownIcon = -1;
    private int mEditIcon = -1;
    private int mDeleteIcon = -1;

    private Context mContext;

    public PlayerAdapter(ArrayList<PlayerObject> playerArrayList) {
        mPlayerArrayList = playerArrayList;

    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        int layout = R.layout.listitem_player;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        boolean attachToParentImmediately = false;

        View view = inflater.inflate(layout,parent,attachToParentImmediately);
        PlayerViewHolder playerViewHolder = new PlayerViewHolder(view);
        playerViewHolder.setDropdownIcon(mDropdownIcon);
        playerViewHolder.setEditIcon(mEditIcon);
        playerViewHolder.setDeleteIcon(mDeleteIcon);

        return playerViewHolder;
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {

        holder.bind(mContext, position,mPlayerArrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return mPlayerArrayList.size();
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
