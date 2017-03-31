package com.example.ufox.simplediscgolf;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StartFragment extends Fragment {

    private OnFragmentInteractionListener mListener;


    private ArrayList<PlayerObject> mSelectedPlayers;
    private TextView mSelectedPlayersTextView;

    public StartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment StartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StartFragment newInstance() {
        StartFragment fragment = new StartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSelectedPlayers = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        mSelectedPlayersTextView = (TextView) view.findViewById(R.id.tw_players_secondary);
        updateSelectedPlayers();
        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    void selectedPlayersChanged(PlayerObject newPlayer) {
        //if empty : first item in list
        if (mSelectedPlayers.isEmpty()) {
            mSelectedPlayers.add(newPlayer);
        } else {
            //if player is in selected players remove it (deselect)
            int i=0;
            for (PlayerObject player : mSelectedPlayers) {
                if (player.getPlayer() == newPlayer.getPlayer()) {
                    mSelectedPlayers.remove(i);
                    break;
                }
                //if not found before end add to selectedPlayers
                if (i == mSelectedPlayers.size()-1) {
                    mSelectedPlayers.add(newPlayer);
                    break;
                }

                i++;
            }
        }

        updateSelectedPlayers();
    }

    void updateSelectedPlayers() {
        //TODO make players Cardview pretty
        //Update textview
        if (mSelectedPlayers.isEmpty()) {
            mSelectedPlayersTextView.setText(getResources().getString(R.string.outline_no_players));
        } else {
            mSelectedPlayersTextView.setText("");
            for (PlayerObject playerObject : mSelectedPlayers) {
                mSelectedPlayersTextView.append(playerObject.getPlayer() + " | ");
            }
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
