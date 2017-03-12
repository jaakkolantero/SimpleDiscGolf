package com.example.ufox.simplediscgolf;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {

    public static final String KEY_PREF_THEME = "pref_theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Set correct theme
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("pref_theme", false)) {
            setTheme(R.style.AppTheme_Dark);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //TODO Create up button to toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_settings_toolbar);
        setSupportActionBar(toolbar);

        getFragmentManager().beginTransaction()
                .replace(R.id.activity_settings,new SettingsFragment())
                .commit();
    }
}
