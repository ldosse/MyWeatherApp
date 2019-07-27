package uk.ac.gcu.myweatherapp.settings;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.CheckedOutputStream;

import uk.ac.gcu.myweatherapp.BackgroundTasks.AlarmReceiver;
import uk.ac.gcu.myweatherapp.R;

import static android.support.v4.content.ContextCompat.getSystemService;


public class SettingsFragment extends PreferenceFragmentCompat {
    private PendingIntent pendingIntent;
    private AlarmManager manager;
    private Context context;
    private SwitchPreferenceCompat switchButton;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        context = getContext();
//        switchButton = ;
        // Retrieve a PendingIntent that will perform a broadcast
        Intent alarmIntent = new Intent(context,AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
        startAlarm(this.getView());
    }

    public void startAlarm(View view) {
        manager = (AlarmManager)this.getContext().getSystemService(Context.ALARM_SERVICE);
        int interval = 10000;

        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this.getContext(), "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    public void setUpdates(View view){
        System.out.println("00000000000000000000000000000000000000000000000Im clicked");
//        if (this.)
    }

}
