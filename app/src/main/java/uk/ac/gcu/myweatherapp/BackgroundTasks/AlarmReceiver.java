package uk.ac.gcu.myweatherapp.BackgroundTasks;

import android.app.AlarmManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import uk.ac.gcu.myweatherapp.DataManager;
import uk.ac.gcu.myweatherapp.Services.UpdateService;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, UpdateService.class);
        i.putExtra("update service", "true");
        context.startService(i);
        System.out.println("I am receiving");
        DataManager.getInstance().updateData();
        Toast.makeText(context, "I'm running "+DataManager.updated, Toast.LENGTH_SHORT).show();
    }


}
