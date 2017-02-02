package com.example.android.simplediscgolf;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
//TODO COMMENT

/**
 * Created by ufox on 30.1.2017.
 */

public class PlayersAdapter extends ArrayAdapter<PlayerItem> {
    public PlayersAdapter(Context context, ArrayList<PlayerItem> translations) {
        super(context, 0, translations);
        this.mContext = context;
        this.mPlayerList = translations;
    }

    private int mEditIcon;

    private int mDeleteIcon;

    private Context mContext;

    private ArrayList<PlayerItem> mPlayerList = new ArrayList<PlayerItem>();

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        PlayerItem playerItem = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.player_item, parent, false);
        }
        // Lookup view for data population
        TextView tvPlayer = (TextView) convertView.findViewById(R.id.tv_player_item_name);
        TextView tvMail = (TextView) convertView.findViewById(R.id.tv_player_item_mail);
        ImageButton ibEdit = (ImageButton) convertView.findViewById(R.id.iw_player_item_edit);
        ImageView ibDelete = (ImageButton) convertView.findViewById(R.id.iw_player_item_delete);
        // Populate the data into the template view using the data object
        tvPlayer.setText(playerItem.getPlayer());
        tvMail.setText(playerItem.getMail());
        ibEdit.setImageResource(mEditIcon);
        ibDelete.setImageResource(mDeleteIcon);

        ibEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.i("Edit Button Clicked", "**********");
                openEditPlayerDialog(position);
            }
        });

        ibDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Delete Button Clicked", "**********");
                deletePlayer(position);
            }
        });



        // Return the completed view to render on screen
        return convertView;
    }

    public void setEditIcon(int resId) {
        mEditIcon = resId;
    }

    public void setDeleteIcon(int resId) {
        mDeleteIcon = resId;
    }

    private void openEditPlayerDialog(int position) {

        //TODO Create custom dialog and use name_edit_dialog.xml

        final PlayerItem item = getItem(position);
        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
        alert.setTitle("Edit Player"); //Set Alert dialog title here

        LinearLayout layout = new LinearLayout(mContext);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText nameEditText = new EditText(mContext);;
        nameEditText.setText((String) item.getPlayer());
        layout.addView(nameEditText);

        final EditText mailEditText = new EditText(mContext);
        mailEditText.setText((String) item.getMail());
        layout.addView(mailEditText);

        alert.setView(layout);
        
        alert.setPositiveButton("EDIT", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String newName = nameEditText.getEditableText().toString();
                String newMail = mailEditText.getEditableText().toString();
                Toast.makeText(mContext, newName, Toast.LENGTH_LONG).show();
                item.setPlayer(newName);
                item.setMail(newMail);
            }
        });
        alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        }); //End of alert.setNegativeButton
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    private void deletePlayer(final int position) {

        //TODO Create custom dialog and use delete_dialog.xml

        final PlayerItem item = getItem(position);
        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
        alert.setTitle("Delete Player"); //Set Alert dialog title here
        alert.setMessage("Are you sure you want to delete " + item.getPlayer() + "?");


        alert.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(mContext, "Player " + item.getPlayer() + "Deleted.", Toast.LENGTH_LONG).show();
                mPlayerList.remove(position);
                notifyDataSetChanged();
            }
        });

        alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        }); //End of alert.setNegativeButton

        AlertDialog alertDialog = alert.create();
        alertDialog.show();

    }
}