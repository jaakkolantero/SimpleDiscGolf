package com.example.ufox.simplediscgolf;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ufox on 2.3.2017.
 */

public class PlayerViewHolder extends RecyclerView.ViewHolder {

    private View mView;
    protected TextView playerTextView;
    protected TextView mailTextView;
    protected ImageButton dropdownMenu;
    protected ImageButton editButton;
    protected ImageButton deleteButton;

    private int dropdownIcon = -1;
    private int editIcon = -1;
    private int deleteIcon = -1;

    private SelectedPlayersListener mSelectedPlayersListener;

    // Container Activity must implement this interface
    public interface SelectedPlayersListener {
        public void selectedPlayersChanged(PlayerObject playerObject);
    }


    public PlayerViewHolder(View view) {
        super(view);

        mView = view;
        playerTextView = (TextView) view.findViewById(R.id.tv_player_item_name);
        mailTextView = (TextView) view.findViewById(R.id.tv_player_item_mail);
        //TODO Add dropdownmenu

    }

    void bind(final Context context, int position, final PlayerObject playerObject) {
        playerTextView.setText(playerObject.getPlayer());
        mailTextView.setText(playerObject.getMail());

        if (context instanceof PlayerViewHolder.SelectedPlayersListener) {
            mSelectedPlayersListener = (PlayerViewHolder.SelectedPlayersListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SelectedPlayersListener");
        }

        //Change background color on item select
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                playerObject.setSelected(!playerObject.getSelected());
                if (playerObject.getSelected()) {
                    mView.setBackgroundColor(fetchBackgroundColor(context));
                } else {
                    mView.setBackgroundColor(Color.TRANSPARENT);
                }
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
