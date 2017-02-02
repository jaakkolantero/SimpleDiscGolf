package com.example.android.simplediscgolf;

import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<SetGameItem> setGameArray = new ArrayList<SetGameItem>();

        //Populate SetGameList
        //TODO Strings to strings.xml
        setGameArray.add(new SetGameItem("Players","No Players",
                R.drawable.ic_accessibility_black_48dp,
                //TODO get packagename in front of class for cleaner look
                "com.example.android.simplediscgolf.PlayersActivity"));
        setGameArray.add(new SetGameItem("Course","No Course",
                R.drawable.ic_location_city_black_48dp,
                "com.example.android.simplediscgolf.CoursesActivity"));

        SetGameAdapter setGameAdapter = new SetGameAdapter(this,setGameArray);

        ListView setGameList = (ListView) findViewById(R.id.set_game_list);

        setGameList.setAdapter(setGameAdapter);
        setGameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final SetGameItem item = (SetGameItem)parent.getItemAtPosition(position);

                if (item.getActivity() != null) {
                    try {
                        Class activityClass = Class.forName(item.getActivity());
                        Intent intent = new Intent(MainActivity.this, activityClass);
                        startActivity(intent);

                    } catch (ClassNotFoundException e) {
                        //TODO ERROR HANDLING
                    }

                }
                return;
            }
        });
    }
}
