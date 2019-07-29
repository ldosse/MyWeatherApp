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
        i.putExtra("updateService", "true");
        context.startService(i);
        DataManager.getInstance().updateData();
        boolean done = DataManager.updated;
        String feedback = done? "success":"Error";
        Toast.makeText(context, " My Weather App - fetching data - "+feedback, Toast.LENGTH_SHORT).show();
    }


}
