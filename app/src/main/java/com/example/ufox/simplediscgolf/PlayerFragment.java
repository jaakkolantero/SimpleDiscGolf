package com.example.ufox.simplediscgolf;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link PlayerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerFragment extends Fragment implements View.OnClickListener{

    protected RecyclerView mPlayerRecyclerView;
    protected PlayerAdapter mPlayerAdapter;
    protected ArrayList<PlayerObject> mPlayerObjectArrayList;

    protected android.support.v4.app.DialogFragment addPlayerFragment;

    private static final String TAG = "PlayerFragment";

    public PlayerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment PlayerFragment.
     */
    public static PlayerFragment newInstance() {
        PlayerFragment fragment = new PlayerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Populate List with dummy data
        initDataSet();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_player,container,false);
        rootView.setTag(TAG);

        //Get Add new player FAB and set OnClickListener
        FloatingActionButton floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.fab_player);
        floatingActionButton.setOnClickListener(this);

        mPlayerRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_player);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mPlayerRecyclerView.setLayoutManager(layoutManager);
        mPlayerRecyclerView.setHasFixedSize(true);

        mPlayerAdapter = new PlayerAdapter(mPlayerObjectArrayList);

        mPlayerRecyclerView.setAdapter(mPlayerAdapter);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onCreateView: " + v.getId());
        switch (v.getId()) {
            case R.id.fab_player:
                showAddPlayerDialog();
                break;
            default:
                break;
        }
    }

    void showAddPlayerDialog() {
        FragmentManager fragmentManager = getFragmentManager();
        addPlayerFragment = new PlayerAddDialogFragment();
        addPlayerFragment.show(fragmentManager,"AddPlayerFragment");
    }

    void initDataSet() {
        mPlayerObjectArrayList = new ArrayList<PlayerObject>();
        mPlayerObjectArrayList.add(new PlayerObject("Tero", "tero.jaakkola1@gmail.com"));
        mPlayerObjectArrayList.add(new PlayerObject("Player1", "player@mail.com"));
        mPlayerObjectArrayList.add(new PlayerObject("Player2", "player@mail.com"));
        mPlayerObjectArrayList.add(new PlayerObject("Player3", "player@mail.com"));
        mPlayerObjectArrayList.add(new PlayerObject("Player4", "player@mail.com"));
    }

    //Data from playeradddialogfragment through mainactivity
    void updatePlayer (PlayerObject player) {
        mPlayerObjectArrayList.add(player);
    }
}
