package uk.ac.gcu.myweatherapp.settings;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.zip.CheckedOutputStream;

import uk.ac.gcu.myweatherapp.BackgroundTasks.AlarmReceiver;
import uk.ac.gcu.myweatherapp.DataManager;
import uk.ac.gcu.myweatherapp.R;

import static android.content.Context.MODE_PRIVATE;
import static android.support.v4.content.ContextCompat.getSystemService;


public class SettingsFragment extends PreferenceFragmentCompat  implements Preference.OnPreferenceChangeListener {
    private PendingIntent pendingIntent8;
    private PendingIntent pendingIntent20;
    private AlarmManager alarmManager;
    private Context context;
    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "uk.ac.gcu.myweatherapp.settings";
    private View view;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        context = getContext();
        view = this.getView();
        mPreferences = this.getActivity()
                .getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
//        Preference preference =
//                this.findPreference("updates_8");


//        switchButton = ;
        // Retrieve a PendingIntent that will perform a broadcast
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        pendingIntent8 = PendingIntent.getBroadcast(context, 123, alarmIntent, 0);
        startAlarm(view, pendingIntent8, 8);

        // Retrieve a PendingIntent that will perform a broadcast
        Intent alarmIntent2 = new Intent(context, AlarmReceiver.class);
        pendingIntent20 = PendingIntent.getBroadcast(context, 321, alarmIntent2, 0);
        startAlarm(view, pendingIntent20,20);
    }

    public void startAlarm(View view, PendingIntent pendingIntent, int hour) {
        // every day at 8 am
        Calendar calendar = Calendar.getInstance();

        // if it's after or equal 8 am schedule for next day
        if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) >= hour) {
            calendar.add(Calendar.DAY_OF_YEAR, 1); // add, not set!
        }
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);

        alarmManager = (AlarmManager)this.getContext().getSystemService(Context.ALARM_SERVICE);
//        int interval = 10000;

        alarmManager.setInexactRepeating(AlarmManager.RTC, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent8);
//        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this.getContext(), "Alarm Set"+ DataManager.updated, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        int hour;
        PendingIntent pi;
        if (preference.getKey().equalsIgnoreCase("updates_8")){
            hour = 8;
            pi = pendingIntent8;
        }else{
            hour = 20;
            pi = pendingIntent20;
        }
//                switchPreferenceCompat.
//                        pref.setSummary(listPref.getEntry());
//            return;
        if ((Boolean) o == true) {
            startAlarm(view,pi,hour);
        } else {
            cancelAlarm(pi);
        }
        return true;
    }

    public void setAlarm(int time){

    }

    public void cancelAlarm(PendingIntent pendingIntent){
            /*
    With FLAG_NO_CREATE it will return null if the PendingIntent doesnt already exist. If it already exists it returns
    reference to the existing PendingIntent
    */
        if (pendingIntent != null)
            alarmManager.cancel(pendingIntent);
    }
}
