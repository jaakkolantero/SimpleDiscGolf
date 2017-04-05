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
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

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

    protected android.support.v4.app.DialogFragment mPlayerAddDialogFragment;

    private static final String TAG = "PlayerFragment";

    private PlayerViewHolder.SelectedPlayersListener mSelectedPlayersListener;

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

        if (getContext() instanceof PlayerViewHolder.SelectedPlayersListener) {
            mSelectedPlayersListener = (PlayerViewHolder.SelectedPlayersListener) getContext();
        } else {
            throw new RuntimeException(getContext().toString()
                    + " must implement SelectedPlayersListener");
        }
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
        mPlayerRecyclerView.addItemDecoration(new DividerItemDecoration(getContext()));
        mPlayerRecyclerView.setHasFixedSize(false);

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
                showAddPlayerDialog(new PlayerObject("",""),getResources().getString(R.string.add_player_dialog_title),getResources().getString(R.string.add));
                break;
            default:
                break;
        }
    }

    void showAddPlayerDialog(PlayerObject playerObject, String title, String positiveButton) {
        FragmentManager fragmentManager = getFragmentManager();
        String jsonPlayerObject = new Gson().toJson(playerObject,PlayerObject.class);
        mPlayerAddDialogFragment = new PlayerAddDialogFragment().newInstance(jsonPlayerObject,title,positiveButton);
        mPlayerAddDialogFragment.show(fragmentManager,"AddPlayerFragment");
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

        boolean editPlayer = false;
        int i = 0;
        for (PlayerObject tempPlayer : mPlayerObjectArrayList) {

            if (tempPlayer.getId().equals(player.getId()) ) {
                //Update name to startFragment
                mSelectedPlayersListener.selectedPlayersChanged(player);
                editPlayer = true;
                mPlayerObjectArrayList.set(i,player);
                mPlayerAdapter.notifyItemChanged(i);
            }

            i++;
        }

        if (!editPlayer) {
            mPlayerObjectArrayList.add(player);
            mPlayerAdapter.notifyItemInserted(mPlayerObjectArrayList.size()-1);
        }

    }

    void deletePlayer (PlayerObject playerObject) {
        int i = 0;
        Log.d(TAG, "deletePlayer: " + playerObject.getPlayer());
        for (PlayerObject tempPlayer : mPlayerObjectArrayList) {

            if (tempPlayer.getId().equals(playerObject.getId()) ) {
                playerObject.setSelected(false);
                playerObject.setPlayer("");
                mSelectedPlayersListener.selectedPlayersChanged(playerObject);

                mPlayerObjectArrayList.remove(i);
                mPlayerAdapter.notifyItemRemoved(i);
                mPlayerAdapter.notifyItemRangeChanged(i,mPlayerObjectArrayList.size());
                break;
            }
            i++;
        }

    }
}
