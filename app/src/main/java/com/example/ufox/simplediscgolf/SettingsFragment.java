package com.example.ufox.simplediscgolf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.pref_settings);
    }

    @Override
    public void onResume() {
        super.onResume();
        //Listen to Preference changes
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        //Stop listening on Preference changes
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        //If not theme change preference change, leave
        if (!key.equals(SettingsActivity.KEY_PREF_THEME)) {
            return;
        }


        //Send broadcast so other activites know to recreate
        Intent broadCastIntent = new Intent("theme-change");
        broadCastIntent.putExtra("data","theme change");
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(broadCastIntent);

        //Recreate activity to redraw new theme
        getActivity().recreate();
        //No animation
        getActivity().overridePendingTransition(0, 0);

    }
}
