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
import android.widget.Toast;

/**
 * Created by ufox on 11.3.2017.
 */

public class PlayerAddDialogFragment extends android.support.v4.app.DialogFragment {

    private static final String TAG = "PlayerAddDialogFragment";

    private AddNewPlayerListener mAddNewPlayerListener;

    // Container Activity must implement this interface
    public interface AddNewPlayerListener {
        public void addNewPlayer(PlayerObject player);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.add_player_dialog_title);
        builder.setView(R.layout.dialog_player_add);
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
        final AlertDialog d = (AlertDialog)getDialog();
        if(d != null)
        {
            Button positiveButton = (Button) d.getButton(Dialog.BUTTON_POSITIVE);
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
                        mAddNewPlayerListener.addNewPlayer(
                                new PlayerObject(
                                        nameEditText.getText().toString().trim(),
                                        mailEditText.getText().toString().trim()));
                        d.dismiss();
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
