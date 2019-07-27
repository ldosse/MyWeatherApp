package uk.ac.gcu.myweatherapp.settings;

import android.app.AlarmManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import uk.ac.gcu.myweatherapp.R;

public class SettingsActivity extends AppCompatActivity {
    public static final String
            KEY_PREF_UPDATES_SWITCH = "updates_switch_preference";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }


}
