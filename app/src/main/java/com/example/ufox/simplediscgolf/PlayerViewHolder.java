package com.example.ufox.simplediscgolf;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by ufox on 2.3.2017.
 */

public class PlayerViewHolder extends RecyclerView.ViewHolder {

    private View mView;
    private Context mContext;
    protected TextView playerTextView;
    protected TextView mailTextView;
    protected TextView menuTextView;
    protected ImageButton dropdownMenu;
    protected ImageButton editButton;
    protected ImageButton deleteButton;

    private int dropdownIcon = -1;
    private int editIcon = -1;
    private int deleteIcon = -1;

    private SelectedPlayersListener mSelectedPlayersListener;
    private DeletePlayerListener mDeletePlayerListener;

    protected android.support.v4.app.DialogFragment mPlayerAddDialogFragment;

    // Container Activity must implement this interface
    public interface SelectedPlayersListener {
        public void selectedPlayersChanged(PlayerObject playerObject);
    }

    public interface DeletePlayerListener {
        public void deletePlayer(PlayerObject playerObject);
    }


    public PlayerViewHolder(View view) {
        super(view);

        mView = view;
        playerTextView = (TextView) view.findViewById(R.id.tv_player_item_name);
        mailTextView = (TextView) view.findViewById(R.id.tv_player_item_mail);
        menuTextView = (TextView) view.findViewById(R.id.tv_player_item_menu);
        //TODO Add dropdownmenu

    }

    void bind(final Context context, int position, final PlayerObject playerObject) {
        playerTextView.setText(playerObject.getPlayer());
        mailTextView.setText(playerObject.getMail());
        mContext = context;


        if (mContext instanceof PlayerViewHolder.SelectedPlayersListener) {
            mSelectedPlayersListener = (PlayerViewHolder.SelectedPlayersListener) mContext;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SelectedPlayersListener");
        }

        if (mContext instanceof PlayerViewHolder.DeletePlayerListener) {
            mDeletePlayerListener = (PlayerViewHolder.DeletePlayerListener) mContext;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement DeletePlayerListener");
        }

        checkSelected(playerObject,context);

        menuTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creating a popup menu
                PopupMenu popup = new PopupMenu(context,menuTextView);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_player);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit_player:
                                FragmentManager fragmentManager = ((AppCompatActivity) mContext).getSupportFragmentManager();
                                String jsonPlayerObject = new Gson().toJson(playerObject);
                                mPlayerAddDialogFragment = new PlayerAddDialogFragment().newInstance(jsonPlayerObject,context.getResources().getString(R.string.edit_player_dialog_title),context.getResources().getString(R.string.edit_player));
                                mPlayerAddDialogFragment.show(fragmentManager,"EditPlayerFragment");
                                break;
                            case R.id.delete_player:
                                mDeletePlayerListener.deletePlayer(playerObject);
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();
            }
        });

        //Change background color on item select
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playerObject.setSelected(!playerObject.getSelected());
                checkSelected(playerObject,context);

                mSelectedPlayersListener.selectedPlayersChanged(playerObject);

            }
        });
    }




    private int fetchBackgroundColor(Context context) {
        TypedValue typedValue = new TypedValue();
        //TODO Create proper style for item highlight
        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[] { R.attr.colorControlActivated});
        int color = a.getColor(0, 0);

        a.recycle();

        return color;
    }

    private void checkSelected (PlayerObject playerObject, Context context) {
        if (playerObject.getSelected()) {
            mView.setBackgroundColor(fetchBackgroundColor(context));
        } else {
            mView.setBackgroundColor(Color.TRANSPARENT);
        }
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
