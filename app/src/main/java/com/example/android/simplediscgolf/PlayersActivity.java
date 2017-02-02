package com.example.android.simplediscgolf;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlayersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        final ArrayList<PlayerItem> playersArray = new ArrayList<PlayerItem>();

        //Populate SetGameList
        //TODO Strings to strings.xml
        playersArray.add(new PlayerItem("Tero", "tero.jaakkola1@gmail.com"));
        playersArray.add(new PlayerItem("Player1", "player@mail.com"));
        playersArray.add(new PlayerItem("Player2", "player@mail.com"));
        playersArray.add(new PlayerItem("Player3", "player@mail.com"));
        playersArray.add(new PlayerItem("Player4", "player@mail.com"));
        playersArray.add(new PlayerItem("Player5", "player@mail.com"));
        playersArray.add(new PlayerItem("Player6", "player@mail.com"));
        playersArray.add(new PlayerItem("Player7", "player@mail.com"));
        playersArray.add(new PlayerItem("Player8", "player@mail.com"));
        playersArray.add(new PlayerItem("Player9", "player@mail.com"));
        playersArray.add(new PlayerItem("Player10", "player@mail.com"));
        playersArray.add(new PlayerItem("Player11", "player@mail.com"));
        playersArray.add(new PlayerItem("Player12", "player@mail.com"));
        playersArray.add(new PlayerItem("Player13", "player@mail.com"));
        playersArray.add(new PlayerItem("Player14", "player@mail.com"));
        playersArray.add(new PlayerItem("Player15", "player@mail.com"));
        playersArray.add(new PlayerItem("Player16", "player@mail.com"));
        playersArray.add(new PlayerItem("Player17", "player@mail.com"));
        playersArray.add(new PlayerItem("Player18", "player@mail.com"));
        playersArray.add(new PlayerItem("Player19", "player@mail.com"));
        playersArray.add(new PlayerItem("Player20", "player@mail.com"));



        PlayersAdapter playersAdapter = new PlayersAdapter(this, playersArray);

        playersAdapter.setEditIcon(R.drawable.ic_mode_edit_black_18dp);
        playersAdapter.setDeleteIcon(R.drawable.ic_clear_black_18dp);

        final ListView playersList = (ListView) findViewById(R.id.players_list);

        playersList.setAdapter(playersAdapter);
        playersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, int position, long id) {

                final PlayerItem item = (PlayerItem) parent.getItemAtPosition(position);
            }
        });

        FloatingActionButton addPlayerButton = (FloatingActionButton) findViewById(R.id.fab_add_player);
        addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Create custom dialog and use name_edit_dialog.xml
                String name = "";
                String mail = "";

                AlertDialog.Builder alert = new AlertDialog.Builder(PlayersActivity.this);
                alert.setTitle("Add Player"); //Set Alert dialog title here

                LinearLayout layout = new LinearLayout(PlayersActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText nameEditText = new EditText(PlayersActivity.this);;
                nameEditText.setText((String) name);
                layout.addView(nameEditText);

                final EditText mailEditText = new EditText(PlayersActivity.this);
                mailEditText.setText((String) mail);
                layout.addView(mailEditText);

                alert.setView(layout);

                alert.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String newName = nameEditText.getEditableText().toString();
                        String newMail = mailEditText.getEditableText().toString();
                        Toast.makeText(PlayersActivity.this, newName, Toast.LENGTH_LONG).show();
                        playersArray.add(new PlayerItem(newName,newMail));
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
        });

    }
}
