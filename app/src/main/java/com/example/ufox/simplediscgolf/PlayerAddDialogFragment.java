package com.example.ufox.simplediscgolf;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

/**
 * Created by ufox on 11.3.2017.
 */

public class PlayerAddDialogFragment extends android.support.v4.app.DialogFragment {

    private static final String TAG = "PlayerAddDialogFragment";

    private AddNewPlayerListener mAddNewPlayerListener;

    private View mView;
    private EditText mPlayerName;
    private EditText mPlayerMail;
    private PlayerObject mPlayerObject;
    private String mTitle;
    private String mPositiveButton;
    private static final String ARG_PLAYEROBJECT = "player-object";
    private static final String ARG_PLAYERTITLE = "player-title";
    private static final String ARG_PLAYERPOSITIVEBUTTON = "player-positive-button";

    // Container Activity must implement this interface
    public interface AddNewPlayerListener {
        public void addNewPlayer(PlayerObject player);
    }

    public static PlayerAddDialogFragment newInstance(String jsonPlayer,String title, String positiveButton) {

        Bundle args = new Bundle();
        args.putString(ARG_PLAYEROBJECT,jsonPlayer);
        args.putString(ARG_PLAYERTITLE, title);
        args.putString(ARG_PLAYERPOSITIVEBUTTON,positiveButton);

        PlayerAddDialogFragment fragment = new PlayerAddDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String jsonPlayerObject;
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_PLAYERTITLE);
            mPositiveButton = getArguments().getString(ARG_PLAYERPOSITIVEBUTTON);

            jsonPlayerObject = getArguments().getString(ARG_PLAYEROBJECT);
            mPlayerObject = new Gson().fromJson(jsonPlayerObject,PlayerObject.class);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        mView = getActivity().getLayoutInflater().inflate(R.layout.dialog_player_add,null);
        mPlayerName = (EditText) mView.findViewById(R.id.dialog_edit_name);
        mPlayerMail = (EditText) mView.findViewById(R.id.dialog_edit_mail);
        mPlayerName.setText(mPlayerObject.getPlayer());
        mPlayerMail.setText(mPlayerObject.getMail());

        builder.setTitle(mTitle);
        builder.setView(mView);


        // Add action buttons
        builder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //NO CODE HERE. Onclick override to change the close behavior
                //Check onResume positiveButton onClick
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                PlayerAddDialogFragment.this.getDialog().cancel();
            }
        });
        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
        final AlertDialog addPlayerDialog = (AlertDialog)getDialog();
        if(addPlayerDialog != null)
        {
            Button positiveButton = (Button) addPlayerDialog.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setText(mPositiveButton);
            positiveButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    EditText mailEditText = (EditText) PlayerAddDialogFragment.this.getDialog().findViewById(R.id.dialog_edit_mail);
                    EditText nameEditText = (EditText) PlayerAddDialogFragment.this.getDialog().findViewById(R.id.dialog_edit_name);

                    //Check for valid email and make it "" if empty (because it's optional)
                    if (isValidMail(mailEditText.getText()) ) {
                        if (mailEditText.getText().toString().trim().length() == 0) {
                            mailEditText.setText("");
                        }

                    } else {
                        Context context = getContext();
                        CharSequence text = getResources().getString(R.string.add_player_check_mail);
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                    if (isValidName(nameEditText.getText()) && isValidMail(mailEditText.getText())) {
                        mPlayerObject.setPlayer(nameEditText.getText().toString().trim());
                        mPlayerObject.setMail(mailEditText.getText().toString().trim());
                        mAddNewPlayerListener.addNewPlayer(mPlayerObject);
                        addPlayerDialog.dismiss();
                    } else {
                        Context context = getContext();
                        CharSequence text = getResources().getString(R.string.add_player_check_name);
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            });


        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddNewPlayerListener) {
            mAddNewPlayerListener = (AddNewPlayerListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement addNewPlayerListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mAddNewPlayerListener = null;
    }

    public static boolean isValidMail(CharSequence target) {
            return target!= null
                    && ( android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
                    || target.toString().trim().length() == 0 );
    }
    public static boolean isValidName(CharSequence target) {
        return target.toString().trim().length() != 0;
    }

}
